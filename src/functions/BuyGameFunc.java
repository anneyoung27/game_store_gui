package functions;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import access.DetailTransaction;
import access.GameInterface;
import access.HeaderTransaction;
import database.ConnectDB;
import components.Game;
import frame.BuyGameFormSubMenu;

public class BuyGameFunc {
	public Map<String, Integer> cart = new HashMap<String, Integer>();
	
	public void addItem(BuyGameFormSubMenu view) {
		String id = view.getTxtGameId().getText();
		String quantity = view.getSpinGameQuantity().getValue().toString();
		String stock = view.getTxtGameStock().getText();
		
		if(id.equals("")){
            JOptionPane.showMessageDialog(view, "Please fill the game id!");
        }else if (!isNumeric(quantity) || Integer.parseInt(quantity) < 1 || Integer.parseInt(quantity) > Integer.parseInt(stock)) {
			JOptionPane.showMessageDialog(view, "Quantity must be numeric, can't be less than 1, and can't be more than its stock");
		}else {
			if (cart.containsKey(id)) {
				if (Integer.parseInt(quantity) + cart.get(id) > Integer.parseInt(stock)) {
					JOptionPane.showMessageDialog(view, "There is no more stock for this game");
					return;
				}
				cart.put(id, Integer.parseInt(quantity) + cart.get(id));
			} else {
				cart.put(id, Integer.parseInt(quantity));
			}
			view.refreshTableBottom();
		}
	}
	
	public void checkOut(BuyGameFormSubMenu view) {
		try {
			if (isConfirmed("Are you sure want to checkout cart?")) {
				// Create header transaction
			    HeaderTransaction headerTransactions = ConnectDB.getHeaderTransaction();
			    components.HeaderTransaction headerTransaction = new components.HeaderTransaction();
			    String id = getNewId();
			    headerTransaction.setId(id);
			    headerTransaction.setUserId(view.user.getId());
			    headerTransaction.setTransactionDate(new Date());
			    headerTransactions.insert(headerTransaction);
			    
				for (Map.Entry<String, Integer> entry : cart.entrySet()) {
					// Create detail transaction
				    DetailTransaction detailTransactions = ConnectDB.getDetailTransaction();
				    components.DetailTransaction detailTransaction = new components.DetailTransaction();
				    detailTransaction.setTransactionId(id);
				    detailTransaction.setGameId(entry.getKey());
				    detailTransaction.setQuantity(entry.getValue());
				    detailTransactions.insert(detailTransaction);

					// Update game stock
				    GameInterface gameInterface = ConnectDB.getGames();
				    Game game = gameInterface.findById(entry.getKey());
				    int newStock = game.getStock() - entry.getValue();
				    game.setStock(newStock);
				    gameInterface.update(game);
				}
				
				cart = new HashMap<String, Integer>();
				view.refreshTables();
				JOptionPane.showMessageDialog(view,"Successfully Checkout Cart!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// hapus item di cart
	public void removeItem(BuyGameFormSubMenu view, String id) {
		cart.remove(id);
		view.refreshTableBottom();
		JOptionPane.showMessageDialog(view,"Game has been removed from cart!");
	}

	// hapus cart.
	public void resetCart(BuyGameFormSubMenu view) {
		if (isConfirmed("Are you sure want to clear cart?")) {
			cart = new HashMap<String, Integer>();
			view.refreshTableBottom();
		}
	}
	
	public List<Game> getAllGames() {
		try {
			GameInterface gameInterface = ConnectDB.getGames();
			return gameInterface.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// numerik
	public static boolean isNumeric(String str) { 
	  try {  
	    Double.parseDouble(str);  
	    return true;
	  } catch(NumberFormatException e){  
	    return false;  
	  }  
	}
	
	public String getNewId() throws SQLException {
		HeaderTransaction headerTransaction = ConnectDB.getHeaderTransaction();
		int lastId = headerTransaction.countAll();
		int newId = lastId + 1;
    	return newId < 10 ? "TR00" + newId : newId < 100 ? "TR0" + newId : String.valueOf(newId);
	}
	
	public boolean isConfirmed(String message) {
		int option = JOptionPane.showConfirmDialog(null, message, "Yes", JOptionPane.YES_NO_OPTION);
		if (option == 0) return true;
		return false;
	}
}
