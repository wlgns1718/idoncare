package d209.Idontcare.mission.controller;


import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.mission.dto.MissionDto;
import d209.Idontcare.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mission")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MissionController {

    private final MissionService missionService;

    @PostMapping(value = "/regist")
    public ResponseDto<?> regist(@RequestBody MissionDto missionDto){

        try{
            Long missionId = missionService.registMission(missionDto);
            return ResponseDto.success(missionId);
        }
        catch (CommonException e){
            return ResponseDto.fail(e);
        }
    }

    @GetMapping(value = "")
    public ResponseDto<?> mission(Authentication authentication){

        try{
            String userId = authentication.getName();
            missionService.
        }
    }

}
