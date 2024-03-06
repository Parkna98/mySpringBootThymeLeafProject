package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import com.sist.web.entity.*;

import jakarta.servlet.http.HttpSession;

import com.sist.web.dao.*;

@Controller
public class ReplyController {
	@Autowired
	private ReplyDAO dao;
	
	@PostMapping("/reply/insert")
	public String reply_insert(Myreply vo,RedirectAttributes ra,HttpSession session) {
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		vo.setId(id);
		vo.setName(name);
		dao.save(vo); // insert
		ra.addAttribute("stay_no", vo.getSno());
		
		return "redirect:/stay/detail";
	}
	
	@GetMapping("/reply/delete")
	public String reply_delete(int sno,int no,RedirectAttributes ra) {
		Myreply vo=dao.findByNo(no);
		dao.delete(vo);
		ra.addAttribute("stay_no", sno);
		return "redirect:/stay/detail";
	}
	
	@PostMapping("/reply/update")
	public String reply_update(Myreply vo,RedirectAttributes ra) {
		dao.save(vo);
		ra.addAttribute("stay_no", vo.getSno());
		return "redirect:/stay/detail";
	}
	
}
