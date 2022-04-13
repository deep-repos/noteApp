package noteApp.config.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import noteApp.config.bean.User;

@Repository
public class UserDaoImp implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public boolean createUser(User user) {
		try {
			String sql="insert into user (username,password) values (?,?) ";
			jdbcTemplate.update(sql,user.getUsername(),user.getPassword());
			return true;
		}catch(Exception e ) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean editUser(User user) {
		try {
			String sql="update user set password = ? where username = ?";
			jdbcTemplate.update(sql,user.getPassword(),user.getUsername());
			return true;
		}catch(Exception e ) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public User getUserByUsername(String username) {
		try {
			
			String sql ="select username,password from user where username = ?";
			User user = jdbcTemplate.queryForObject(sql,new  BeanPropertyRowMapper<User>(User.class),username);
			return user;
		}catch(Exception e ) {
			e.printStackTrace();
			return null;
		}
		
		
	}

}
