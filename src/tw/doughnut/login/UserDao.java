package tw.doughnut.login;

import java.util.List;

public interface UserDao {

	boolean login(User user);
	
	int insert(User user);
	
	List<User> getAll();

}