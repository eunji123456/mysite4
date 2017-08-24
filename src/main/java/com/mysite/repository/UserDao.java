package com.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlsession;
	
	public int insert(UserVo uservo) {
		return sqlsession.insert("user.insert",uservo);
		
	}
	
	public UserVo getUser(String email,String password) {
		Map<String,Object> usermap =new HashMap<String,Object>();
		
		usermap.put("email", email);
		usermap.put("password",password);
		
		UserVo uservo = sqlsession.selectOne("user.selectUserByEmailPw",usermap);
		System.out.println("uservo:"+uservo.toString());
		return uservo;
	}
	
	
	public UserVo getUser(int no) {
		
		UserVo uservo=sqlsession.selectOne("user.selectUserbyno",no);
		
		return uservo;
		
	}
	
	public int UpdateUser(UserVo vo) {
		
		 return  sqlsession.update("user.update", vo);
		  
		 
		
	}
	public String joincheck(String email) {
		
		return sqlsession.selectOne("user.joincheck", email);
	}
}
