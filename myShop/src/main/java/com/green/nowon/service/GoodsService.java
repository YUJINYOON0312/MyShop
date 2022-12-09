package com.green.nowon.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface GoodsService {

	Map<String, String> fileTempUpload(MultipartFile gimg);

}
