package com.dogpaw.balancegame.controller;

import com.dogpaw.balancegame.dto.GuestDTO;
import com.dogpaw.balancegame.dto.ResponseDTO;
import com.dogpaw.balancegame.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class GuestController {

    private final GuestService guestService;

    // 게스트 답 등록, 스코어 및 확률 반환
    @PostMapping("/{uuid}/guest")
    public ResponseDTO.ResultResponse saveGuest(@PathVariable UUID uuid, @RequestBody GuestDTO.Create dto) {
        return guestService.saveGuest(uuid, dto);
    }

    // 리더보드 확인
    @GetMapping("/{uuid}/leader-board")
    public ResponseDTO.LeaderBoard getLeaderBoard(@PathVariable UUID uuid) {
        return guestService.getLeaderBoard(uuid);
    }

}
