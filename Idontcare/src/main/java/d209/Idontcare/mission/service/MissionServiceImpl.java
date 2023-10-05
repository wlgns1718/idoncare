package d209.Idontcare.mission.service;


import d209.Idontcare.account.dto.req.VirtualToVirtualReq;
import d209.Idontcare.account.service.VirtualAccountService;
import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.common.exception.NoSuchContentException;
import d209.Idontcare.common.exception.UpdateFailException;
import d209.Idontcare.mission.dto.MissionDetailInfoDto;
import d209.Idontcare.mission.dto.MissionDto;
import d209.Idontcare.mission.dto.MissionSimpleDto;
import d209.Idontcare.mission.dto.MissionStatusDto;
import d209.Idontcare.mission.entity.Mission;
import d209.Idontcare.mission.entity.Type;
import d209.Idontcare.mission.repository.MissionRepository;
import d209.Idontcare.user.entity.Role;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Tuple;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final UserRepository userRepository;
    private final VirtualAccountService virtualAccountService;


    @Override
    public Long[] registMission(MissionDto missionDto,Role role) throws CommonException {

        int len = missionDto.getChildIds().length;
        Long[] answer = new Long[len];
        User parents = userRepository.getReferenceById(missionDto.getParentId());

        if(role == Role.PARENT){
            missionDto.setType(Type.PROCESS);
            for(int i = 0; i < len; i++){

                User child = userRepository.getReferenceById(missionDto.getChildIds()[i]);

                Mission mission = Mission.toRegistEntity(missionDto,child,parents);
                missionRepository.save(mission);
                answer[i] = mission.getMissionId();

            }
        }
        else{
            missionDto.setType(Type.REQUEST);
            User child = userRepository.getReferenceById(missionDto.getChildIds()[0]);
            Mission mission = Mission.toRegistEntity(missionDto,child,parents);
            answer[0] = mission.getMissionId();

        }


        return answer;
    }

    @Override
    public List<MissionSimpleDto> findAllMission(Long userId, Role role) {
        List<Tuple> missions;
        if(role == Role.PARENT){
            missions = missionRepository.findAllByParent_UserId(userId);
        }
        else {
            missions = missionRepository.findAllByChild_UserId(userId);
        }
        return missions.stream().map(MissionSimpleDto::new).toList();
    }

    @Override
    public Long updateStatus(MissionStatusDto missionStatusDto, Role role) {
        Long missionId = missionStatusDto.getMissionId();
        Mission mission = missionRepository.findById(missionId).orElseThrow(NoSuchContentException::new);
        if(mission.getType() == Type.PROCESS && role == Role.CHILD){

            mission.setAfterMessage(missionStatusDto.getAfterMessage());
            mission.setType(Type.UNPAID);
            log.info("테스트 중입니다.");
//            Long childId = mission.getChild().getUserId();
//            VirtualToVirtualReq virtualToVirtualReq = new VirtualToVirtualReq();
//            virtualToVirtualReq.setUserId(childId);
//            virtualToVirtualReq.setContent("미션 리워드");
//            virtualToVirtualReq.setType(d209.Idontcare.account.entity.Type.MISSION);
//            virtualToVirtualReq.setMoney(mission.getAmount());
//
//            virtualAccountService.virtualPayment(virtualToVirtualReq, mission.getParent().getUserId());
//            mission.setType(Type.COMPLETE);

        }
        else if(mission.getType() == Type.UNPAID && role == Role.PARENT){

            log.info("완료 미션에 대해 리워드를 지급합니다.");
            Long childId = mission.getChild().getUserId();

            VirtualToVirtualReq virtualToVirtualReq = new VirtualToVirtualReq();
            virtualToVirtualReq.setUserId(childId);
            virtualToVirtualReq.setContent("미션 리워드");
            virtualToVirtualReq.setType(d209.Idontcare.account.entity.Type.MISSION);
            virtualToVirtualReq.setMoney(mission.getAmount());
            virtualAccountService.virtualPayment(virtualToVirtualReq, virtualAccountService.userAccount(mission.getParent().getUserId()));
            log.info("완료 미션에 대해 리워드를 지급 완료했습니다.");
            mission.setType(Type.COMPLETE);
        }
        else if (mission.getType() == Type.REQUEST && role == Role.PARENT) {
            if(missionStatusDto.getAmount() != 0){
                mission.setAmount(missionStatusDto.getAmount());
            }
            if(missionStatusDto.getBeforeMessage() != null){
                mission.setBeforeMessage(missionStatusDto.getBeforeMessage());
            }
            mission.setType(Type.PROCESS);
        }
        else{
            throw new UpdateFailException();
        }
        return mission.getMissionId();
    }

    @Override
    public void deleteMission(Long missionId) {

        if(missionRepository.existsById(missionId)) {
            missionRepository.deleteById(missionId);
        }
        else{
            throw new NoSuchContentException();
        }
    }

    @Override
    public MissionDetailInfoDto getMissionDetail(Long missionId) {
        Mission mission = missionRepository.findById(missionId).orElseThrow((NoSuchContentException::new));
        return MissionDetailInfoDto.toDto(mission);
    }


}
