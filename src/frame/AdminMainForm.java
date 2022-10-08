package frame;
import javax.swing.*;

import components.User;
import visible.VisibleTrue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainForm extends JFrame {
    JLabel userName;
    JMenuBar menuBar;
    JMenu menuProfile, menuManage;
    JMenuItem editProfile, exit, manageGame;
    JDesktopPane desktopPane;
    User user;

    public AdminMainForm(User user){
    	this.user = user;
    	AdminMainForm frame = this;
    	
        // user name
        userName = new JLabel();
        userName.setFont(new Font("Calibri",Font.PLAIN,25));
        userName.setText("Welcome to GameIn!, " + user.getUsername() + ".");

        // create the menu bar
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(255,255,204));

        // create the sub menu
        menuProfile = new JMenu("Profile");
        menuManage = new JMenu("Manage");

        // create the submenu items
        editProfile = new JMenuItem("Edit Profile");
        exit = new JMenuItem("Exit");
        manageGame = new JMenuItem("Manage Game");
        manageGame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		userName.setVisible(false);
        		VisibleTrue.showManageGameForm(frame, user);
        	}
        });

        // add items to menu
        addToMenu();

        desktopPane = new JDesktopPane();
        desktopPane.setLayout(new BorderLayout());
        desktopPane.setBackground(new Color(255,255,204));
        getContentPane().add(desktopPane);


        editProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	userName.setVisible(false);
            	VisibleTrue.showUpdateProfileForm(frame, user);
            }
        });

        // program terminated.
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isConfirmed("Are you sure want to exit?")) {
                    System.exit(0);
                }
            }
        });

        // add menu to frame
        getContentPane().add(userName);
        this.setJMenuBar(menuBar);

        runGUI();
    }
    void runGUI(){
        this.pack();
        this.setTitle("GameIn!");
        this.getContentPane().setBackground(new Color(255,255,204));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,550); // w = 699 h = 365
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    void addToMenu(){
        menuProfile.add(editProfile);
        menuProfile.add(exit);

        menuManage.add(manageGame);

        menuBar.add(menuProfile);
        menuBar.add(menuManage);
    }

    public boolean isConfirmed(String message) {
        int option = JOptionPane.showConfirmDialog(null, message, "Yes", JOptionPane.YES_NO_OPTION);
        if (option == 0) return true;
        return false;
    }

}

