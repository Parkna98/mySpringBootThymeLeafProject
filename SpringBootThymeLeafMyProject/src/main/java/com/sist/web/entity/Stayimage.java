package com.sist.web.entity;
/*
	SINO bigint 
	MAINIMAGE varchar(1000) 
	SUB1 varchar(1000) 
	SUB2 varchar(1000) 
	SUB3 varchar(1000) 
	SUB4 varchar(1000)
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Stayimage {
	@Id
	private int sino;
	private String mainimage,sub1,sub2,sub3,sub4;
}
