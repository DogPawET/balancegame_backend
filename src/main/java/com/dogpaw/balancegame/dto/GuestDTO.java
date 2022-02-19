package com.dogpaw.balancegame.dto;

import com.dogpaw.balancegame.entity.Guest;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

public class GuestDTO {

    @Getter
    public static class Create {
        private UUID uuid;
        private String name;
        private List<Byte> answers;

        @Builder
        public Create(UUID uuid, String name, List<Byte> answers) {
            this.uuid = uuid;
            this.name = name;
            this.answers = answers;
        }

        public Guest toEntity(Integer score, Integer percentage) {
            return Guest.builder()
                    .name(name)
                    .answers(answers)
                    .score(score)
                    .percentage(percentage)
                    .build();
        }

    }
}
