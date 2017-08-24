package com.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.service.GuestBookService;
import com.mysite.vo.GuestBookVo;

@Controller
public class GuestbookController {
	@Autowired
	GuestBookService guestbookservice;

	
	@RequestMapping("/gb/list")
	public String list(Model model) {
		List<GuestBookVo> list = guestbookservice.getList();
		model.addAttribute("list", list);
		return "/guestbook/list";
	}

	@RequestMapping(value = "gb/add", method = RequestMethod.POST)
	public String insert(@ModelAttribute GuestBookVo guestBookVo) {
		guestbookservice.insert(guestBookVo);
		return "redirect:/gb/list";
	}

		@RequestMapping(value = "gb/deleteform")
	public String deleteform(@RequestParam("no") int no, Model model) {
		model.addAttribute("no", no);
		return "/guestbook/deleteform";
	}

		@RequestMapping(value = "gb/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		guestbookservice.delete(no, password);
		return "redirect:/gb/list";
	}
		
		@RequestMapping(value = "gb/list-ajax", method = RequestMethod.GET)
		public String listAjax() {
			return"/guestbook/list-ajax";
		}
		

}
