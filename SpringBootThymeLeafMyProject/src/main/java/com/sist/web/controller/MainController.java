package com.sist.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.dao.StayDAO;
import com.sist.web.dao.StayimageDAO;
import com.sist.web.entity.Stayimage;
import com.sist.web.entity.Stayinfo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	@Autowired
	private StayDAO dao;
	
	@Autowired
	private StayimageDAO iDao;
	
	@GetMapping("/")
	public String main_page(Model model,HttpServletRequest request) {
		
		Cookie[] cookies=request.getCookies();
		List<Stayinfo> cList=new ArrayList<Stayinfo>();
		List<Stayimage> ciList=new ArrayList<Stayimage>();
		int k=0;
		if(cookies!=null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("stay")) {
					if(k>4) break;
					String stay_no=cookies[i].getValue();
					String sino=stay_no;
					Stayinfo r=dao.stayDetailData(Integer.valueOf(stay_no));
					Stayimage ivo=iDao.findBySino(Integer.valueOf(sino));
					cList.add(r);
					ciList.add(ivo);
					k++;
				}
			}
		}
		
		
		model.addAttribute("cList", cList);
		model.addAttribute("ciList", ciList);
		
		model.addAttribute("main_html","main/home");
		return "main";
	}
}
