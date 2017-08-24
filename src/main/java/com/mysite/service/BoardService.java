package com.mysite.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.BoardDao;
import com.mysite.vo.BoardVo;


@Service
public class BoardService {
	
	@Autowired
	BoardDao dao;
	
	public List<BoardVo> getlist() {
		return dao.getlist(); 
		}
	
	public int insert(BoardVo vo) {
		return dao.insert(vo); 
		}
	
	public int delete(int no) {
		return dao.delete(no);
	}

	public BoardVo selectbyno(int no,int userno) {
		BoardVo vo = dao.selectbyno(no);
		if(vo.getUserNo()!=userno) {//userno=세션에서 받아온 아이(로그인한 사람의 유저넘버) ,vo 안에잇는애 : 글쓴이의  유저넘버 
			
			dao.updatehit(no);
			
		}else {
			return dao.selectbyno(no);}
		
		return vo;
	}
	
public int update(BoardVo vo) {
		
		return dao.update(vo);
	}
}
