package frame;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import functions.BuyGameFunc;
import components.Game;
import components.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuyGameFormSubMenu extends JInternalFrame {
    JLabel labelGameId, labelGameName, labelGameType, labelGamePrice, labelGameStock, labelGameQuantity;
    // read only
    JTextField txtGameId, txtGameName, txtGameType, txtGamePrice, txtGameStock;
    JButton btnAddToCard, btnRemoveSelectedCart, btnClearCart, btnCheckOut;
    DefaultTableModel dtmHeader, dtmHeaderBottom;
    JTable tableUpper, tableBottom;
    JSpinner spinGameQuantity;
    JScrollPane scrollPaneUpper, scrollPaneBottom;
    JPanel jPanelUpper, jPanelCenter, jPanelBottom, jPanelGameId, jPanelGameId2, jPanelGameName, jPanelGameName2,
            jPanelGameType, jPanelGameType2, jPanelGamePrice, jPanelGamePrice2, jPanelStock, jPanelStock2,
            jPanelQuantity, jPanelQuantity2, jPanelButtonAddCart, jPanelButtonRemove, jPanelButtonClear,
            jPanelButtonCheckOut, jPanelTableUpper, jPanelTableBottom;
    List<Game> games = new ArrayList<>();
    BuyGameFunc buyGameFunc;
    public User user;

    public JTextField getTxtGameId() {
		return txtGameId;
	}

	public JTextField getTxtGameName() {
		return txtGameName;
	}

	public JTextField getTxtGameType() {
		return txtGameType;
	}

	public JTextField getTxtGamePrice() {
		return txtGamePrice;
	}

	public JTextField getTxtGameStock() {
		return txtGameStock;
	}

	public JSpinner getSpinGameQuantity() {
		return spinGameQuantity;
	}

	public BuyGameFormSubMenu(User user){
        super("",true,true,true,true);
        
        buyGameFunc = new BuyGameFunc();
        this.user = user;
        panelPerform();
        refreshTables();
        
        this.getContentPane().add(jPanelUpper, BorderLayout.NORTH);
        this.getContentPane().add(jPanelCenter, BorderLayout.CENTER);
        getContentPane().setLayout(new GridLayout(5,3));
        getContentPane().add(jPanelButtonAddCart);
        this.getContentPane().add(jPanelTableBottom);
        this.getContentPane().add(jPanelBottom, BorderLayout.SOUTH);
    }
    
    public void refreshTables() {
		refreshTableUpper();
		refreshTableBottom();
	}

	public void refreshTableUpper() {
		games = buyGameFunc.getAllGames();
		dtmHeader.setRowCount(0);
		for (Game game : games) {
			Object[] row = new Object[5];
			row[0] = game.getId();
	        row[1] = game.getName();
	        row[2] = game.getType();
	        row[3] = game.getPrice();
	        row[4] = game.getStock();
	        dtmHeader.addRow(row);
		}
	}
	
	public void refreshTableBottom() {
		Map<String, Integer> cart = buyGameFunc.cart;
		dtmHeaderBottom.setRowCount(0);
		for (Game game : games) {
			Integer value = cart.get(game.getId());
			if (value != null) {
				Object[] row = new Object[7];
				row[0] = game.getId();
		        row[1] = game.getName();
		        row[2] = game.getType();
		        row[3] = game.getPrice();
		        row[4] = game.getStock();
		        row[5] = cart.get(game.getId());
		        row[6] = cart.get(game.getId()) * game.getPrice();
		        dtmHeaderBottom.addRow(row);
			}
		}
	}


    void panelPerform(){
    	BuyGameFormSubMenu frame = this;
    	
        // set up panel
        jPanelUpper = new JPanel(new GridLayout(1,1));
        jPanelUpper.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Buy Game",
                TitledBorder.CENTER,TitledBorder.TOP));
        jPanelCenter = new JPanel(new GridLayout(3,4));
        jPanelBottom = new JPanel(new GridLayout(1,3));

        // initialization
        Vector<Object> tableHeader, tableDataUpper;
        Vector<Object> tableHeaderBottom, tableDataBottom;

        // Game Id
        jPanelGameId = new JPanel(new FlowLayout(FlowLayout.LEFT,15,15));
        labelGameId = new JLabel("Game ID");
        jPanelGameId.setBackground(new Color(255,255,204));
        jPanelGameId.add(labelGameId);

        // Game Name
        jPanelGameName = new JPanel(new FlowLayout(FlowLayout.LEFT, 15,15));
        labelGameName = new JLabel("Game Name");
        jPanelGameName.setBackground(new Color(255,255,204));
        jPanelGameName.add(labelGameName);

        // Game Type
        jPanelGameType = new JPanel(new FlowLayout(FlowLayout.LEFT, 15,15));
        labelGameType = new JLabel("Game Type");
        jPanelGameType.setBackground(new Color(255,255,204));
        jPanelGameType.add(labelGameType);

        // Game Price
        jPanelGamePrice = new JPanel(new FlowLayout(FlowLayout.LEFT, 15,15));
        labelGamePrice = new JLabel("Game Price");
        jPanelGamePrice.setBackground(new Color(255,255,204));
        jPanelGamePrice.add(labelGamePrice);

        // Game Stock
        jPanelStock = new JPanel(new FlowLayout(FlowLayout.LEFT, 15,15));
        labelGameStock = new JLabel("Game Stock");
        jPanelStock.setBackground(new Color(255,255,204));
        jPanelStock.add(labelGameStock);

        // Game Quantity
        jPanelQuantity = new JPanel(new FlowLayout(FlowLayout.LEFT, 15,15));
        labelGameQuantity = new JLabel("Game Quantity");
        jPanelQuantity.setBackground(new Color(255,255,204));
        jPanelQuantity.add(labelGameQuantity);

        // game id txt
        jPanelGameId2 = new JPanel();
        txtGameId = new JTextField();
        txtGameId.setPreferredSize(new Dimension(200,25));
        txtGameId.setEditable(false);
        jPanelGameId2.setBackground(new Color(255,255,204));
        jPanelGameId2.add(txtGameId);

        // game name txt
        jPanelGameName2 = new JPanel();
        txtGameName = new JTextField();
        txtGameName.setEditable(false);
        txtGameName.setPreferredSize(new Dimension(200,25));
        jPanelGameName2.setBackground(new Color(255,255,204));
        jPanelGameName2.add(txtGameName);

        // game type txt
        jPanelGameType2 = new JPanel();
        txtGameType = new JTextField();
        txtGameType.setEditable(false);
        txtGameType.setPreferredSize(new Dimension(200,25));
        jPanelGameType2.setBackground(new Color(255,255,204));
        jPanelGameType2.add(txtGameType);

        // game price txt
        jPanelGamePrice2 = new JPanel();
        txtGamePrice = new JTextField();
        txtGamePrice.setPreferredSize(new Dimension(200,25));
        txtGamePrice.setEditable(false);
        jPanelGamePrice2.setBackground(new Color(255,255,204));
        jPanelGamePrice2.add(txtGamePrice);

        // game stock txt
        jPanelStock2 = new JPanel();
        txtGameStock = new JTextField();
        txtGameStock.setPreferredSize(new Dimension(200,25));
        txtGameStock.setEditable(false);
        jPanelStock2.setBackground(new Color(255,255,204));
        jPanelStock2.add(txtGameStock);

        // game quantity spinner (konekin ke database, soalnya butuh banyaknya kuantitas dari stock)
        jPanelQuantity2 = new JPanel();
        spinGameQuantity = new JSpinner();
        spinGameQuantity.setPreferredSize(new Dimension(200,25));
        jPanelQuantity2.setBackground(new Color(255,255,204));
        jPanelQuantity2.add(spinGameQuantity);

        // button
        jPanelButtonAddCart = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAddToCard = new JButton("Add to Cart");

        btnAddToCard.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		buyGameFunc.addItem(frame);
        	}
        });

        btnAddToCard.setPreferredSize(new Dimension(150,40));
        btnAddToCard.setBorder(BorderFactory.createEtchedBorder());
        jPanelButtonAddCart.setBackground(new Color(255,255,204));
        jPanelButtonAddCart.add(btnAddToCard);

        jPanelButtonRemove = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnRemoveSelectedCart = new JButton("Remove Selected Cart");

        btnRemoveSelectedCart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int i = tableBottom.getSelectedRow();
        		if (i > -1) {
        			Game game = games.get(i);
            		buyGameFunc.removeItem(frame, game.getId());
        		}
        	}
        });

        btnRemoveSelectedCart.setPreferredSize(new Dimension(310,25));
        btnRemoveSelectedCart.setBorder(BorderFactory.createEtchedBorder());
        jPanelButtonRemove.setBackground(new Color(255,255,204));
        jPanelButtonRemove.add(btnRemoveSelectedCart);

        jPanelButtonClear = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnClearCart = new JButton("Clear Cart");
        btnClearCart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		buyGameFunc.resetCart(frame);
        	}
        });
        btnClearCart.setPreferredSize(new Dimension(310,25));
        btnClearCart.setBorder(BorderFactory.createEtchedBorder());
        jPanelButtonClear.setBackground(new Color(255,255,204));
        jPanelButtonClear.add(btnClearCart);

        jPanelButtonCheckOut = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnCheckOut = new JButton("Checkout");
        btnCheckOut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		buyGameFunc.checkOut(frame);
        	}
        });
        btnCheckOut.setPreferredSize(new Dimension(310,25));
        btnCheckOut.setBorder(BorderFactory.createEtchedBorder());
        jPanelButtonCheckOut.setBackground(new Color(255,255,204));
        jPanelButtonCheckOut.add(btnCheckOut);


        // JTable Upper
        tableHeader = new Vector<>();
        tableHeader.add("Game Id");
        tableHeader.add("Game Name");
        tableHeader.add("Game Type");
        tableHeader.add("Game Price");
        tableHeader.add("Game Stock");

        dtmHeader = new DefaultTableModel(tableHeader, 0);

        // set up data table atas (load data pakai database)
        jPanelTableUpper = new JPanel(new GridLayout(1,1));
        tableUpper = new JTable(dtmHeader);
        tableUpper.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int i = tableUpper.getSelectedRow();
        		if (i > -1) {
        			Game game = games.get(i);
        			if (game.getStock() > 0) {
        				txtGameId.setText(game.getId());
            			txtGameName.setText(game.getName());
            			txtGameType.setText(game.getType());
            			txtGamePrice.setText(String.valueOf(game.getPrice()));
            			txtGameStock.setText(String.valueOf(game.getStock()));
        			}
				}
        	}
        });

        scrollPaneUpper = new JScrollPane(tableUpper);
        jPanelTableUpper.add(scrollPaneUpper);

        // JTable Bottom
        tableHeaderBottom= new Vector<>();
        tableHeaderBottom.add("Game Id");
        tableHeaderBottom.add("Game Name");
        tableHeaderBottom.add("Game Type");
        tableHeaderBottom.add("Game Price");
        tableHeaderBottom.add("Game Stock");
        tableHeaderBottom.add("Game Quantity");
        tableHeaderBottom.add("Sub Total");

        dtmHeaderBottom = new DefaultTableModel(tableHeaderBottom, 0 );

        // set up data Table Bawah
        jPanelTableBottom = new JPanel(new GridLayout(1,1));
        tableBottom = new JTable(dtmHeaderBottom);
        scrollPaneBottom = new JScrollPane(tableBottom);
        jPanelTableBottom.add(scrollPaneBottom);

        // add components to panel
        jPanelUpper.add(jPanelTableUpper);

        jPanelCenter.add(jPanelGameId);
        jPanelCenter.add(jPanelGameId2);
        jPanelCenter.add(jPanelGamePrice);
        jPanelCenter.add(jPanelGamePrice2);
        jPanelCenter.add(jPanelGameName);
        jPanelCenter.add(jPanelGameName2);
        jPanelCenter.add(jPanelStock);
        jPanelCenter.add(jPanelStock2);
        jPanelCenter.add(jPanelGameType);
        jPanelCenter.add(jPanelGameType2);
        jPanelCenter.add(jPanelQuantity);
        jPanelCenter.add(jPanelQuantity2);

        jPanelBottom.add(jPanelButtonRemove);
        jPanelBottom.add(jPanelButtonClear);
        jPanelBottom.add(jPanelButtonCheckOut);

        jPanelUpper.setBackground(new Color(255,255,204));
        jPanelCenter.setBackground(new Color(255,255,204));
        jPanelBottom.setBackground(new Color(255,255,204));

    }
}
