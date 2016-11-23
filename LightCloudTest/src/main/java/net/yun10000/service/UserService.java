package net.yun10000.service;

import net.yun10000.dao.UserDao;
import net.yun10000.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User getUser(Integer id){
		if(id==null){
			return null;
		}
		return userDao.getUser(id);
	}

}
