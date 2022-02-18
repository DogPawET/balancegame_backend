package com.dogpaw.balancegame.dto;

import com.dogpaw.balancegame.entity.Guest;
import lombok.Getter;
import org.bson.types.ObjectId;

import java.util.List;

public class ResponseDTO {

    @Getter
    public static class LeaderBoard {
        private String hostName;
        private List<GuestResponse> guest;

        public LeaderBoard(String hostName, List<GuestResponse> guest) {
            this.hostName = hostName;
            this.guest = guest;
        }
    }

    public static class GuestResponse {
        private ObjectId id;
        private String name;
        private Integer percentage;

        public GuestResponse(Guest entity) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.percentage = entity.getPercentage();
        }
    }
}
