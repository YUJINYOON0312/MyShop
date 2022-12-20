package com.green.nowon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.service.CategoryService;
import com.green.nowon.service.impl.CategoryServiceProcess;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService service; //=new CategoryServiceProcess(); 탑다운후 지우기
	
	@GetMapping("/admin/categories")
	public String category() {
		return "category/reg";
	}
	
	@ResponseBody //ajax요청이니까
	@GetMapping("/admin/categories/{text}")
	public String category(@PathVariable String text) {
		
		return service.isReg(text);
	}
	
	@PostMapping("/admin/categories")
	public String category(String[] name) {
		service.save(name);
		return "category/reg";
	}

}
