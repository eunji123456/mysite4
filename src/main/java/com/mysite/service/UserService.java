package com.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.UserDao;
import com.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userdao;
	
	public int join(UserVo uservo) {
		return userdao.insert(uservo);
	}
	
	
	public UserVo getUser(String email, String password) {
		return userdao.getUser(email,password);
	}
	
	public UserVo getUser(int no) {
		return userdao.getUser(no);
	}
	
	public int UpdateUser(UserVo vo) {
		
		return userdao.UpdateUser(vo);
	}
	
	public String joincheck(String email) {
		String emailcheck=userdao.joincheck(email);
		System.out.println("디비에 갔다온emailchek:"+emailcheck);
		if(emailcheck==null) {
			return emailcheck = "useful";
			
		}
		else
		return "you can't use this id  ";
	}
}
