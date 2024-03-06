package com.sist.web.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;
import java.util.*;


@Repository
public interface StayDAO extends JpaRepository<Stayinfo, Integer>{
	// 목록출력
	@Query(value = "SELECT * "
			+ "FROM stayinfo ORDER BY stay_no ASC "
			+ "LIMIT :start,9", nativeQuery = true)
	public List<Stayinfo> stayListData(@Param("start") int start);
	
	@Query(value = "SELECT COUNT(*) FROM stayinfo", nativeQuery = true)
	public int stayRowCount();
	
	// 디테일
	@Query(value = "SELECT * FROM stayinfo "
			+ "WHERE stay_no=:stay_no",nativeQuery = true)
	public Stayinfo stayDetailData(@Param("stay_no") int stay_no);
	
	@Query(value = "SELECT * FROM stayinfo "
			+ "WHERE detail_address LIKE CONCAT('%',:address,'%') "
			+ "LIMIT :start,9",nativeQuery = true)
	public List<Stayinfo> stayFindListData(@Param("address") String address, @Param("start") int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/9.0) FROM stayinfo "
			+ "WHERE detail_address LIKE CONCAT('%',:address,'%')",nativeQuery = true)
	public int stayFindTotalPage(@Param("address") String address);
	
	
	
}
