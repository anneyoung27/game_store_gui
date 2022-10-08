package functions;

import java.util.List;

import javax.swing.JInternalFrame;

import access.DetailTransaction;
import access.HeaderTransaction;
import database.ConnectDB;

public class TransactionFunc {
	public List<components.HeaderTransaction> getAllHeaderTransactions(String userId) {
		try {
			HeaderTransaction headerTransaction = ConnectDB.getHeaderTransaction();
			return headerTransaction.getAllByUser(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<components.DetailTransaction> getAllDetailTransactions(String transactionId, JInternalFrame view) {
		try {
			DetailTransaction detailTransaction = ConnectDB.getDetailTransaction();
			return detailTransaction.getAllByTransactionIdJoinGame(transactionId, view);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
