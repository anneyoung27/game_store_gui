package frame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import functions.RegisterFunc;

public class RegisterForm extends JFrame implements ActionListener, MouseListener {
	RegisterFunc registerFunc;

    JLabel labelId, labelUsername, labelPassword, labelRole, labelEmail,
            labelPhone, labelAddress, labelGender, labelTitle, labelSignIn;
    JTextField textId, textUsername, textEmail, textPhone;
    JTextArea address;
    JPasswordField password;
    JRadioButton radioMale, radioFemale;
    ButtonGroup groupGender;
    JButton buttonRegister;
    JPanel panelForm, panelGender, panelGender2, panelTitle, panelBottom, panelId, panelId2, panelUserName, panelUserName2,
            panelEmail, panelEmail2, panelPhone, panelPhone2, panelAddress, panelAddress2, panelPassword, panelPassword2,
            panelRole, panelRole2, panelButton;
    JComboBox <String> roles;
    
    public JTextField getTextId() {
		return textId;
	}

	public JTextField getTextUsername() {
		return textUsername;
	}

	public JTextField getTextEmail() {
		return textEmail;
	}

	public JTextField getTextPhone() {
		return textPhone;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public JComboBox<String> getRoles() {
		return roles;
	}

	public JTextArea getAddress() {
		return address;
	}

	public ButtonGroup getGroupGender() {
		return groupGender;
	}

	public JRadioButton getRadioMale() {
		return radioMale;
	}

	public RegisterForm() {
		registerFunc = new RegisterFunc();
        initComponent();
        try {
			textId.setText(registerFunc.getNewId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
        textId.setEditable(false); // menonaktifkan text field.
        runWindow();

        getContentPane().add(panelTitle, BorderLayout.NORTH);
        getContentPane().add(panelForm, BorderLayout.CENTER);
        getContentPane().add(panelBottom, BorderLayout.SOUTH);
    }

    void runWindow(){
        this.getContentPane().setBackground(new Color(255,255,204));
        this.setTitle("Register Form");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(550,720);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    // initialize semua component
    void initComponent() {
        // Id panel label
        panelId2 = new JPanel(new FlowLayout(FlowLayout.LEFT,35,10));
        labelId = new JLabel("ID");
        panelId2.add(labelId);
        panelId2.setBackground(new Color(255,255,204));

        panelUserName = new JPanel(new FlowLayout(FlowLayout.LEFT,35,10));
        labelUsername = new JLabel("User Name");
        panelUserName.add(labelUsername);
        panelUserName.setBackground(new Color(255,255,204));

        panelEmail = new JPanel(new FlowLayout(FlowLayout.LEFT,35,10));
        labelEmail = new JLabel("Email");
        panelEmail.add(labelEmail);
        panelEmail.setBackground(new Color(255,255,204));

        panelPhone = new JPanel(new FlowLayout(FlowLayout.LEFT,35,10));
        labelPhone = new JLabel("Phone");
        panelPhone.add(labelPhone);
        panelPhone.setBackground(new Color(255,255,204));

        panelAddress = new JPanel(new FlowLayout(FlowLayout.LEFT,35,25));
        labelAddress = new JLabel("Address");
        panelAddress.add(labelAddress);
        panelAddress.setBackground(new Color(255,255,204));

        panelPassword = new JPanel(new FlowLayout(FlowLayout.LEFT,35,19));
        labelPassword = new JLabel("Password");
        panelPassword.add(labelPassword);
        panelPassword.setBackground(new Color(255,255,204));

        panelGender = new JPanel(new FlowLayout(FlowLayout.LEFT,35,10));
        labelGender = new JLabel("Gender");
        panelGender.add(labelGender);
        panelGender.setBackground(new Color(255,255,204));

        panelRole = new JPanel(new FlowLayout(FlowLayout.LEFT,35,10));
        labelRole = new JLabel("Role");
        panelRole.add(labelRole);
        panelRole.setBackground(new Color(255,255,204));

        labelSignIn = new JLabel("Sign In");
        labelSignIn.setBorder(new EmptyBorder(0,0,11,0));

        // Id Panel txt
        panelId = new JPanel();
        textId = new JTextField();
        textId.setPreferredSize(new Dimension(245,35));
        panelId.add(textId);
        panelId.setBackground(new Color(255,255,204));

        // User Name panel txt
        panelUserName2 = new JPanel();
        textUsername = new JTextField();
        textUsername.setPreferredSize(new Dimension(245,35));
        panelUserName2.add(textUsername);
        panelUserName2.setBackground(new Color(255,255,204));

        // email panel txt
        panelEmail2 = new JPanel();
        textEmail = new JTextField();
        textEmail.setPreferredSize(new Dimension(245,35));
        panelEmail2.add(textEmail);
        panelEmail2.setBackground(new Color(255,255,204));

        // phone panel txt
        panelPhone2 = new JPanel();
        textPhone = new JTextField();
        textPhone.setPreferredSize(new Dimension(245,35));
        panelPhone2.add(textPhone);
        panelPhone2.setBackground(new Color(255,255,204));

        // panel password txt
        panelPassword2 = new JPanel();
        password = new JPasswordField();
        password.setPreferredSize(new Dimension(245,35));
        panelPassword2.add(password);
        panelPassword2.setBackground(new Color(255,255,204));

        // panel address txt
        panelAddress2 = new JPanel();
        address = new JTextArea();
        address.setPreferredSize(new Dimension(245,100));
        panelAddress2.add(address);
        panelAddress2.setBackground(new Color(255,255,204));
        address.setLineWrap(true);
        address.setWrapStyleWord(true);

        radioFemale = new JRadioButton("Female");
        radioFemale.setBackground(new Color(255,255,204));
        radioMale = new JRadioButton("Male");
        radioMale.setSelected(true);
        radioMale.setBackground(new Color(255,255,204));
        radioMale.setBorder(new EmptyBorder(0,70,0,20));

        // radio button harus sepaket dengan button group
        groupGender = new ButtonGroup();
        groupGender.add(radioFemale);
        groupGender.add(radioMale);

        panelGender2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelGender2.setBackground(new Color(255,255,204));
        panelGender2.add(radioFemale);
        panelGender2.add(radioMale);

        panelRole2 = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
        String roleChoice[] = {"Admin", "Customer"};
        roles = new JComboBox(roleChoice);
        roles.setPreferredSize(new Dimension(245,30));
        panelRole2.add(roles);
        panelRole2.setBackground(new Color(255,255,204));


        panelBottom = new JPanel();
        panelBottom.setLayout(new GridLayout(2,3));
        panelBottom.setBackground(new Color(255,255,204));
        panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
        buttonRegister = new JButton("Register");
        buttonRegister.setPreferredSize(new Dimension(80,25));
        buttonRegister.setBorder(BorderFactory.createEtchedBorder());
        buttonRegister.addActionListener(this); // action listener.
        panelButton.add(buttonRegister);
        panelButton.setBackground(new Color(255,255,204));

        labelTitle = new JLabel("Register Form");
        labelTitle.setFont(new Font("Calibri", Font.PLAIN,35));
        labelTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);

        panelTitle = new JPanel(new GridLayout(1, 1));
        panelTitle.add(labelTitle);

        panelForm = new JPanel(new GridLayout(8, 1));
        panelForm.add(panelId2);
        panelForm.add(panelId);
        panelForm.add(panelUserName);
        panelForm.add(panelUserName2);
        panelForm.add(panelEmail);
        panelForm.add(panelEmail2);
        panelForm.add(panelPhone);
        panelForm.add(panelPhone2);
        panelForm.add(panelAddress);
        panelForm.add(panelAddress2);
        panelForm.add(panelPassword);
        panelForm.add(panelPassword2);
        panelForm.add(panelGender);
        panelForm.add(panelGender2); // berisi radioMale dan radioFemale
        panelForm.add(panelRole);
        panelForm.add(panelRole2);

        panelBottom.add(panelButton);
        panelBottom.add(labelSignIn);
        labelSignIn.setHorizontalAlignment((int) CENTER_ALIGNMENT);


        panelTitle.setBackground(new Color(255,255,204));
        panelGender2.setBackground(new Color(255,255,204));
        panelForm.setBackground(new Color(255,255,204));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getComponent().equals(labelSignIn)){
            this.dispose();
            LoginForm log = new LoginForm();
            log.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getComponent().equals(labelSignIn)){
            this.dispose();
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonRegister){
            registerFunc.register(this);
        }
    }
}
