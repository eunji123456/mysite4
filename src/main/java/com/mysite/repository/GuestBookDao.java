package com.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
		@Autowired
		SqlSession sqlSession;
		
		public List<GuestBookVo> getList() {
			return sqlSession.selectList("guestbook.getList");
		}
		
		public int insert(GuestBookVo guestBookVo) {
			return sqlSession.insert("guestbook.insert", guestBookVo);
		}
		
		public int delete(int no, String password) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("no", no);
			map.put("password", password);
			
			return sqlSession.delete("guestbook.delete", map);
		}
		
		public int insertVo(GuestBookVo guestBookVo) {
			System.out.println("insertVo : "+ guestBookVo.toString()); /*처음의 vo 안에는 no가 없다.*/ 
			sqlSession.insert("guestbook.insertNo", guestBookVo);		 /*select key를가진 insert를 할때 no 에 값을 넣어줬으므로 가지고있음 */ 
			System.out.println("insertVo1 : "+ guestBookVo.toString());/*찍어보면 no 안에 값이 잇을것 */
			
			return guestBookVo.getNo();
		}
		
		public GuestBookVo selectVo(int no) {
			GuestBookVo vo1= sqlSession.selectOne("guestbook.selectbyNo",no);
			System.out.println("셀렉트한  vo1 : "+vo1.toString());
			return vo1;
		}
	}
