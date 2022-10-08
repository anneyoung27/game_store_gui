package frame;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import functions.TransactionFunc;
import components.DetailTransaction;
import components.HeaderTransaction;
import components.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransactionFormSubMenu extends JInternalFrame {
    JLabel labelSelectedId, labelGrandTotal;
    JTable jTableHeaderTransact, jTableDetailTransact;
    DefaultTableModel dtm, dtm2;
    JScrollPane jScrollHeaderTransact, jScrollDetailTransact;
    JTextField txtSelectedId, txtGrandTotal;
    JPanel upperPanel, centerPanel, bottomPanel, panelTableUpper, panelTableBottom, panelSelected, panelGrandTotal;
    TransactionFunc transactionFunc;
    User user;
    List<HeaderTransaction> headerTransactions = new ArrayList<HeaderTransaction>();
    List<DetailTransaction> detailTransactions = new ArrayList<DetailTransaction>();

    public TransactionFormSubMenu(User user){
        super("",true,true,true,true);
        this.user = user;
        transactionFunc = new TransactionFunc();
        panelPerform();
        refreshTableUpper();

        this.getContentPane().add(upperPanel, BorderLayout.NORTH);
        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
        getContentPane().setLayout(new GridLayout(3,2));
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

    }
    
    public void refreshTableUpper() {
    	headerTransactions = transactionFunc.getAllHeaderTransactions(user.getId());
    	dtm.setRowCount(0);
    	for (HeaderTransaction headerTransaction : headerTransactions) {
			Object[] row = new Object[3];
			row[0] = headerTransaction.getId();
	        row[1] = headerTransaction.getUserId();
	        row[2] = headerTransaction.getTransactionDate();
	        dtm.addRow(row);
		}
    }
    
    public void refreshTableBottom() {
    	dtm2.setRowCount(0);
    	int total = 0;
    	for (DetailTransaction detailTransaction : detailTransactions) {
    		int subtotal = detailTransaction.getGame().getPrice() * detailTransaction.getQuantity();
			Object[] row = new Object[7];
			row[0] = detailTransaction.getTransactionId();
			row[1] = detailTransaction.getGameId();
	        row[2] = detailTransaction.getGame().getName();
	        row[3] = detailTransaction.getGame().getType();
	        row[4] = detailTransaction.getGame().getPrice();
	        row[5] = detailTransaction.getQuantity();
	        row[6] = subtotal;
	        dtm2.addRow(row);
	        total += subtotal;
		}
    	txtGrandTotal.setText(String.valueOf(total));
    }

    void panelPerform(){
    	TransactionFormSubMenu frame = this;
    	
        // set up panel
        upperPanel = new JPanel(new GridLayout(1,1));
        upperPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Transaction History",
                TitledBorder.CENTER, TitledBorder.TOP));
        centerPanel = new JPanel(new GridLayout(1,1));
        bottomPanel = new JPanel(new GridLayout(2,1));

        // initialize component
        Vector<Object> tableHeaderTransact, tableDataHeader;

        // table Header
        tableHeaderTransact = new Vector<>();

        tableHeaderTransact.add("Transaction ID");
        tableHeaderTransact.add("User ID");
        tableHeaderTransact.add("Transaction Date");

        dtm = new DefaultTableModel(tableHeaderTransact,0);

        panelTableUpper = new JPanel(new GridLayout(1,1));
        jTableHeaderTransact = new JTable(dtm);
        jTableHeaderTransact.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int i = jTableHeaderTransact.getSelectedRow();
        		if (i > -1) {
        			HeaderTransaction headerTransaction = headerTransactions.get(i);
        			txtSelectedId.setText(headerTransaction.getId());
        			detailTransactions = transactionFunc.getAllDetailTransactions(headerTransaction.getId(), frame);
        			refreshTableBottom();
				}
        	}
        });
        jScrollHeaderTransact = new JScrollPane(jTableHeaderTransact);
        panelTableUpper.add(jScrollHeaderTransact);

        // selected id
        panelSelected = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelSelectedId = new JLabel("Selected ID");
        panelSelected.setBackground(new Color(255,255,204));
        panelSelected.add(labelSelectedId);

        // txt selected id
        txtSelectedId = new JTextField(15);
        panelSelected.add(txtSelectedId);

        // table detail bottom
        Vector<Object> tableDetailTransact, tableDataDetail;

        tableDetailTransact = new Vector<>();
        tableDetailTransact.add("Transaction ID");
        tableDetailTransact.add("Game ID");
        tableDetailTransact.add("Game Name");
        tableDetailTransact.add("Game Type");
        tableDetailTransact.add("Game Price");
        tableDetailTransact.add("Game Quantity");
        tableDetailTransact.add("Sub Total");

        panelTableBottom = new JPanel(new GridLayout(1,1));

        dtm2 = new DefaultTableModel(tableDetailTransact,0);

        jTableDetailTransact = new JTable(dtm2);
        jScrollDetailTransact = new JScrollPane(jTableDetailTransact);
        jScrollDetailTransact.setPreferredSize(new Dimension(800,500));
        panelTableBottom.add(jScrollDetailTransact);

        // grand total
        panelGrandTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        labelGrandTotal = new JLabel("Grand Total");
        panelGrandTotal.setBackground(new Color(255,255,204));
        panelGrandTotal.add(labelGrandTotal);

        // txt grand total
        txtGrandTotal = new JTextField(15);
        panelGrandTotal.add(txtGrandTotal);


        // add component to panel
        upperPanel.add(panelTableUpper);

        centerPanel.add(panelSelected);

        bottomPanel.add(panelTableBottom);
        bottomPanel.add(panelGrandTotal);

        upperPanel.setBackground(new Color(255,255,204));
        centerPanel.setBackground(new Color(255,255,204));
        bottomPanel.setBackground(new Color(255,255,204));
    }

}
