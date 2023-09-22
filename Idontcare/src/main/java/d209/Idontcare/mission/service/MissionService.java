package d209.Idontcare.mission.service;


import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.mission.dto.MissionDto;
import d209.Idontcare.mission.dto.MissionSimpleDto;
import d209.Idontcare.mission.dto.MissionStatusDto;
import d209.Idontcare.mission.entity.Mission;
import d209.Idontcare.user.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MissionService {

    Long[] registMission(MissionDto missionDto) throws CommonException;

    List<MissionSimpleDto> findRequestMissionParent(Long parent_userId) throws CommonException;

    List<MissionSimpleDto> findProcessMissionParent (Long parent_userId) throws CommonException;

    List<MissionSimpleDto> findUnpaidMissionParent (Long parent_userId) throws CommonException;

    List<MissionSimpleDto> findCompleteMissionParent (Long parent_userId) throws CommonException;

    List<MissionSimpleDto> findRequestMissionChild(Long child_userId) throws CommonException;

    List<MissionSimpleDto> findProcessMissionChild (Long child_userId) throws CommonException;

    List<MissionSimpleDto> findUnpaidMissionChild (Long child_userId) throws CommonException;

    List<MissionSimpleDto> findCompleteMissionChild (Long child_userId) throws CommonException;

    Mission findMission(Long missionId) throws CommonException;

     List<MissionSimpleDto> findAllMission(Long userId, Role role);

    Long updateStatus(MissionStatusDto missionStatusDto, Role role);

    void deleteMission(Long missionId);
}
