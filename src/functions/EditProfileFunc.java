package functions;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import access.UserInterface;
import database.ConnectDB;
import components.User;
import frame.EditProfileSubMenu;

public class EditProfileFunc {
    // update profile
	public void updateProfile(EditProfileSubMenu view, User user) {
        String userName = view.getTxtUserName().getText();
        String email = view.getTxtEmail().getText();
        String gender = view.getBtnMale().isSelected() ? "male" : "female";
        String address = view.getTxtAddress().getText();
        String phone = view.getTxtPhone().getText();

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
        }else if(address.equals("")) {
            JOptionPane.showMessageDialog(view, "Address must fill ");
        }else if(!address.endsWith(" Street")){
            JOptionPane.showMessageDialog(view,"Address must ends with \'Street\' ");
        }else {
            try{
            	UserInterface userInterface  = ConnectDB.getUsers();
                user.setUsername(userName);
                user.setEmail(email);
                user.setGender(gender);
                user.setAddress(address);
                user.setPhone(phone);
            	
                if (isConfirmed("Are you sure want to update profile?")) {
                	userInterface.updateUser(user);
                    JOptionPane.showMessageDialog(view,"Successfully Updated");
                }
                
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
	}
	
	public boolean isConfirmed(String message) {
		int option = JOptionPane.showConfirmDialog(null, message, "Yes", JOptionPane.YES_NO_OPTION);
		if (option == 0) return true;
		return false;
	}
}
