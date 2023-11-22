package com.ssafital.mission.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MissionStatusDto {

    private Long missionId;

    private String afterMessage;

    private String beforeMessage;

    private Long amount;
}

