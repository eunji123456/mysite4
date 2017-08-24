package com.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.mysite.service.BoardService;
import com.mysite.vo.BoardVo;
import com.mysite.vo.UserVo;

@Controller
public class BoardController {

@Autowired
	private BoardService boardservice;
	
	@RequestMapping("/bd/list") //	
	public String list(Model model) {
		List<BoardVo> list = boardservice.getlist();
		model.addAttribute("list",list);
		return"board/list";}
	
	@RequestMapping("/bd/writeform")
	public String writeform() {
		return"board/writeform"; }
	
	@RequestMapping(value="/bd/wirte",method=RequestMethod.POST)
	public String write(@ModelAttribute BoardVo vo ,HttpSession session) {//@RequestParam("title")String title, @RequestParam("content")String content
		UserVo authUser= (UserVo)session.getAttribute("authUser");
		int no=authUser.getNo();
		
		vo.setUserNo(no);
		boardservice.insert(vo);
		
		return "redirect:/bd/list";}
	
	@RequestMapping("/bd/delete")
	public String deleteform(@RequestParam("no") int no) {
		
		boardservice.delete(no);
		return "redirect:/bd/list";
	}
	@RequestMapping("/bd/read")
	public String read(@RequestParam("no") int no,Model model ,HttpSession session) {
		int userno=0;//아니면 그냥 0으로 넘긴다 . 
		UserVo authUser= (UserVo)session.getAttribute("authUser");
		if(authUser!=null) {//세션에서 받아온 어스유저가 널이아니면 (정보가있으면 )
			userno=authUser.getNo();} //유저넘버를 넣고 
		
		BoardVo boardVo =boardservice.selectbyno(no,userno);//클릭한 글의 번호와,  판단한 유저번호를 넣어서 서비스로넘김 
		boardVo.setNo(no);
		model.addAttribute("boardVo",boardVo);
		System.out.println("리드 : "+boardVo);
		return "board/read";
	}
	@RequestMapping("/bd/modifyform")
	public String modifyform(@ModelAttribute BoardVo vo) {
		
		System.out.println("모디파이폼 :"+vo.toString());
		return "board/modifyform";
	}
	
	@RequestMapping("/bd/modify")
	public String modify(@ModelAttribute BoardVo vo) {
		System.out.println("모디파이: "+vo.toString());
		boardservice.update(vo);
		System.out.println("업데이트 돌아옴 ");
		return "redirect:/bd/list";
	}
}
