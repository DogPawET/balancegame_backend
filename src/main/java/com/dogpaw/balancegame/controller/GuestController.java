package com.dogpaw.balancegame.controller;

import com.dogpaw.balancegame.dto.GuestDTO;
import com.dogpaw.balancegame.dto.ResponseDTO;
import com.dogpaw.balancegame.service.GuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Tag(name = "guest", description = "게스트 API")
public class GuestController {

    private final GuestService guestService;

    // uuid로 호스트 문제 문자 찾아 반환
    @Tag(name = "guest")
    @GetMapping("/{uuid}/questions")
    @Operation(summary = "밸런스게임 질문 찾기", description = "uuid값으로 밸런스게임 문제를 찾아서 반환합니다.")
    @ApiResponse(responseCode = "200", description = "질문 찾기 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.QuestionsResponse.class)))
    public ResponseDTO.QuestionsResponse getQuestions(@PathVariable UUID uuid) {
        return guestService.getQuestions(uuid);
    }

    // 게스트 답 등록 및 결과 반환
    @Tag(name = "guest")
    @PostMapping("/guest")
    @Operation(summary = "게스트 등록", description = "유저의 답 등록 및 결과 반환")
    @ApiResponse(responseCode = "200", description = "답 등록 완료", content = @Content(schema = @Schema(implementation = ResponseDTO.ResultResponse.class)))
    public ResponseDTO.ResultResponse saveGuest(@RequestBody GuestDTO.Create dto) {
        return guestService.saveGuest(dto);
    }

    // 리더보드 반환
    @Tag(name = "guest")
    @GetMapping("/{uuid}/leader-board")
    @Operation(summary = "리더보드 확인")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResponseDTO.LeaderBoard.class)))
    public ResponseDTO.LeaderBoard getLeaderBoard(@PathVariable UUID uuid) {
        return guestService.getLeaderBoard(uuid);
    }

}
