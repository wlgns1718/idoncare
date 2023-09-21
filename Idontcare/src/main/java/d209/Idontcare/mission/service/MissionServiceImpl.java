package d209.Idontcare.mission.service;


import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.common.exception.NoSuchContentException;
import d209.Idontcare.mission.dto.MissionDto;
import d209.Idontcare.mission.dto.MissionSimpleDto;
import d209.Idontcare.mission.entity.Mission;
import d209.Idontcare.mission.entity.Type;
import d209.Idontcare.mission.repository.MissionRepository;
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


    @Override
    public Long registMission(MissionDto missionDto) throws CommonException {

        User child = userRepository.getReferenceById(missionDto.getChildId());
        User parents = userRepository.getReferenceById(missionDto.getParentId());
        Mission mission = Mission.toEntity(missionDto,child,parents);

        missionRepository.save(mission);
        return mission.getMissionId();
    }

    @Override
    public List<MissionSimpleDto> findRequestMissionParent(Long parent_userId) throws CommonException {
        List<Tuple> list = new ArrayList<>();
        list = missionRepository.findByParent_UserIdAndType(parent_userId, Type.REQUEST);
        return list.stream().map(MissionSimpleDto::new).toList();
    }

    @Override
    public List<MissionSimpleDto> findProcessMissionParent(Long parent_userId) throws CommonException {
        List<Tuple> list = new ArrayList<>();
        list = missionRepository.findByParent_UserIdAndType(parent_userId, Type.PROCESS);
        return list.stream().map(MissionSimpleDto::new).toList();
    }

    @Override
    public List<MissionSimpleDto> findUnpaidMissionParent(Long parent_userId) throws CommonException {
        List<Tuple> list = new ArrayList<>();
        list = missionRepository.findByParent_UserIdAndType(parent_userId, Type.UNPAID);
        return list.stream().map(MissionSimpleDto::new).toList();
    }

    @Override
    public List<MissionSimpleDto> findCompleteMissionParent(Long parent_userId) throws CommonException {
        List<Tuple> list = new ArrayList<>();
        list = missionRepository.findByParent_UserIdAndType(parent_userId, Type.COMPLETE);
        return list.stream().map(MissionSimpleDto::new).toList();
    }

    @Override
    public List<MissionSimpleDto> findRequestMissionChild(Long child_userId) throws CommonException {
        List<Tuple> list = new ArrayList<>();
        list = missionRepository.findByParent_UserIdAndType(child_userId, Type.REQUEST);
        return list.stream().map(MissionSimpleDto::new).toList();
    }

    @Override
    public List<MissionSimpleDto> findProcessMissionChild(Long child_userId) throws CommonException {
        List<Tuple> list = new ArrayList<>();
        list = missionRepository.findByParent_UserIdAndType(child_userId, Type.PROCESS);
        return list.stream().map(MissionSimpleDto::new).toList();
    }

    @Override
    public List<MissionSimpleDto> findUnpaidMissionChild(Long child_userId) throws CommonException {
        List<Tuple> list = new ArrayList<>();
        list = missionRepository.findByParent_UserIdAndType(child_userId, Type.UNPAID);
        return list.stream().map(MissionSimpleDto::new).toList();
    }

    @Override
    public List<MissionSimpleDto> findCompleteMissionChild(Long child_userId) throws CommonException {
        List<Tuple> list = new ArrayList<>();
        list = missionRepository.findByParent_UserIdAndType(child_userId, Type.COMPLETE);
        return list.stream().map(MissionSimpleDto::new).toList();
    }


    @Override
    public Mission findMission(Long missionId) throws CommonException {
        return missionRepository.findById(missionId).orElseThrow(NoSuchContentException::new);

    }


}
