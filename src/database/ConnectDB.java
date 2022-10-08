package database;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import access.GameInterface;
import access.UserInterface;
import implement.DetailTransaction;
import implement.GameInterfaceImpl;
import implement.HeaderTransaction;
import implement.UserImpl;

public class ConnectDB {
	private static Connection con;
	private static UserInterface users;
	private static GameInterface gameInterface;
	private static access.HeaderTransaction headerTransaction;
	private static access.DetailTransaction detailTransaction;

    public static Connection getCon() throws SQLException {
    	final String USERNAME = "root";
    	final String PASSWORD = "";
    	final String DATABASE = "<password>";
    	final String HOST = "localhost:3306";
		final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

        if (con == null) {
        	// Reference : https://www.tabnine.com/code/java/classes/com.mysql.jdbc.jdbc2.optional.MysqlDataSource
            MysqlDataSource datasource = new MysqlDataSource();
            datasource.setURL(CONNECTION);
            datasource.setUser(USERNAME);
            datasource.setPassword(PASSWORD);
            con = (Connection) datasource.getConnection();
        }
        return con;
    }
    
    public static UserInterface getUsers() throws SQLException  {
    	if (users == null) {
    		users = new UserImpl(getCon());
    	}
    	return users;
    }
    
    public static GameInterface getGames() throws SQLException  {
    	if (gameInterface == null) {
    		gameInterface = new GameInterfaceImpl(getCon());
    	}
    	return gameInterface;
    }
    
    public static access.HeaderTransaction getHeaderTransaction() throws SQLException  {
    	if (headerTransaction == null) {
    		headerTransaction = new HeaderTransaction(getCon());
    	}
    	return headerTransaction;
    }
    
    public static access.DetailTransaction getDetailTransaction() throws SQLException  {
    	if (detailTransaction == null) {
    		detailTransaction = new DetailTransaction(getCon());
    	}
    	return detailTransaction;
    }
}
