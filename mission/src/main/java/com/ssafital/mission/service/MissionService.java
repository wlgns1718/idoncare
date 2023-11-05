package com.ssafital.mission.service;


import com.ssafital.mission.dto.MissionDetailInfoDto;
import com.ssafital.mission.dto.MissionDto;
import com.ssafital.mission.dto.MissionSimpleDto;
import com.ssafital.mission.dto.MissionStatusDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MissionService {

    Long[] registMission(MissionDto missionDto, Role role) throws CommonException;

    List<MissionSimpleDto> findAllMission(Long userId, Role role);

    Long updateStatus(MissionStatusDto missionStatusDto, Role role);

    void deleteMission(Long missionId);

    MissionDetailInfoDto getMissionDetail(Long missionId);
}

