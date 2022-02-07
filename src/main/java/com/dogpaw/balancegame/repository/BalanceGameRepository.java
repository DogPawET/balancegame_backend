package com.dogpaw.balancegame.repository;

import com.dogpaw.balancegame.entity.BalanceGame;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BalanceGameRepository extends MongoRepository<BalanceGame, ObjectId> {
}
