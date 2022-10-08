package implement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.JDBC4ResultSet;

public class HeaderTransaction implements access.HeaderTransaction {
	Connection connection;

	private String getAll = "SELECT * FROM headertransactions";
	private String getAllByUser = "SELECT * FROM  headertransactions WHERE UserID = ?";
	private String insertHeaderTransaction = "INSERT INTO headertransactions (TransactionID, UserID, TransactionDate) VALUES (?,?,?)";
	private String countAll = "SELECT COUNT(*) FROM headertransactions";
	
	public HeaderTransaction(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<components.HeaderTransaction> getAll() {
		return null;
	}

	@Override
	public void insert(components.HeaderTransaction headerTransaction) {
		PreparedStatement statement = null;
        try {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            statement = (PreparedStatement) connection.prepareStatement(insertHeaderTransaction);
            statement.setString(1, headerTransaction.getId());
            statement.setString(2, headerTransaction.getUserId());
            statement.setString(3, formatter.format(headerTransaction.getTransactionDate()));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                   e.printStackTrace();
                }
            }
        }
	}

	@Override
	public void update(components.HeaderTransaction hTransaction) {
	}

	@Override
	public void delete(String id) {
		
	}

	@Override
	public components.HeaderTransaction findById(String id) {
		return null;
	}

    // Reference : http://tutorials.jenkov.com/jdbc/resultset.html
	@Override
	public int countAll() {
		PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(countAll);
            JDBC4ResultSet rs = (JDBC4ResultSet) statement.executeQuery();
            while (rs.next()) {
            	return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                   e.printStackTrace();
                }
            }
        }
        return 0;
	}

	@Override
	public List<components.HeaderTransaction> getAllByUser(String userId) {
		List<components.HeaderTransaction> result = new ArrayList<components.HeaderTransaction>();
		PreparedStatement statement = null;
		components.HeaderTransaction headerTransaction = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(getAllByUser);
            statement.setString(1, userId);
            JDBC4ResultSet rs = (JDBC4ResultSet) statement.executeQuery();
            while (rs.next()) {
                String id = rs.getString("TransactionID");
                Date transactionDate = rs.getDate("TransactionDate");
                
                headerTransaction = new components.HeaderTransaction();
                headerTransaction.setId(id);
                headerTransaction.setUserId(userId);
                headerTransaction.setTransactionDate(transactionDate);
                
                result.add(headerTransaction);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
		return null;
	}

}
