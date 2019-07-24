package com.modern.be.book.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
//@Entity
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "history")
public class History {
	
	private Long seq;
	private String userId;
	private String keyword;
	private Date searchDate;
}
