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
		BalanceGame balanceGame = BalanceGame.of(dto.getName(), UUID.randomUUID(), dto.getQuestionNumber());

		balanceGameRepository.save(balanceGame);

		return new ResponseDTO.hostResponse(balanceGame.getUuid());
	}

	@Transactional
	public ResponseDTO.hostResponse makeBalanceGame(makeBalanceGame dto) {
		BalanceGame existBalanceGame = balanceGameRepository.findBalanceGameByUuid(dto.getUuid())
			.orElseThrow(() -> new IllegalArgumentException(
				"balanceGame with uuid : " + dto.getUuid() + "is not valid"));

		BalanceGame balanceGame = BalanceGame.of(existBalanceGame, dto.getQuestions(), dto.getAnswers());
		balanceGameRepository.save(balanceGame);

		return new ResponseDTO.hostResponse(balanceGame.getUuid());
	}
}
