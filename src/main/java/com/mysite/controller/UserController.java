package com.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.service.UserService;
import com.mysite.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	private UserService userservice;
	
	
	@RequestMapping(value="/user/joinform",method=RequestMethod.GET)
	public String joinform() {
		return "user/joinform";
	}
	
	@RequestMapping(value="/user/join",method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo uservo) {
		System.out.println(uservo.toString());
		
		userservice.join(uservo);
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/user/loginform",method=RequestMethod.GET)
	public String loginform() {
		return "user/loginform";
	}

	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password")String password ,HttpSession session) {
		
		UserVo authUser= userservice.getUser(email,password);
		
		if (authUser!= null) {
		session.setAttribute("authUser",authUser);
		return "redirect:/main";
		}else {
			return "redirect:/user/loginform?result=fail";
		}
	}
	
	@RequestMapping(value="/user/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		return"redirect:/main";
	}
	
	@RequestMapping(value="/user/modifyform",method=RequestMethod.GET)
	public String modifyform(HttpSession session,Model model) {
		UserVo authUser= (UserVo)session.getAttribute("authUser");
		int no=authUser.getNo();
		userservice.getUser(no);
		
		UserVo uservo=userservice.getUser(no);
		model.addAttribute("userVo",uservo);
		System.out.println("마지막으로 온 vo"+uservo.toString());
		return"user/modifyform";
	}
	@RequestMapping(value="/user/modify",method=RequestMethod.POST)
	public String modify(@ModelAttribute UserVo vo,HttpSession session) {
		UserVo authUser =(UserVo)session.getAttribute("authUser");
		int no =authUser.getNo();
		vo.setNo(no);
		
	
		userservice.UpdateUser(vo);
		

		//session.setAttribute("authUser", vo);
		authUser.setName(vo.getName());
		
		return"redirect:/main";
	}
	@ResponseBody
	@RequestMapping(value="/user/joincheck",method=RequestMethod.POST)
	public String joincheck(@RequestParam("email") String email) {
		System.out.println("컨트롤러에 넘어온 이메일 :"+email);
		String emailcheck=userservice.joincheck(email);
		System.out.println(emailcheck);
		return emailcheck;
	}
	
	
}
