package implement;

import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.JDBC4ResultSet;

import access.GameInterface;
import components.Game;

public class GameInterfaceImpl implements GameInterface {
	private Connection connection;

	// database query.
	private String getAll = "SELECT * FROM games";
	private String findById = "SELECT * FROM games WHERE GameID = ?";
	private String insertGame = "INSERT INTO games (GameID, GameName, GameType, GamePrice, GameStock) VALUES (?,?,?,?,?)";
	private String updateGame = "UPDATE games SET GameName = ?, GameType = ?, GamePrice = ?, GameStock = ? WHERE GameID = ?";
	private String deleteGame = "DELETE FROM games WHERE GameID = ?";
	private String countAll = "SELECT COUNT(*) FROM games";

	public GameInterfaceImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Game> getAll() {
		List<Game> result = new ArrayList<Game>();
		PreparedStatement statement = null;
        Game game = null;
        // Reference : http://tutorials.jenkov.com/jdbc/resultset.html
        try {
            statement = (PreparedStatement) connection.prepareStatement(getAll);
            JDBC4ResultSet rs = (JDBC4ResultSet) statement.executeQuery();
            while (rs.next()) {
                String id = rs.getString("GameID");
                String name = rs.getString("GameName");
                String type = rs.getString("GameType");
                int price = rs.getInt("GamePrice");
                int stock = rs.getInt("GameStock");
                
                game = new Game();
                game.setId(id);
                game.setName(name);
                game.setType(type);
                game.setPrice(price);
                game.setStock(stock);
                result.add(game);
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
	public void insert(Game game) {
		PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(insertGame);
            statement.setString(1, game.getId());
            statement.setString(2, game.getName());
            statement.setString(3, game.getType());
            statement.setInt(4, game.getPrice());
            statement.setInt(5, game.getStock());
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
	public void update(Game game) {
		PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(updateGame);
            statement.setString(1, game.getName());
            statement.setString(2, game.getType());
            statement.setInt(3, game.getPrice());
            statement.setInt(4, game.getStock());
            statement.setString(5, game.getId());
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
	public void delete(String id) {
		PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(deleteGame);
            statement.setString(1, id);
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
	public Game findById(String id) {
		PreparedStatement statement = null;
        Game game = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(findById);
            statement.setString(1, id);
            JDBC4ResultSet rs = (JDBC4ResultSet) statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("GameName");
                String type = rs.getString("GameType");
                int price = rs.getInt("GamePrice");
                int stock = rs.getInt("GameStock");
                
                game = new Game();
                game.setId(id);
                game.setName(name);
                game.setType(type);
                game.setPrice(price);
                game.setStock(stock);
            }
            return game;
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
	

}
