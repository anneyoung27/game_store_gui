package implement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.JDBC4ResultSet;
import com.mysql.jdbc.PreparedStatement;

import access.UserInterface;
import components.User;
import frame.LoginForm;

public class UserImpl implements UserInterface {
	private Connection connection;

	// database query
	private String insertUser = "INSERT INTO users (UserID, UserName, UserEmail, UserPassword, UserDOB, UserGender, UserAddress, UserPhone, UserRole) VALUES(?,?,?,?,?,?,?,?,?)";
	private String updateUser = "UPDATE users SET UserName = ?, UserEmail = ?, UserPhone = ?, UserAddress = ?, UserGender = ? WHERE UserID = ?";
	private String findByEmail = "SELECT * FROM users WHERE UserEmail = ?";
	private String findByUsername = "SELECT * FROM users WHERE UserName = ?";
	private String findByEmailAndPassword = "SELECT * FROM users WHERE UserEmail = ? AND UserPassword = ?";
	private String countUser = "SELECT COUNT(*) FROM users";
	private String countByUsername = "SELECT COUNT(*) FROM users WHERE UserName = ?";
	private String countByEmail = "SELECT COUNT(*) FROM users WHERE UserEmail = ?";

	public UserImpl(Connection connection) {
        this.connection = connection;
	}

	@Override
	public List<User> getAll() {
		return null;
	}

	@Override
	public void insertUser(User user) {
		PreparedStatement statement = null;
        try {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            statement = (PreparedStatement) connection.prepareStatement(insertUser);
            statement.setString(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, formatter.format(new Date()));
            statement.setString(6, user.getGender());
            statement.setString(7, user.getAddress());
            statement.setString(8, user.getPhone());
            statement.setString(9, user.getRole());
            statement.executeUpdate(); 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
	}

	@Override
	public void updateUser(User user) {
		PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(updateUser);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getGender());
            statement.setString(6, user.getId());
            statement.executeUpdate(); 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
	}

	@Override
	public void deleteUser(String id) {
	}

	@Override
	public User findById(String id) {
		return null;
	}

	@Override
	public User findByUsername(String username) {
		return null;
	}

    // Reference : http://tutorials.jenkov.com/jdbc/resultset.html
	@Override
	public User findByEmailAndPassword(String email, String password, LoginForm view) {
		PreparedStatement statement = null;
        User user = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(findByEmailAndPassword);
            statement.setString(1, email);
            statement.setString(2, password);
            JDBC4ResultSet rs = (JDBC4ResultSet) statement.executeQuery();
            while (rs.next()) {
                String uId = rs.getString("UserID");
                String uUsername = rs.getString("UserName");
                String uEmail = rs.getString("UserEmail");
                String uPassword = rs.getString("UserPassword");
                Date uDateOfBirth = rs.getDate("UserDOB");
                String uGender = rs.getString("UserGender");
                String uAddress = rs.getString("UserAddress");
                String uPhoneNumber = rs.getString("UserPhone");
                String uRole = rs.getString("UserRole");
                user = new User(uId, uUsername, uEmail, uPassword, uDateOfBirth, uGender, uAddress, uPhoneNumber, uRole);
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
		return null;
	}

	@Override
	public int countUser() {
		PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(countUser);
            JDBC4ResultSet rs = (JDBC4ResultSet) statement.executeQuery();
            while (rs.next()) {
            	return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
	}

	@Override
	public int countByUsername(String username) {
		PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(countByUsername);
            statement.setString(1, username);
            JDBC4ResultSet rs = (JDBC4ResultSet) statement.executeQuery();
            while (rs.next()) {
            	return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
	}
	
	@Override
	public int countByEmail(String email) {
		PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(countByEmail);
            statement.setString(1, email);
            JDBC4ResultSet rs = (JDBC4ResultSet) statement.executeQuery();
            while (rs.next()) {
            	return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
	}
}
