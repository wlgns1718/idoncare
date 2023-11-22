package com.ssafital.mission.controller;

import com.ssafital.mission.dto.MissionDto;
import com.ssafital.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RequestMapping("/api/mission")
@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;
    @PostMapping(value = "/regist")
//    @Operation(summary = "미션 등록", description = "부모와 미션 등록할 자녀의 userId를 통해 미션 등록")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공"),
//            @ApiResponse(responseCode = BadRequestException.CODE, description = BadRequestException.DESCRIPTION),
//    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto<?> regist(@RequestBody MissionDto missionDto, HttpServletRequest request){
//        (Long)request.getAttribute("userId");
        Role role = (Role) request.getAttribute("role");
        try{
            System.out.println(missionDto);
            System.out.println(role);
            Long[] missionIds = missionService.registMission(missionDto,role);
            return ResponseDto.success(missionIds);

        }
        catch (CommonException e){
            return ResponseDto.fail(e);
        }
    }

    @GetMapping(value = "")
//    @Operation(summary = "미션 불러오기", description = "해당 유저의 타입과 ID로 미션 불러오기")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공",
//                    content = @Content(schema = @Schema(implementation = GetMissionInfoDto.class))),
//            @ApiResponse(responseCode = BadRequestException.CODE, description = BadRequestException.DESCRIPTION),
//    })
    public ResponseDto<?> getMission(HttpServletRequest request){

        try{
            Long userId = (Long) request.getAttribute("userId");
            Role role = (Role) request.getAttribute("role");
            List<MissionSimpleDto> missions = missionService.findAllMission(userId, role);
            return ResponseDto.success(missions);
        }catch(NoSuchContentException e){
            return ResponseDto.fail(e);
        }
    }
    @GetMapping("/{missionId}")
//    @Operation(summary = "미션 상세 조회", description =  "미션ID로 미션 상세 정보 불러오기")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공",
//                    content = @Content(schema = @Schema(implementation = GetMissionDetailInfoDto.class))),
//            @ApiResponse(responseCode = BadRequestException.CODE, description = BadRequestException.DESCRIPTION),
//    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto<?> getMissionDetail(@PathVariable("missionId") Long missionId){
        try{
            MissionDetailInfoDto missionDetailInfoDto = missionService.getMissionDetail(missionId);
            return ResponseDto.success(missionDetailInfoDto);
        }
        catch (NoSuchContentException e){
            return ResponseDto.fail(e);
        }
    }



    @PutMapping("/status")
//    @Operation(summary = "미션 상태 변경", description = "REQUEST > PROCESS > UNPAID > COMPLETE")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공"),
//            @ApiResponse(responseCode = BadRequestException.CODE, description = BadRequestException.DESCRIPTION),
//    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto<?> changeStatus(@RequestBody MissionStatusDto missionStatusDto,HttpServletRequest request){
        try{
            Role role = (Role) request.getAttribute("role");
            Long missionId = missionService.updateStatus(missionStatusDto, role);
            return ResponseDto.success(missionId);
        }catch (UpdateFailException | NoSuchContentException e){
            return ResponseDto.fail(e);
        }

    }

    @DeleteMapping("/{missionId}")
//    @Operation(summary = "미션 삭제", description = "MissionId로 조회 후 삭제")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공"),
//            @ApiResponse(responseCode = BadRequestException.CODE, description = BadRequestException.DESCRIPTION),
//    })
    public ResponseDto<?> deleteMission(@PathVariable("missionId") Long missionId){
        try{
            missionService.deleteMission(missionId);
            HashMap<String,String> map= new HashMap<>();
            map.put("msg","삭제가 완료되었습니다.");
            return ResponseDto.success(map);
        }
        catch (NoSuchContentException e){
            return ResponseDto.fail(e);
        }
    }

}
