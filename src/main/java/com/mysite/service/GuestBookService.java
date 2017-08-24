package com.mysite.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.repository.GuestBookDao;
import com.mysite.vo.GuestBookVo;

@Repository
public class GuestBookService {

	@Autowired
	GuestBookDao guestbookDao;

	
		public List<GuestBookVo> getList() {
			return guestbookDao.getList();
		}
		
		public int insert(GuestBookVo guestBookVo) {
			String content = guestBookVo.getContent().replace("\r\n", "<br/>");
			guestBookVo.setContent(content);
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(cal.getTime());
			
			guestBookVo.setRegDate(date);
			
			return guestbookDao.insert(guestBookVo);
		}
		
			public int delete(int no, String password) {
			return guestbookDao.delete(no, password);
		}
			
			
		
			public GuestBookVo write (GuestBookVo guestBookVo) {
				int no = guestbookDao.insertVo(guestBookVo); //얘의 결과값을 no 로 받았음 (select key해서)
				
				System.out.println("on 의 숫자 -service : "+no);
				return guestbookDao.selectVo(no);
			}	
	}