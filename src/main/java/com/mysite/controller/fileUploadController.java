package com.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.service.FileUploadService;

@Controller
public class fileUploadController {

	
	@Autowired FileUploadService fupservice;
	
	@RequestMapping("fileupload/form")
	public String form () {
		return "fileupload/form";
	}
	
	@RequestMapping("fileupload/upload")
	public String upload (@RequestParam("email")String email ,@RequestParam ("file") MultipartFile file,Model model) {
		System.out.println("email : "+email);
		System.out.println("file : "+file);
		
		
		String savename= fupservice.restore(file);
		model.addAttribute("savename",savename);
		System.out.print(savename);
		return "fileupload/result";
	}
}
