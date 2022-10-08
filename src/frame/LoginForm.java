package frame;
import javax.swing.*;

import functions.LoginFunc;
import visible.VisibleTrue;

import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame implements ActionListener, MouseListener{
	LoginFunc loginFunc;
    JPanel newPanel;
    JLabel lblEmail,lblPassword,lblSignUp;
    JTextField txtEmail;
    JPasswordField inPassword;
    JButton btnLogin;

    RegisterForm registerForm;
    private JTextField emailField;
    private JPasswordField passwordField;

    public JTextField getEmailField() {
		return emailField;
	}

    public JPasswordField getPasswordField() {
		return passwordField;
	}


    public LoginForm() {
		LoginForm frame = this;
		loginFunc = new LoginFunc();
		
        // set up
        newPanel = new JPanel();
        lblEmail = new JLabel("Email ");
        lblEmail.setBounds(120, 126, 38, 16);
        lblPassword = new JLabel("Password ");
        txtEmail = new JTextField(20);
        inPassword = new JPasswordField(20);
        btnLogin = new JButton("Login");
        lblSignUp = new JLabel("Sign Up Here");
        newPanel.setLayout(null);
        newPanel.add(lblEmail);
        btnLogin.setPreferredSize(new Dimension(60,25));
        btnLogin.setBorder(BorderFactory.createEtchedBorder());
        btnLogin.addActionListener(this);
        lblSignUp.addMouseListener(this);
//        newPanel.add(lblSignUp, constraints);

        // set border for the panel
//        newPanel.setBorder(BorderFactory.createTitledBorder(
//                BorderFactory.createEtchedBorder(),
//                "Login Form",2,0,new Font("Calibri", Font.PLAIN,45)));
        newPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Login Form",
                2,2,new Font("Calibri", Font.PLAIN,45)));

        // background color
        newPanel.setBackground(new Color(255,255,204));

        // add the panel to this frame
        getContentPane().add(newPanel);
        
        emailField = new JTextField();
        emailField.setBounds(350, 120, 247, 26);
        newPanel.add(emailField);
        emailField.setColumns(10);
        
        JLabel lblPassword_1 = new JLabel("Password");
        lblPassword_1.setBounds(120, 175, 61, 16);
        newPanel.add(lblPassword_1);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(350, 170, 247, 26);
        newPanel.add(passwordField);
        
        JButton btnLogin_1 = new JButton("Login");
        btnLogin_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		loginFunc.login(frame);
        	}
        });

        btnLogin_1.setBounds(290, 219, 117, 29);
        newPanel.add(btnLogin_1);
        
        JLabel lblNewLabel = new JLabel("Sign Up Here");
        lblNewLabel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		VisibleTrue.navigateToRegisterForm(frame);
        	}
        });

        lblNewLabel.setBounds(300, 260, 81, 16);
        newPanel.add(lblNewLabel);
        runGUI();
    }

    void runGUI(){
//        this.pack();
        this.setTitle("Login Form");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(699,325); // w = 699 h = 365
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    // open register
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getComponent().equals(lblSignUp)){
            this.dispose();
            registerForm = new RegisterForm();
            registerForm.setVisible(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
