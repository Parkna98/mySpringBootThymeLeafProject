package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import jakarta.servlet.http.HttpServletRequest;

import com.sist.web.entity.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import com.sist.web.dao.*;

@Controller
public class StayController {
	@Autowired
	private StayDAO dao;
	
	@Autowired
	private StayimageDAO iDao;
	
	@Autowired
	private ReplyDAO rDao;
	
	@GetMapping("/stay/main")
	public String stay_main(String page,Model model) {
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		int rowSize=9;
		int start=(rowSize*curpage)-rowSize;
		List<Stayinfo> list=dao.stayListData(start);
		List<Stayimage> iList=iDao.imageListData(start);
		int count=dao.stayRowCount();
		int totalpage=(int)(Math.ceil(count/9.0));
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		
		
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("count", count);
		model.addAttribute("list", list);
		model.addAttribute("iList", iList);
		

		model.addAttribute("main_html","stay/main");
		
		return "main";
	}
	
	@GetMapping("/stay/before")
	public String stay_before(int stay_no,RedirectAttributes ra,HttpServletResponse response) {
		// 쿠키에 저장
				Cookie cookie=new Cookie("stay"+stay_no, String.valueOf(stay_no));
				// cookie는 저장시에 문자열만 저장이 가능
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24);
				response.addCookie(cookie); // 클라이언트 브라우저로 전송
				
				ra.addAttribute("stay_no",stay_no);
				return "redirect:../stay/detail";
				/*
				 *		RedirectAttributes sendRedirect을 이용해서 데이터 전송 
				 *		Model : forward 
				 */
	}
	
	@GetMapping("/stay/detail")
	public String stay_detail(int stay_no,Model model) {
		
		int sno=stay_no;
		List<Myreply> list=rDao.replyListData(stay_no);
		int len=list.size();
		Stayinfo svo=dao.stayDetailData(stay_no);
		int sino=stay_no;
		Stayimage ivo=iDao.findBySino(sino);
		
		model.addAttribute("svo", svo);
		model.addAttribute("ivo",ivo);
		model.addAttribute("list",list);
		model.addAttribute("stay_no",sno);
		model.addAttribute("len", len);
		model.addAttribute("main_html", "stay/detail");
		
		return "main";
		
	}
	
	@RequestMapping("/stay/find")
	public String stay_find(String page,String address,Model model) {
		if(page==null) {
			page="1";
		}
		if(address==null) {
			address="인천";
		}
		int curpage=Integer.parseInt(page);
		int rowSize=9;
		int start=(rowSize*curpage)-rowSize;
		List<Stayimage> iList=new ArrayList<Stayimage>();
		List<Stayinfo> list=dao.stayFindListData(address,start);
		for(Stayinfo si:list) {
			int sino=si.getStay_no();
			Stayimage sivo=iDao.findBySino(sino);
			iList.add(sivo);
		}
		
		int totalpage=dao.stayFindTotalPage(address);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		model.addAttribute("iList", iList);
		model.addAttribute("address", address);		

		model.addAttribute("main_html","stay/find");
		
		return "main";
	}
	
}
