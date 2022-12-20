package com.green.nowon.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.service.GoodsService;
import com.green.nowon.service.impl.GoodsServiceProcess;

@Controller
public class GoodsController {
	
	@Autowired
	private GoodsService service; //=new GoodsServiceProcess(); 탑다운으로 만들고
	
	//ajax요청이지만 응답페이지를 다른페이지로 처리: 응답을 html로 처리
	@GetMapping("/common/categories")
	public String categories(Model model) {
		service.categoryList(model);
		return "goods/category";
	}
	//sub카테고리
	@GetMapping("/common/categories/{no}")
	public String categories(@PathVariable long no, Model model) {
		service.categoryList(no, model);
		return "goods/category";
	}
	
	@GetMapping("/admin/goods")
	public String goods() {
		return "goods/reg";
	}
	
	@ResponseBody //응답데이터를 json타입으로 리턴합니다.
	@PostMapping("/admin/temp-upload")
	public Map<String, String> tempUpload(MultipartFile gimg) {
		return service.fileTempUpload(gimg);
	}
	
}
