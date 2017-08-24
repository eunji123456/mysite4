package com.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> getlist() {
		System.out.println("겟리스트  다오 ");
		return sqlSession.selectList("board.getlist");
		
	}
	
	public int insert(BoardVo vo){
		System.out.println("다오 vo 값 :ㅣ"+vo.toString());
		return sqlSession.insert("board.insert", vo);
		
	}
	public int delete(int no){
		
		return sqlSession.delete("board.delete",no);
		
	}
	public BoardVo selectbyno(int no){
		System.out.println(no);
		return sqlSession.selectOne("board.selectOne", no);
		
	}
	public int updatehit(int no){
		System.out.println(no);
		return sqlSession.update("board.updatehit", no);
		
	}
	
	
	public int update(BoardVo vo ){
		System.out.println(vo);
		return sqlSession.update("board.update", vo);
	}
	
	
	
}
