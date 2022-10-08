package visible;

import javax.swing.JFrame;

import components.User;
import frame.AdminMainForm;
import frame.BuyGameFormSubMenu;
import frame.CustomerMainForm;
import frame.EditProfileSubMenu;
import frame.ManageGameFormSubMenu;
import frame.RegisterForm;
import frame.TransactionFormSubMenu;

public class VisibleTrue {
	public static void navigateToRegisterForm(JFrame view) {
		RegisterForm page = new RegisterForm();
		page.setLocationRelativeTo(null);
		page.setVisible(true);
		view.setVisible(false);
	}
	
	public static void navigateToAdminMainForm(JFrame view, User user) {
		AdminMainForm page = new AdminMainForm(user);
		page.setLocationRelativeTo(null);
		page.setVisible(true);
		view.setVisible(false);
	}
	
	public static void navigateToCustomerMainForm(JFrame view, User user) {
		CustomerMainForm page = new CustomerMainForm(user);
		page.setLocationRelativeTo(null);
		page.setVisible(true);
		view.setVisible(false);
	}
	
	public static void showUpdateProfileForm(JFrame view, User user) {
		EditProfileSubMenu editProfileSubMenu = new EditProfileSubMenu(user);
		view.add(editProfileSubMenu);
        editProfileSubMenu.setVisible(true);
        editProfileSubMenu.show();
	}
	
	public static void showBuyGameForm(JFrame view, User user) {
		BuyGameFormSubMenu buyGameFormSubMenu = new BuyGameFormSubMenu(user);
		view.add(buyGameFormSubMenu);
	    buyGameFormSubMenu.setVisible(true);
	    buyGameFormSubMenu.show();
	}
	
	public static void showTransactionHistory(JFrame view, User user) {
		TransactionFormSubMenu transactionFormSubMenu = new TransactionFormSubMenu(user);
		view.add(transactionFormSubMenu);
		transactionFormSubMenu.setVisible(true);
	    transactionFormSubMenu.show();
	}
	
	public static void showManageGameForm(JFrame view, User user) {
		ManageGameFormSubMenu transactionFormSubMenu = new ManageGameFormSubMenu();
		view.add(transactionFormSubMenu);
		transactionFormSubMenu.setVisible(true);
	    transactionFormSubMenu.show();
	}
}
