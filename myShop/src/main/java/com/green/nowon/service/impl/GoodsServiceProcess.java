package com.green.nowon.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.entity.CategoryRepository;
import com.green.nowon.service.GoodsService;
import com.green.nowon.utils.MyFileUtils;

@Service
public class GoodsServiceProcess implements GoodsService {

	@Value("${file.location.temp}")
	private String locationTemp;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Map<String, String> fileTempUpload(MultipartFile gimg) {
		
		return 	MyFileUtils.fileUpload(gimg, locationTemp);
	}

	@Override
	public void categoryList(Model model) {
		//1차 카테고리만 읽어오기
		model.addAttribute("list",
				categoryRepository.findAllByParentNoIsNull());
		
	}

	//3차카테고리를 읽어
	@Override
	public void categoryList(long no, Model model) {
		model.addAttribute("list",
				categoryRepository.findAllByParentNo(no));
	}

}
