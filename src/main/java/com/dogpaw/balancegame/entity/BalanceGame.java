package com.dogpaw.balancegame.entity;

import lombok.*;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "new_collections")
public class BalanceGame {

	@Id
	@NotNull
	private ObjectId id;

	@NotNull
	private String name;

	@NotNull
	private UUID uuid;

	@NotNull
	private Integer questionNumber;

	private List<Question> questions;

	private List<Byte> answers;

	private List<Guest> guests;

	@Builder
	public BalanceGame(ObjectId id, String name, UUID uuid, Integer questionNumber, List<Question> questions,
		List<Byte> answers) {
		this.id = id;
		this.name = name;
		this.uuid = uuid;
		this.questionNumber = questionNumber;
		this.questions = questions;
		this.guests = new ArrayList<>();
		this.answers = answers;
	}

}
