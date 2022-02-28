package com.dogpaw.balancegame.dto;

import java.util.List;

import java.util.UUID;

import com.dogpaw.balancegame.entity.Question;

import lombok.Data;

public class HostDTO {

    @Data
    public static class makeHost {

        private String name;
        private int questionNumber;
    }

    @Data
    public static class hostResult {
        private UUID uuid;

        public hostResult(UUID uuid) {
            this.uuid = uuid;
        }
    }

    @Data
    public static class makeBalanceGame {

        private UUID uuid;
        private List<Question> questions;
        private List<Byte> answers;
    }
}
