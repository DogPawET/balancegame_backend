package com.dogpaw.balancegame.dto;

import java.util.List;

import org.bson.types.ObjectId;

import com.dogpaw.balancegame.entity.Question;

import lombok.Data;

public class HostDTO {
	@Data
	public static class makeHost {
		private String name;
	}

	@Data
	public static class makeBalanceGame {
		private ObjectId id;
		private List<Question> questions;
		private List<Byte> answers;
	}
}
