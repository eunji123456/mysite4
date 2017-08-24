package com.mysite.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.service.GuestBookService;
import com.mysite.vo.GuestBookVo;

@Controller
@RequestMapping(value="api/gb")
public class ApiGusetBookController {
	
	@Autowired
	GuestBookService guestbookservice;
	
	@ResponseBody//이 어노테이션을 붙임으로써 리턴하는것이 페이지가 아니라 순수한 데이터임을 알려준다. 이데이터를 바디에 실어서 보낸다 를 알려쥬ㅜㅁ 
	@RequestMapping(value ="/list",method=RequestMethod.POST)
	public List<GuestBookVo>  list() {
		System.out.println("리스트 뿌려주기 성공 ");
		List<GuestBookVo> list =guestbookservice.getList();
		return list;
	}
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public GuestBookVo  add (@RequestBody GuestBookVo guestbookvo){
		System.out.println("성공99 "+guestbookvo);
		 guestbookvo=guestbookservice.write(guestbookvo);
		//원래 이렇게한후에 다시 리스트를 불러오는 화면으로 넘겼는데 . 지금은 
		//저장후에 > 데이터를 가져와서 넘겨줘야 페이지에 뿌려ㅓ줄수 있겠지 
		 return guestbookvo;
	}
	
}
