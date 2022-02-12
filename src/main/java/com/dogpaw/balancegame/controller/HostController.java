package com.dogpaw.balancegame.controller;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
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
@RequestMapping("/api/host")
public class HostController {

	private final HostService hostService;

	@PostMapping("/makeHost")
	public @NotNull ObjectId makeHost(@RequestBody HostDTO.makeHost dto) {
		BalanceGame balanceGame = hostService.makeHost(dto.getName());

		return balanceGame.getId();
	}

	@PostMapping("/makeBalanceGame")
	public @NotNull UUID makeBalanceGame(@RequestBody HostDTO.makeBalanceGame dto) throws
		ChangeSetPersister.NotFoundException {
		BalanceGame balanceGame = hostService.makeBalanceGame(dto);

		return balanceGame.getUuid();
	}

}
