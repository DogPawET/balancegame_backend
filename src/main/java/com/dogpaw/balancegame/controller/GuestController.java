package com.dogpaw.balancegame.controller;

import com.dogpaw.balancegame.dto.GuestDTO;
import com.dogpaw.balancegame.dto.ResponseDTO;
import com.dogpaw.balancegame.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class GuestController {

    private final GuestService guestService;

    // uuid로 호스트 문제 문자 찾아 반환
    @GetMapping("/{uuid}/questions")
    public ResponseDTO.QuestionsResponse getQuestions(@PathVariable UUID uuid) {
        return guestService.getQuestions(uuid);
    }

    // 게스트 답 등록 및 결과 반환
    @PostMapping("/guest")
    public ResponseDTO.ResultResponse saveGuest(@RequestBody GuestDTO.Create dto) {
        return guestService.saveGuest(dto);
    }

    // 리더보드 반환
    @GetMapping("/{uuid}/leader-board")
    public ResponseDTO.LeaderBoard getLeaderBoard(@PathVariable UUID uuid) {
        return guestService.getLeaderBoard(uuid);
    }

}
