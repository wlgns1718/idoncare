package d209.Idontcare.mission.service;


import d209.Idontcare.account.dto.req.VirtualToVirtualReq;
import d209.Idontcare.account.service.VirtualAccountService;
import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.common.exception.NoSuchContentException;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
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
    public List<MissionSimpleDto> findRequestMissionParent(Long parent_userId) throws CommonException {
        List<Tuple> list;
        list = missionRepository.findByParent_UserIdAndType(parent_userId, Type.REQUEST);
        return list.stream().map(MissionSimpleDto::new).toList();
    }

    @Override
    public List<MissionSimpleDto> findProcessMissionParent(Long parent_userId) throws CommonException {
        List<Tuple> list;
        list = missionRepository.findByParent_UserIdAndType(parent_userId, Type.PROCESS);
        return list.stream().map(MissionSimpleDto::new).toList();
    }

    @Override
    public List<MissionSimpleDto> findUnpaidMissionParent(Long parent_userId) throws CommonException {
        List<Tuple> list;
        list = missionRepository.findByParent_UserIdAndType(parent_userId, Type.UNPAID);
        return list.stream().map(MissionSimpleDto::new).toList();
    }

    @Override
    public List<MissionSimpleDto> findCompleteMissionParent(Long parent_userId) throws CommonException {
        List<Tuple> list;
        list = missionRepository.findByParent_UserIdAndType(parent_userId, Type.COMPLETE);
        return list.stream().map(MissionSimpleDto::new).toList();
    }

    @Override
    public List<MissionSimpleDto> findRequestMissionChild(Long child_userId) throws CommonException {
        List<Tuple> list;
        list = missionRepository.findByParent_UserIdAndType(child_userId, Type.REQUEST);
        return list.stream().map(MissionSimpleDto::new).toList();
    }

    @Override
    public List<MissionSimpleDto> findProcessMissionChild(Long child_userId) throws CommonException {
        List<Tuple> list;
        list = missionRepository.findByParent_UserIdAndType(child_userId, Type.PROCESS);
        return list.stream().map(MissionSimpleDto::new).toList();
    }

    @Override
    public List<MissionSimpleDto> findUnpaidMissionChild(Long child_userId) throws CommonException {
        List<Tuple> list;
        list = missionRepository.findByParent_UserIdAndType(child_userId, Type.UNPAID);
        return list.stream().map(MissionSimpleDto::new).toList();
    }

    @Override
    public List<MissionSimpleDto> findCompleteMissionChild(Long child_userId) throws CommonException {
        List<Tuple> list;
        list = missionRepository.findByParent_UserIdAndType(child_userId, Type.COMPLETE);
        return list.stream().map(MissionSimpleDto::new).toList();
    }


    @Override
    public Mission findMission(Long missionId) throws CommonException {
        return missionRepository.findById(missionId).orElseThrow(NoSuchContentException::new);

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
            mission.setType(Type.UNPAID);
        }
        else if(mission.getType() == Type.UNPAID && role == Role.PARENT){
            Long childId = mission.getChild().getUserId();
            VirtualToVirtualReq virtualToVirtualReq = new VirtualToVirtualReq();

            virtualToVirtualReq.setUserId(childId);
            virtualToVirtualReq.setContent("미션 리워드");
            virtualToVirtualReq.setType(d209.Idontcare.account.entity.Type.MISSION);
            virtualToVirtualReq.setMoney(mission.getAmount());

            virtualAccountService.virtualPayment(virtualToVirtualReq, mission.getParent().getUserId());
            mission.setType(Type.COMPLETE);
        }
        else if (mission.getType() == Type.REQUEST && role == Role.PARENT) {
            mission.setAmount(missionStatusDto.getAmount());
            mission.setType(Type.PROCESS);
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
    public MissionDto getMissionDetail(Long missionId) {
        Mission mission = missionRepository.findById(missionId).orElseThrow((NoSuchContentException::new));
        return MissionDto.toDto(mission);
    }


}
