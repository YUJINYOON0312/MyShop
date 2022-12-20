package com.green.nowon.service;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface GoodsService {

	Map<String, String> fileTempUpload(MultipartFile gimg);

	void categoryList(Model model);

	void categoryList(long no, Model model); //컨트롤러

}
