package noteApp.config.repository;

import noteApp.config.bean.User;

public interface UserDao {
	public boolean createUser(User user);
	public boolean editUser(User user);
	public User getUserByUsername(String username);
		
}
