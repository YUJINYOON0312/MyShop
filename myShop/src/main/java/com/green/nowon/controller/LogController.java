package com.green.nowon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.nowon.domain.dto.member.MemberInsertDTO;
import com.green.nowon.service.LogService;
import com.green.nowon.service.impl.LogServiceProcess;

@Controller
public class LogController {
	
	@Autowired
	private LogService service; //=new LogServiceProcess();
	
	@GetMapping("/member/login")
	public String login() {
		return "log/login";
	}
	
	@GetMapping("/member/signup")
	public String join() {
		return "log/signup";
	}
	
	//회원가입처리
	@PostMapping("/member/signup")
	public String join(MemberInsertDTO dto) {
		service.save(dto); // 일단 save쓰고 탑다운으로 LogService에 save만들기, 그리고 오버라이드
		return "redirect:/member/login";
	}
}
