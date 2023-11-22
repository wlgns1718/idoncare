package d209.Idontcare.mission.service;


import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.mission.dto.MissionDetailInfoDto;
import d209.Idontcare.mission.dto.MissionDto;
import d209.Idontcare.mission.dto.MissionSimpleDto;
import d209.Idontcare.mission.dto.MissionStatusDto;
import d209.Idontcare.mission.entity.Mission;
import d209.Idontcare.user.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MissionService {

    Long[] registMission(MissionDto missionDto,Role role) throws CommonException;

     List<MissionSimpleDto> findAllMission(Long userId, Role role);

    Long updateStatus(MissionStatusDto missionStatusDto, Role role);

    void deleteMission(Long missionId);

    MissionDetailInfoDto getMissionDetail(Long missionId);
}
