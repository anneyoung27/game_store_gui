package implement;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.JDBC4ResultSet;

import components.Game;

public class DetailTransaction implements access.DetailTransaction {
	Connection connection;

	// database query
	private String insertDetailTransaction = "INSERT INTO detailtransactions (TransactionID, GameID, Quantity) VALUES (?,?,?)";
	private String getAllByTransactionId = "SELECT detailtransactions FROM games WHERE TransactionID = ?";
	private String getAllByTransactionIdJoinGame = "SELECT * FROM detailtransactions LEFT JOIN games ON detailtransactions.GameID = games.GameID WHERE detailtransactions.TransactionID = ?";
	
	public DetailTransaction(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<components.DetailTransaction> getAll() {
		return null;
	}

	@Override
	public void insert(components.DetailTransaction detailTransaction) {
		PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(insertDetailTransaction);
            statement.setString(1, detailTransaction.getTransactionId());
            statement.setString(2, detailTransaction.getGameId());
            statement.setInt(3, detailTransaction.getQuantity());
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
	public void update(components.DetailTransaction dTransaction) {
	}

	@Override
	public void delete(String id) {
	}

	@Override
	public components.DetailTransaction findById(String id) {
		return null;
	}

	@Override
	public int countAll() {
		return 0;
	}

	@Override
	public List<components.DetailTransaction> getAllByTransactionId(String transactionId) {
		List<components.DetailTransaction> result = new ArrayList<components.DetailTransaction>();
		PreparedStatement statement = null;
		components.DetailTransaction detailTransaction = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(getAllByTransactionId);
            statement.setString(1, transactionId);
            JDBC4ResultSet rs = (JDBC4ResultSet) statement.executeQuery();
            while (rs.next()) {
                String gameId = rs.getString("GameID");
                int quantity = rs.getInt("Quantity");
                
                detailTransaction = new components.DetailTransaction();
                detailTransaction.setTransactionId(transactionId);
                detailTransaction.setGameId(gameId);
                detailTransaction.setQuantity(quantity);
                
                result.add(detailTransaction);
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

	@Override
	public List<components.DetailTransaction> getAllByTransactionIdJoinGame(String transactionId, JInternalFrame view) {
		List<components.DetailTransaction> result = new ArrayList<components.DetailTransaction>();
		PreparedStatement statement = null;
		components.DetailTransaction detailTransaction = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(getAllByTransactionIdJoinGame);
            statement.setString(1, transactionId);
            JDBC4ResultSet rs = (JDBC4ResultSet) statement.executeQuery();
            while (rs.next()) {
                String gameId = rs.getString(2);
                int quantity = rs.getInt(3);
                
                detailTransaction = new components.DetailTransaction();
                detailTransaction.setTransactionId(transactionId);
                detailTransaction.setGameId(gameId);
                detailTransaction.setQuantity(quantity);
                
                Game game = new Game();
                game.setId(rs.getString(4));
                game.setName(rs.getString(5));
                game.setType(rs.getString(6));
                game.setPrice(rs.getInt(7));
                game.setStock(rs.getInt(8));
                detailTransaction.setGame(game);
                
                result.add(detailTransaction);
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
