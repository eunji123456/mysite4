package com.mysite.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	
	public String  restore(MultipartFile file){
		//D:\JavaStudy\사진
		
		
		String saved="D:\\JavaStudy\\file\\";
		
		String orgfileName = file.getOriginalFilename();
		System.out.println("orgfileName :"+orgfileName);
		
		
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				//마지막 .을 기준으로 찾는다 (확장자를 찾기위해서 )
		System.out.println("exName:"+exName);
		
		
		
		long filesize= file.getSize();
		System.out.println("filesize :"+filesize);
	
;
		
		
		//저장이름 
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		System.out.println(saveName);
		
		//저장해줄 패스 
		String filepath=saved+saveName;
		System.out.println("파일 패스 : "+filepath);
		
		
		

		//파일 카피 (디렉토리에 복사 )
		try {
			byte[] filegetbite = file.getBytes();
			
			OutputStream out = new FileOutputStream(filepath);
			BufferedOutputStream bout =new BufferedOutputStream(out);
			
			bout.write(filegetbite);
			bout.flush();
			if(bout!= null) {
				bout.close();
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return saveName;
	}
}
