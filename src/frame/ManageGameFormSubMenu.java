package frame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import functions.ManageGameFunc;
import components.Game;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageGameFormSubMenu extends JInternalFrame {
    JLabel labelNewGameId, labelNewGameName, labelNewGameType, labelNewGamePrice, labelNewGameStock, labelGameId,
            labelGameName, labelGameType, labelGamePrice, labelGameStock, labelAddStock;
    JTable jGameTable;
    DefaultTableModel dtm;
    JScrollPane jGameTableContainer;
    JTextField txtGameId, txtGameName, txtGamePrice, txtGameStock, txtNewGameId, txtNewGameName, txtNewGamePrice;
    JComboBox<String> jComGameType, jComNewGameType;
    JSpinner inAddStock, inNewGameStock;
    JButton btnUpdateGame, btnDeleteGame, btnAddStock, btnInsertGame, btnReset;
    JPanel jPanelUpper, jPanelCenter, jPanelBottom, jPanelGameTable, jPanelNewGameId, jPanelNewGameId2, jPanelNewGameName,
            jPanelNewGameName2, jPanelNewGameType, jPanelNewGameType2, jPanelGameId, jPanelGameId2, jPanelGameName,
            jPanelGameName2, jPanelGameType, jPanelGameType2, jPanelNewGamePrice, jPanelNewGamePrice2, jPanelNewGameStock,
            jPanelNewGameStock2, jPanelGamePrice, jPanelGamePrice2, jPanelGameStock, jPanelGameStock2, jPanelBtnInsert,
            jPanelBtnUpdate, jPanelBtnDelete, jPanelButtons, jPanelAddStock, jPanelAddStock2, jPanelBtnReset,
            jPanelBtnAddStock, jPanelButtons2;
    ManageGameFunc manageGameFunc;
    List<Game> games = new ArrayList<>();

    public JTextField getTxtGameId() {
		return txtGameId;
	}

	public JTextField getTxtGameName() {
		return txtGameName;
	}

	public JTextField getTxtGamePrice() {
		return txtGamePrice;
	}

	public JTextField getTxtGameStock() {
		return txtGameStock;
	}

	public JTextField getTxtNewGameId() {
		return txtNewGameId;
	}

	public JTextField getTxtNewGameName() {
		return txtNewGameName;
	}

	public JTextField getTxtNewGamePrice() {
		return txtNewGamePrice;
	}

	public JComboBox<String> getjComGameType() {
		return jComGameType;
	}

	public JComboBox<String> getjComNewGameType() {
		return jComNewGameType;
	}

	public JSpinner getInAddStock() {
		return inAddStock;
	}

	public JSpinner getInNewGameStock() {
		return inNewGameStock;
	}

	public ManageGameFormSubMenu(){
        super("",true,true,true,true);
        manageGameFunc = new ManageGameFunc();
        panelPerform();
        initValue();
        this.getContentPane().add(jPanelUpper, BorderLayout.NORTH);
        this.getContentPane().add(jPanelCenter, BorderLayout.CENTER);
        this.getContentPane().add(jPanelBottom, BorderLayout.SOUTH);
        getContentPane().setLayout(new GridLayout(3,3));
    }
	
	private void initValue() {
		initId();
		refreshTable();
	}

	public void refreshTable() {
		games = manageGameFunc.getAllGames();
		dtm.setRowCount(0);
		for (Game game : games) {
			Object[] row = new Object[5];
			row[0] = game.getId();
	        row[1] = game.getName();
	        row[2] = game.getType();
	        row[3] = game.getPrice();
	        row[4] = game.getStock();
	        dtm.addRow(row);
		}
	}
    
    private void initId() {
    	try {
			txtNewGameId.setText(manageGameFunc.getNewId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	void panelPerform(){
		ManageGameFormSubMenu frame = this;
		
        // set up panel
        jPanelUpper = new JPanel(new GridLayout(1,1));
        jPanelUpper.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Manage Game",
                TitledBorder.CENTER, TitledBorder.TOP));
        jPanelCenter = new JPanel(new GridLayout(5,4));
        jPanelBottom = new JPanel(new GridLayout(2,3));

        // initialize component
        Vector<Object> tableGameHeader, tableData;

        // table
        tableGameHeader = new Vector<>();

        tableGameHeader.add("Game Id");
        tableGameHeader.add("Game Name");
        tableGameHeader.add("Game Type");
        tableGameHeader.add("Game Price");
        tableGameHeader.add("Game Stock");

        dtm = new DefaultTableModel(tableGameHeader, 0);

        // // set up data table atas (load data pakai database)
        jPanelGameTable = new JPanel(new GridLayout(1,1));
        jGameTable = new JTable(dtm);
        jGameTable.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int i = jGameTable.getSelectedRow();
        		if (i > -1) {
        			Game game = games.get(i);
        			txtGameId.setText(game.getId());
        			txtGameName.setText(game.getName());
        			jComGameType.setSelectedItem(game.getType());
        			txtGamePrice.setText(String.valueOf(game.getPrice()));
        			txtGameStock.setText(String.valueOf(game.getStock()));
				}
        	}
        });

        jGameTableContainer = new JScrollPane(jGameTable);
        jPanelGameTable.add(jGameTableContainer);

        // new game id
        jPanelNewGameId = new JPanel(new FlowLayout(FlowLayout.LEFT,5,15));
        labelNewGameId = new JLabel("New Game Id");
        jPanelNewGameId.setBackground(new Color(255,255,204));
        jPanelNewGameId.add(labelNewGameId);

        // new game name
        jPanelNewGameName = new JPanel(new FlowLayout(FlowLayout.LEFT,5,15));
        labelNewGameName = new JLabel("New Game Name");
        jPanelNewGameName.setBackground(new Color(255,255,204));
        jPanelNewGameName.add(labelNewGameName);

        // new game type
        jPanelNewGameType = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 15));
        labelNewGameType = new JLabel("New Game Type");
        jPanelNewGameType.setBackground(new Color(255,255,204));
        jPanelNewGameType.add(labelNewGameType);

        // game id
        jPanelGameId = new JPanel(new FlowLayout(FlowLayout.LEFT,5,15));
        labelGameId = new JLabel("Game ID");
        jPanelGameId.setBackground(new Color(255,255,204));
        jPanelGameId.add(labelGameId);

        // game name
        jPanelGameName = new JPanel(new FlowLayout(FlowLayout.LEFT,5,15));
        labelGameName = new JLabel("Game Name");
        jPanelGameName.setBackground(new Color(255,255,204));
        jPanelGameName.add(labelGameName);

        // game type
        jPanelGameType = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 15));
        labelGameType = new JLabel("Game Type");
        jPanelGameType.setBackground(new Color(255,255,204));
        jPanelGameType.add(labelGameType);

        // new game price
        jPanelNewGamePrice = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 15));
        labelNewGamePrice = new JLabel("New Game Price");
        jPanelNewGamePrice.setBackground(new Color(255,255,204));
        jPanelNewGamePrice.add(labelNewGamePrice);

        // new game stock
        jPanelNewGameStock = new JPanel(new FlowLayout(FlowLayout.LEFT,5,15));
        labelNewGameStock = new JLabel("New Game Stock");
        jPanelNewGameStock.setBackground(new Color(255,255,204));
        jPanelNewGameStock.add(labelNewGameStock);

        // game price
        jPanelGamePrice = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,15));
        labelGamePrice = new JLabel("Game Price");
        jPanelGamePrice.setBackground(new Color(255,255,204));
        jPanelGamePrice.add(labelGamePrice);

        // game stock
        jPanelGameStock = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,15));
        labelGameStock = new JLabel("Game Stock");
        jPanelGameStock.setBackground(new Color(255,255,204));
        jPanelGameStock.add(labelGameStock);

        // add stock
        jPanelAddStock = new JPanel();
        labelAddStock = new JLabel("Add Stock");
        labelAddStock.setBorder(new EmptyBorder(5,0,0,0));
        jPanelAddStock.setBackground(new Color(255,255,204));
        jPanelAddStock.add(labelAddStock);

        // new game id txt
        jPanelNewGameId2 = new JPanel();
        txtNewGameId = new JTextField();
        txtNewGameId.setEditable(false);
        txtNewGameId.setPreferredSize(new Dimension(200,30));
        jPanelNewGameId2.setBackground(new Color(255,255,204));
        jPanelNewGameId2.add(txtNewGameId);

        // new game name txt
        jPanelNewGameName2 = new JPanel();
        txtNewGameName = new JTextField();
        txtNewGameName.setPreferredSize(new Dimension(200,30));
        jPanelNewGameName2.setBackground(new Color(255,255,204));
        jPanelNewGameName2.add(txtNewGameName);

        // game id txt
        jPanelGameId2 = new JPanel();
        txtGameId = new JTextField();
        txtGameId.setPreferredSize(new Dimension(200,30));
        txtGameId.setEditable(false);
        jPanelGameId2.setBackground(new Color(255,255,204));
        jPanelGameId2.add(txtGameId);

        // game name txt
        jPanelGameName2 = new JPanel();
        txtGameName = new JTextField();
        txtGameName.setPreferredSize(new Dimension(200,35));
        jPanelGameName2.setBackground(new Color(255,255,204));
        jPanelGameName2.add(txtGameName);

        // new game type combo box
        jPanelNewGameType2 = new JPanel();
        String [] gameType = {"TPS", "MOBA", "FPS", "RPG"};
        jComNewGameType = new JComboBox(gameType);
        jComNewGameType.setPreferredSize(new Dimension(200,30));
        jPanelNewGameType2.setBackground(new Color(255,255,204));
        jPanelNewGameType2.add(jComNewGameType);

        // game type combo box
        jPanelGameType2 = new JPanel();
        jComGameType = new JComboBox(gameType);
        jComGameType.setPreferredSize(new Dimension(200,30));
        jPanelGameType2.setBackground(new Color(255,255,204));
        jPanelGameType2.add(jComGameType);

        // new game price txt
        jPanelNewGamePrice2 = new JPanel();
        txtNewGamePrice = new JTextField();
        txtNewGamePrice.setPreferredSize(new Dimension(200,30));
        jPanelNewGamePrice2.setBackground(new Color(255,255,204));
        jPanelNewGamePrice2.add(txtNewGamePrice);

        // game price txt
        jPanelGamePrice2 = new JPanel();
        txtGamePrice = new JTextField();
        txtGamePrice.setPreferredSize(new Dimension(200,30));
        jPanelGamePrice2.setBackground(new Color(255,255,204));
        jPanelGamePrice2.add(txtGamePrice);

        // new game stock spinner
        jPanelNewGameStock2 = new JPanel();
        inNewGameStock = new JSpinner();
        inNewGameStock.setPreferredSize(new Dimension(200,30));
        jPanelNewGameStock2.setBackground(new Color(255,255,204));
        jPanelNewGameStock2.add(inNewGameStock);

        // game stock txt
        jPanelGameStock2 = new JPanel();
        txtGameStock = new JTextField();
        txtGameStock.setPreferredSize(new Dimension(200,30));
        txtGameStock.setEditable(false);
        jPanelGameStock2.setBackground(new Color(255,255,204));
        jPanelGameStock2.add(txtGameStock);

        // button insert
        jPanelButtons = new JPanel(new GridLayout(1,1)); // Hold 3 buttons

        jPanelBtnInsert = new JPanel();
        btnInsertGame = new JButton("Insert Game");
        btnInsertGame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		manageGameFunc.insertGame(frame);
        	}
        });

        btnInsertGame.setPreferredSize(new Dimension(300,35));
        jPanelBtnInsert.setBackground(new Color(255,255,204));
        jPanelBtnInsert.add(btnInsertGame);

        // button update game
        jPanelBtnUpdate = new JPanel();
        btnUpdateGame = new JButton("Update Game");
        btnUpdateGame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		manageGameFunc.updateGame(frame);
        	}
        });
        btnUpdateGame.setPreferredSize(new Dimension(300,35));
        jPanelBtnUpdate.setBackground(new Color(255,255,204));
        jPanelBtnUpdate.add(btnUpdateGame);

        // button delete game
        jPanelBtnDelete = new JPanel();
        btnDeleteGame = new JButton("Delete Game");
        btnDeleteGame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		manageGameFunc.deleteGame(frame);
        	}
        });
        btnDeleteGame.setPreferredSize(new Dimension(300,35));
        jPanelBtnDelete.setBackground(new Color(255,255,204));
        jPanelBtnDelete.add(btnDeleteGame);

        // button reset
        jPanelButtons2 = new JPanel(new GridLayout(1,4)); // Hold 4 buttons

        jPanelBtnReset = new JPanel(new FlowLayout(FlowLayout.CENTER,25,0));
        btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		resetNewGameInput();
        		resetGameInput();
        		refreshTable();
        	}
        });

        btnReset.setPreferredSize(new Dimension(215,35));
        jPanelBtnReset.setBackground(new Color(255,255,204));
        jPanelBtnReset.add(btnReset);

        // spinner add stock
        jPanelAddStock2 = new JPanel();
        inAddStock = new JSpinner();
        inAddStock.setPreferredSize(new Dimension(235,35));
        jPanelAddStock2.setBackground(new Color(255,255,204));
        jPanelAddStock2.add(inAddStock);

        // button add stock
        jPanelBtnAddStock = new JPanel();
        btnAddStock = new JButton("Add Stock");
        btnAddStock.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		manageGameFunc.addStockGame(frame);
        	}
        });
        btnAddStock.setPreferredSize(new Dimension(215,35));
        jPanelBtnAddStock.setBackground(new Color(255,255,204));
        jPanelBtnAddStock.add(btnAddStock);

        // add the component into panel
        jPanelUpper.add(jPanelGameTable);

        jPanelCenter.add(jPanelNewGameId);
        jPanelCenter.add(jPanelNewGameId2);
        jPanelCenter.add(jPanelGameId);
        jPanelCenter.add(jPanelGameId2);
        jPanelCenter.add(jPanelNewGameName);
        jPanelCenter.add(jPanelNewGameName2);
        jPanelCenter.add(jPanelGameName);
        jPanelCenter.add(jPanelGameName2);
        jPanelCenter.add(jPanelNewGameType);
        jPanelCenter.add(jPanelNewGameType2);
        jPanelCenter.add(jPanelGameType);
        jPanelCenter.add(jPanelGameType2);
        jPanelCenter.add(jPanelNewGamePrice);
        jPanelCenter.add(jPanelNewGamePrice2);
        jPanelCenter.add(jPanelGamePrice);
        jPanelCenter.add(jPanelGamePrice2);
        jPanelCenter.add(jPanelNewGameStock);
        jPanelCenter.add(jPanelNewGameStock2);
        jPanelCenter.add(jPanelGameStock);
        jPanelCenter.add(jPanelGameStock2);

        jPanelButtons.add(jPanelBtnInsert);
        jPanelButtons.add(jPanelBtnUpdate);
        jPanelButtons.add(jPanelBtnDelete);

        jPanelButtons2.add(jPanelBtnReset);
        jPanelButtons2.add(jPanelAddStock);
        jPanelButtons2.add(jPanelAddStock2);
        jPanelButtons2.add(jPanelBtnAddStock);

        jPanelBottom.add(jPanelButtons);
        jPanelBottom.add(jPanelButtons2);

        jPanelUpper.setBackground(new Color(255,255,204));
        jPanelCenter.setBackground(new Color(255,255,204));
        jPanelBottom.setBackground(new Color(255,255,204));
    }
	
	public void resetNewGameInput() {
		initId();
		txtNewGameName.setText("");
		txtNewGamePrice.setText("");
		jComNewGameType.setSelectedIndex(0);
	}
	
	public void resetGameInput() {
		txtGameId.setText("");
		txtGameName.setText("");
		txtGamePrice.setText("");
		txtGameStock.setText("");
		jComGameType.setSelectedIndex(0);
	}
}
