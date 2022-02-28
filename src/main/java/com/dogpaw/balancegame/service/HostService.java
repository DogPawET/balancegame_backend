package com.dogpaw.balancegame.service;

import com.dogpaw.balancegame.dto.HostDTO.makeBalanceGame;
import com.dogpaw.balancegame.dto.ResponseDTO;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dogpaw.balancegame.dto.HostDTO;
import com.dogpaw.balancegame.entity.BalanceGame;
import com.dogpaw.balancegame.repository.BalanceGameRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class HostService {

    private final BalanceGameRepository balanceGameRepository;

    @Transactional
    public ResponseDTO.hostResponse makeHost(HostDTO.makeHost dto) {
        UUID uuid = UUID.randomUUID();

        BalanceGame balanceGame = BalanceGame.builder()
            .name(dto.getName())
            .questionNumber(dto.getQuestionNumber())
            .uuid(uuid)
            .build();
        balanceGameRepository.save(balanceGame);

        return new ResponseDTO.hostResponse(balanceGame.getUuid());
    }

    @Transactional
    public ResponseDTO.hostResponse makeBalanceGame(makeBalanceGame dto) {
        BalanceGame balanceGame = balanceGameRepository.findBalanceGameByUuid(dto.getUuid())
            .orElseThrow(() -> new IllegalArgumentException(
                "balanceGame with uuid : " + dto.getUuid() + "is not valid"));

        BalanceGame dataCollections = BalanceGame.builder()
            .id(balanceGame.getId())
            .uuid(balanceGame.getUuid())
            .name(balanceGame.getName())
            .questionNumber(balanceGame.getQuestionNumber())
            .questions(dto.getQuestions())
            .answers(dto.getAnswers())
            .build();

        dataCollections.validateQuestion(dto.getQuestions().size());
        balanceGameRepository.save(dataCollections);

        return new ResponseDTO.hostResponse(balanceGame.getUuid());
    }
}
