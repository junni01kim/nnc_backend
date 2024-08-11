package com.eastflag.nnc.testkmj.relation;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.testkmj.request.UpdateUserRelationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * UserRelation과 관련된 API가 관리되는 Controller
 *
 */
@RestController
@RequestMapping("/api/v1/userRelation")
@RequiredArgsConstructor
public class UserRelationController {
    private final UserRelationRepository userRelationRepository;
    private final UserRelationService userRelationService;

    /**
     * UserRelation 정보를 전달한다.
     *
     * @param userId 전달받을 User1 Id
     * @return 성공: 200
     */
    @GetMapping("/getUserRelation/{userId}")
    public CommonResponse getUserRelation(
            @PathVariable int userId
    ) {
        var userRelation = userRelationRepository.findByCaretakerId(userId).orElseGet(() -> userRelationRepository
                .findByCaregiverId(userId)
                .orElseThrow(() -> new RuntimeException(userId + "에 대한 관계 설정이 없음")));
        return CommonResponse.builder().code(200).message(userId + ": 객제 전달 성공").data(userRelation).build();
    }

    /**
     * UserRelation 정보 업데이트
     * ※ 관계를 변경시키는 주체는 무조건 CareTaker이다.
     *
     * @param request 수정할 user_relation 정보
     * @return 성공: 200
     */
    @PatchMapping("/updateUserRelation")
    public CommonResponse updateUserRelation(
            @RequestBody UpdateUserRelationRequest request
    ){
        var userRelation = userRelationService.updateUserRelation(request);

        return CommonResponse.builder().code(200).message(request.getCaretakerId() + ": 객제 전달 성공").data(userRelation).build();
    }
}
