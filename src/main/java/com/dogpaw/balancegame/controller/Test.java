package com.dogpaw.balancegame.controller;

import com.dogpaw.balancegame.entity.BalanceGame;
import com.dogpaw.balancegame.repository.BalanceGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class Test {

    private final BalanceGameRepository balanceGameRepository;

    @PostMapping("/test")
    public void save() {

        UUID uuid = UUID.randomUUID();

        BalanceGame dataCollections = BalanceGame.builder()
                .name("jisu")
                .uuid(uuid)
                .build();

        balanceGameRepository.save(dataCollections);
    }
}
