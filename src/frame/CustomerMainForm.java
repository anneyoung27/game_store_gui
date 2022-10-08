package frame;
import javax.swing.*;

import components.User;
import visible.VisibleTrue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerMainForm extends JFrame {
    JLabel userName;
    JMenuBar menuBar;
    JMenu menuProfile, menuTransaction;
    JMenuItem editProfile, exit, buyGame, viewTransactionHistory;
    JDesktopPane desktopPane;
    User user;

    public CustomerMainForm(User user){
    	this.user = user;
    	CustomerMainForm frame = this;
    	
        // label
        userName = new JLabel();
        userName.setFont(new Font("Calibri",Font.PLAIN,25));
        userName.setText("Welcome to GameIn!, " + user.getUsername() + ".");

        // create the menu bar
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(255,255,204));

        // create the sub menu
        menuProfile = new JMenu("Profile");
        menuBar.add(menuProfile);
        menuTransaction = new JMenu("Transaction");
        menuBar.add(menuTransaction);

        // create the submenu items
        editProfile = new JMenuItem("Edit Profile");
        menuProfile.add(editProfile);
        exit = new JMenuItem("Exit");
        menuProfile.add(exit);
        buyGame = new JMenuItem("Buy Game");
        menuTransaction.add(buyGame);
        viewTransactionHistory = new JMenuItem("View Transaction History");
        menuTransaction.add(viewTransactionHistory);

        desktopPane = new JDesktopPane();
        desktopPane.setLayout(new BorderLayout());
        desktopPane.setBackground(new Color(255,255,204));
        this.getContentPane().add(desktopPane);

        // move to internal frame

        // Edit Profile Internal Frame
        editProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // untuk Edit Profile Customer class
            	userName.setVisible(false);
            	VisibleTrue.showUpdateProfileForm(frame, user);
            }
        });

        // buy game internal frame
        buyGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // untuk beli game
            	userName.setVisible(false);
                VisibleTrue.showBuyGameForm(frame, user);
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

        // view transaction history
        viewTransactionHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	userName.setVisible(false);
                VisibleTrue.showTransactionHistory(frame, user);
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
        this.setResizable(true);
    }

    public boolean isConfirmed(String message) {
        int option = JOptionPane.showConfirmDialog(null, message, "Yes", JOptionPane.YES_NO_OPTION);
        if (option == 0) return true;
        return false;
    }

}
