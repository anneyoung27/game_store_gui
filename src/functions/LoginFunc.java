package functions;

import javax.swing.JOptionPane;

import access.UserInterface;
import database.ConnectDB;
import components.User;
import visible.VisibleTrue;
import frame.LoginForm;

public class LoginFunc {
	public void login(LoginForm view) {

		String email = view.getEmailField().getText();
		String password = String.valueOf(view.getPasswordField().getPassword());
        
        if (email.equals("")) {
            JOptionPane.showMessageDialog(view, "Please fill email columns");
        } else if (password.equals("")) {
            JOptionPane.showMessageDialog(view, "Please fill password columns");
        } else {
        	try {
        		UserInterface userInterface = ConnectDB.getUsers();
        		User user = userInterface.findByEmailAndPassword(email, password, view);

        		if (user == null) {
        			JOptionPane.showMessageDialog(view, "Wrong email or password!");
					return;
				}
        		
        		// Navigate to main form
                if (user.getRole().toLowerCase().equals("admin")) {
                	VisibleTrue.navigateToAdminMainForm(view, user);
                } else {
                	VisibleTrue.navigateToCustomerMainForm(view, user);
                }
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
	}
}
