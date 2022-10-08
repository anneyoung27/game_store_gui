package access;

import java.util.List;

import components.User;
import frame.LoginForm;

public interface UserInterface {
	public List<User> getAll();

    public void insertUser(User user);

    public void updateUser(User user);

    public void deleteUser(String id);

    public User findById(String id);

    public User findByUsername(String username);
    
    public User findByEmailAndPassword(String email, String password, LoginForm views);
    
    public int countUser();
    
    public int countByUsername(String username);
    
    public int countByEmail(String email);
}