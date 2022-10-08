package frame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import functions.EditProfileFunc;
import components.User;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditProfileSubMenu extends JInternalFrame {
    JLabel updateProfTitle, userName, userEmail, userPhone, userAddress, userGender;
    JTextField txtUserName, txtEmail, txtPhone;
    JTextArea txtAddress;
    JRadioButton btnMale, btnFemale;
    ButtonGroup btnGroup;
    JButton btnUpdateProfile;
    JPanel upperPanel, centerPanel, bottomPanel, genderPanel, genderPanel2, userNamePanel, userNamePanel2,
            userEmailPanel, userEmailPanel2, userPhonePanel, userPhonePanel2, userAddressPanel, userAddressPanel2,
            buttonUpdatePanel;
    User user;
    EditProfileFunc editProfileFunc;

    public JTextField getTxtUserName() {
		return txtUserName;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JTextField getTxtPhone() {
		return txtPhone;
	}

	public JTextArea getTxtAddress() {
		return txtAddress;
	}

	public JRadioButton getBtnMale() {
		return btnMale;
	}

	public EditProfileSubMenu(User user){
        super("",true,true,true,true);
        this.user = user;
        editProfileFunc = new EditProfileFunc();
        panelPerform();
        initValue();
        getContentPane().add(upperPanel, BorderLayout.NORTH);
        getContentPane().add(centerPanel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }

    private void initValue() {
    	boolean isMale = user.getGender().toLowerCase().equals("male") ? true : false;
    	txtUserName.setText(user.getUsername());
    	txtEmail.setText(user.getEmail());
    	txtPhone.setText(user.getPhone());
    	txtAddress.setText(user.getAddress());
    	btnMale.setSelected(isMale);
    	btnFemale.setSelected(!isMale);
	}

	void panelPerform(){
		EditProfileSubMenu frame = this;
		
        // initialize panel
        upperPanel = new JPanel(new GridLayout(1, 1));
        centerPanel = new JPanel(new GridLayout(5,3,0,-10));
        bottomPanel = new JPanel(new GridLayout(1,3));

        // initialize component
        updateProfTitle = new JLabel("Update Profile");
        updateProfTitle.setFont(new Font("Calibri", Font.BOLD,15));
        updateProfTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        updateProfTitle.setPreferredSize(new Dimension(0,70));

        // User Name
        userNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,15,15));
        userName = new JLabel("Username");
        userNamePanel.setBackground(new Color(255,255,204));
        userNamePanel.add(userName);

        // User Email
        userEmailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,15,15));
        userEmail = new JLabel("Email");
        userEmailPanel.setBackground(new Color(255,255,204));
        userEmailPanel.add(userEmail);

        // User Phone
        userPhonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,15,15));
        userPhone = new JLabel("Phone");
        userPhonePanel.setBackground(new Color(255,255,204));
        userPhonePanel.add(userPhone);

        // User Address
        userAddressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15,30));
        userAddress = new JLabel("User Address");
        userAddressPanel.setBackground(new Color(255,255,204));
        userAddressPanel.add(userAddress);

        // Gender
        genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,15,30));
        userGender = new JLabel("User Gender");
        genderPanel.setBackground(new Color(255,255,204));
        genderPanel.add(userGender);

        // UserName TextField
        userNamePanel2 = new JPanel();
        txtUserName = new JTextField();
        txtUserName.setPreferredSize(new Dimension(350,40));
        userNamePanel2.setBackground(new Color(255,255,204));
        userNamePanel2.add(txtUserName);

        // UserEmail TextField
        userEmailPanel2 = new JPanel();
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(350,40));
        userEmailPanel2.setBackground(new Color(255,255,204));
        userEmailPanel2.add(txtEmail);

        // UserPhone TextField
        userPhonePanel2 = new JPanel();
        txtPhone = new JTextField();
        txtPhone.setPreferredSize(new Dimension(350,40));
        userPhonePanel2.setBackground(new Color(255,255,204));
        userPhonePanel2.add(txtPhone);

        // UserAddress TextArea
        userAddressPanel2 = new JPanel();
        txtAddress = new JTextArea();
        txtAddress.setPreferredSize(new Dimension(350,90));
        userAddressPanel2.setBackground(new Color(255,255,204));
        userAddressPanel2.add(txtAddress);

        // Gender (Radio Button).
        btnMale = new JRadioButton("Male");
        btnMale.setBackground(new Color(255,255,204));
        btnFemale = new JRadioButton("Female");
        btnFemale.setBackground(new Color(255,255,204));
        btnFemale.setBorder(new EmptyBorder(0,150,0,20));

        // Button Update
        buttonUpdatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnUpdateProfile = new JButton("Update Profile");

        btnUpdateProfile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		editProfileFunc.updateProfile(frame, user);
        	}
        });

        btnUpdateProfile.setPreferredSize(new Dimension(500,40));
        buttonUpdatePanel.setBorder(new EmptyBorder(0,0,10,0));
        buttonUpdatePanel.setBackground(new Color(255,255,204));
        buttonUpdatePanel.add(btnUpdateProfile);

        // Gender
        btnGroup = new ButtonGroup();
        btnGroup.add(btnMale);
        btnGroup.add(btnFemale);
        genderPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT,0,30));
        genderPanel2.setBackground(new Color(255,255,204));
        genderPanel2.add(btnMale);
        genderPanel2.setBorder(new EmptyBorder(0,58,0,115));
        genderPanel2.add(btnFemale);

        // adding component to panel
        upperPanel.add(updateProfTitle);

        centerPanel.add(userNamePanel);
        centerPanel.add(userNamePanel2);
        centerPanel.add(userEmailPanel);
        centerPanel.add(userEmailPanel2);
        centerPanel.add(userPhonePanel);
        centerPanel.add(userPhonePanel2);
        centerPanel.add(userAddressPanel);
        centerPanel.add(userAddressPanel2);
        centerPanel.add(genderPanel);
        centerPanel.add(genderPanel2);

        bottomPanel.add(buttonUpdatePanel);

        upperPanel.setBackground(new Color(255,255,204));
        centerPanel.setBackground(new Color(255,255,204));
        bottomPanel.setBackground(new Color(255,255,204));

    }

}
