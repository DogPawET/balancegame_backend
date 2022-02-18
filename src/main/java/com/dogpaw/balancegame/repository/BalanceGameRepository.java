package com.dogpaw.balancegame.repository;

import com.dogpaw.balancegame.entity.BalanceGame;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface BalanceGameRepository extends MongoRepository<BalanceGame, ObjectId> {

    Optional<BalanceGame> findBalanceGameByUuid(UUID uuid);
}
