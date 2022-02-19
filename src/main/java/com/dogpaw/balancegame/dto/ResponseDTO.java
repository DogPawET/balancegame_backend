package com.dogpaw.balancegame.dto;

import com.dogpaw.balancegame.entity.BalanceGame;
import com.dogpaw.balancegame.entity.Guest;
import com.dogpaw.balancegame.entity.Question;
import lombok.Getter;
import org.bson.types.ObjectId;

import java.util.List;

public class ResponseDTO {

    @Getter
    public static class ResultResponse {
        private String hostName;
        private String guestName;
        private List<Question> questions;
        private List<Byte> hostAnswers;
        private List<Byte> guestAnswers;
        private Integer score;
        private Integer percentage;

        public ResultResponse(BalanceGame balanceGame, Guest guest) {
            this.hostName = balanceGame.getName();
            this.guestName = guest.getName();
            this.questions = balanceGame.getQuestions();
            this.hostAnswers = balanceGame.getAnswers();
            this.guestAnswers = guest.getAnswers();
            this.score = guest.getScore();
            this.percentage = guest.getPercentage();
        }
    }

    @Getter
    public static class LeaderBoard {
        private String hostName;
        private List<GuestResponse> guest;

        public LeaderBoard(String hostName, List<GuestResponse> guest) {
            this.hostName = hostName;
            this.guest = guest;
        }
    }

    @Getter
    public static class GuestResponse {
        private String name;
        private Integer percentage;

        public GuestResponse(Guest guest) {
            this.name = guest.getName();
            this.percentage = guest.getPercentage();
        }
    }
}
