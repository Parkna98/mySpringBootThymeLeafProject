package com.sist.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
	Columns:
	STAY_NO bigint 
	STYPE varchar(20) 
	SNAME varchar(200) 
	SCORE float 
	ADDRESS varchar(2000) 
	DETAIL_ADDRESS varchar(2000) 
	PRICE bigint 
	REVIEW_COUNT bigint 
	LIKECOUNT bigint 
	JJIM bigint 
	RDATE varchar(300) 
	HIT bigint 
	STAYMSG varchar(1000)
 */
@Entity
@Data
public class Stayinfo {
	@Id
	private int stay_no;
	private String stype,sname,address,detail_address;
	private int price,review_count,likecount,jjim,hit;
	private String rdate,staymsg;
	
}
