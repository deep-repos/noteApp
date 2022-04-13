package noteApp.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noteApp.config.bean.User;
import noteApp.config.repository.UserDao;


@Service
public class UserService {
		
	@Autowired
	UserDao userDao;
	
	public boolean createUser(User user) {
		return userDao.createUser(user);
	}
	public boolean editUser(User user) {
		return userDao.editUser(user);
	}
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}
		
	
}
