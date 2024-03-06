package com.sist.web.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sist.web.entity.Myreply;

import java.util.*;

public interface ReplyDAO extends JpaRepository<Myreply, Integer>{
	@Query(value = "SELECT * FROM myreply WHERE sno=:sno ORDER BY no DESC",
			nativeQuery = true)
	public List<Myreply> replyListData(@Param("sno") int sno);
	
	// insert, update, delete는 만들어져있다
	public Myreply findByNo(int no);
}
