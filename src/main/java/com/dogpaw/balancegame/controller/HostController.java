package com.dogpaw.balancegame.controller;

import com.dogpaw.balancegame.dto.HostDTO.hostResult;
import com.dogpaw.balancegame.dto.HostDTO.makeBalanceGame;

import com.dogpaw.balancegame.dto.HostDTO.makeHost;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogpaw.balancegame.dto.HostDTO;
import com.dogpaw.balancegame.entity.BalanceGame;
import com.dogpaw.balancegame.service.HostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Tag(name = "user", description = "호스트 API")
public class HostController {

    private final HostService hostService;

    @Tag(name = "user")
    @PostMapping("/host")
    @Operation(summary = "호스트 생성", description = "호스트 이름과 문제 개수로 밸런스 게임을 생성합니다.")
    @ApiResponse(responseCode = "200", description = "호스트 생성 성공", content = @Content(schema = @Schema(implementation = HostDTO.hostResult.class)))
    public hostResult makeHost(@RequestBody makeHost dto) {
        BalanceGame balanceGame = hostService.makeHost(dto);

        return new hostResult(balanceGame.getUuid());
    }

    @Tag(name = "user")
    @PostMapping("/balanceGame")
    @Operation(summary = "밸런스게임 생성", description = "질문과 정답을 입력받아 밸런스 게임을 생성합니다.")
    @ApiResponse(responseCode = "200", description = "밸런스게임 생성 성공", content = @Content(schema = @Schema(implementation = HostDTO.hostResult.class)))
    public hostResult makeBalanceGame(@RequestBody makeBalanceGame dto) {
        BalanceGame balanceGame = hostService.makeBalanceGame(dto);

        return new hostResult(balanceGame.getUuid());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(
        IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
