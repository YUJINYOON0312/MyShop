package com.green.nowon.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

public class MyFileUtils {

	public static Map<String, String> fileUpload(MultipartFile gimg, String location) {
		ClassPathResource cpr=new ClassPathResource("static"+location);
		File folder=null;
		String fileName=null;
		try {
			folder=cpr.getFile();
			String orgName=gimg.getOriginalFilename();
			
			int idx=orgName.lastIndexOf("."); //파일이름중에서 마지막(.)이 인덱스 번호
		
			fileName=orgName.substring(0,idx)
					+"_"+(System.nanoTime()/1000000)
					+orgName.substring(idx); //.+확장자
			//fileName=UUID.randomUUID().toString()+orgName.substring(idx);
			
			//원본이름_15844258.jpg
			gimg.transferTo(new File(folder, fileName));
			
			System.out.println("임시폴더에 파일업로드"+location+fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, String> tempfile=new HashMap<>();
		tempfile.put("location", location);
		tempfile.put("fileName", fileName);
		tempfile.put("url", location+fileName);
		
		return tempfile;
	}
	
	private MyFileUtils() {}

}
