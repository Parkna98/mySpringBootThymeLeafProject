package com.sist.web.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;
import java.util.*;

@Repository
public interface StayimageDAO extends JpaRepository<Stayimage, Integer>{
	// 목록출력
	@Query(value = "SELECT * FROM stayimage "
			+ "ORDER BY sino ASC "
			+ "LIMIT :start,9",nativeQuery = true)
	public List<Stayimage> imageListData(@Param("start") int start);
	
	/*
	 * @Query(value = "SELECT COUNT(*) FROM stayimage", nativeQuery = true) public
	 * int imageRowCount();
	 */
	
	// 디테일
	public Stayimage findBySino(int sino);
	
	// find 
}
