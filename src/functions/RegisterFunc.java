package functions;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import access.UserInterface;
import database.ConnectDB;
import components.User;
import visible.VisibleTrue;
import frame.RegisterForm;

public class RegisterFunc {
	public void register(RegisterForm view){
        String id = view.getTextId().getText();
        String userName = view.getTextUsername().getText();
        String email = view.getTextEmail().getText();
        String _password = String.valueOf(view.getPassword().getPassword());
        String gender = view.getRadioMale().isSelected() ? "male" : "female";
        String _address = view.getAddress().getText();
        String phone = view.getTextPhone().getText();
        String role = (String) view.getRoles().getSelectedItem();

        if(userName.length() < 5 || userName.length() > 30){
            JOptionPane.showMessageDialog(view, "User Name must between 5 until 30 characters");
        }else if(!email.contains("@")) {
            JOptionPane.showMessageDialog(view, "Email must contain @");
        }else if(!email.contains(".")){
            JOptionPane.showMessageDialog(view,"Email must contain .");
        }else if(email.startsWith(".@")) {
            JOptionPane.showMessageDialog(view, "Email must not starts with .@");
        }else if(email.contains("@.")) {
            JOptionPane.showMessageDialog(view, "Email must not starts with @.");
        }else if(phone.equals("")) {
        	JOptionPane.showMessageDialog(view, "Phone must fill ");
        }else if(phone.length() < 12) {
        	JOptionPane.showMessageDialog(view, "Phone must more than equals 12 digits ");
        }else if(_address.equals("")) {
            JOptionPane.showMessageDialog(view, "Address must fill ");
        }else if(!_address.endsWith(" Street")){
            JOptionPane.showMessageDialog(view,"Address must ends with \'Street\' ");
        }else if(_password.length() < 5 || _password.length() > 30){
            JOptionPane.showMessageDialog(view,"Password must 5 - 30 length of character.");
        } else {
            try{
            	UserInterface userInterface = ConnectDB.getUsers();
            	User user = new User();
                user.setId(id);
                user.setUsername(userName);
                user.setEmail(email);
                user.setPassword(_password);
                user.setGender(gender);
                user.setAddress(_address);
                user.setPhone(phone);
                user.setRole(role);
                
                if (!validateUniqueUsername(userName)) {
            		JOptionPane.showMessageDialog(view, "Username is already taken, please use another username!");
            		return;
            	}
            	
            	if (!validateUniqueEmail(email)) {
            		JOptionPane.showMessageDialog(view, "Email is already registered, please use another email!");
            		return;
            	}
                
            	userInterface.insertUser(user);
                JOptionPane.showMessageDialog(view,"Successfully Register");
                
                // to main form
                if (user.getRole().toLowerCase().equals("admin")) {
                	VisibleTrue.navigateToAdminMainForm(view, user);
                } else {
                	VisibleTrue.navigateToCustomerMainForm(view, user);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
	
	public String getNewId() throws SQLException {
		UserInterface userInterface = ConnectDB.getUsers();
		int lastId = userInterface.countUser();
		int newId = lastId + 1;
    	return newId < 10 ? "US00" + newId : newId < 100 ? "US0" + newId : String.valueOf(newId);
	}
	
	private boolean validateUniqueUsername(String username) {
		try {
			UserInterface userInterface = ConnectDB.getUsers();
			int count = userInterface.countByUsername(username);
			if (count < 1) return true;
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return false;
	}
	
	private boolean validateUniqueEmail(String email) {
		try {
			UserInterface userInterface = ConnectDB.getUsers();
			int count = userInterface.countByEmail(email);
			if (count < 1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
