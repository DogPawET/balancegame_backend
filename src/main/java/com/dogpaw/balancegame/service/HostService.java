package com.dogpaw.balancegame.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import com.dogpaw.balancegame.dto.HostDTO;
import com.dogpaw.balancegame.entity.BalanceGame;
import com.dogpaw.balancegame.repository.BalanceGameRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HostService {
	private final BalanceGameRepository balanceGameRepository;

	public BalanceGame makeHost(String name) {
		UUID uuid = UUID.randomUUID();

		BalanceGame dataCollections = BalanceGame.builder()
			.name(name)
			.uuid(uuid)
			.build();

		return balanceGameRepository.save(dataCollections);
	}

	public BalanceGame makeBalanceGame(HostDTO.makeBalanceGame dto) throws ChangeSetPersister.NotFoundException {
		BalanceGame balanceGame = balanceGameRepository.findById(dto.getId())
			.orElseThrow((ChangeSetPersister.NotFoundException::new));

		BalanceGame dataCollections = BalanceGame.builder()
			.id(balanceGame.getId())
			.uuid(balanceGame.getUuid())
			.name(balanceGame.getName())
			.questions(dto.getQuestions())
			.answers(dto.getAnswers())
			.build();

		return balanceGameRepository.save(dataCollections);

	}
}
