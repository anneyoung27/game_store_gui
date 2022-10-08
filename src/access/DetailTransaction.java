package access;

import java.util.List;

import javax.swing.JInternalFrame;

public interface DetailTransaction {
	public List<components.DetailTransaction> getAll();
	
	public List<components.DetailTransaction> getAllByTransactionId(String transactionId);
	
	public List<components.DetailTransaction> getAllByTransactionIdJoinGame(String transactionId, JInternalFrame view);

    public void insert(components.DetailTransaction dTransaction);

    public void update(components.DetailTransaction dTransaction);

    public void delete(String id);

    public components.DetailTransaction findById(String id);
    
    public int countAll();
}
