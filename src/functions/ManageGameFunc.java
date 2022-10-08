package functions;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import access.GameInterface;
import database.ConnectDB;
import components.Game;
import frame.ManageGameFormSubMenu;

public class ManageGameFunc {
	public void insertGame(ManageGameFormSubMenu view) {
		String id = view.getTxtNewGameId().getText();
		String name = view.getTxtNewGameName().getText();
		String type = view.getjComNewGameType().getSelectedItem().toString();
		String price = view.getTxtNewGamePrice().getText();
		String stock = view.getInNewGameStock().getValue().toString();
		
		if(id.equals("")){
            JOptionPane.showMessageDialog(view, "Please fill the game id!");
        }else if(name.length() < 5 || name.length() > 30) {
            JOptionPane.showMessageDialog(view, "Game name must consist of 5-30 characters");
        }else if((!isNumeric(price)) || Double.parseDouble(price) < 1) {
        	JOptionPane.showMessageDialog(view, "Game price must more than 0 and numeric");
        }else if((!isNumeric(stock)) || (Double.parseDouble(stock) < 1)) {
        	JOptionPane.showMessageDialog(view, "Game stock must more than 0 and numeric");
        }else {
        	try {
        		Game game = new Game();
            	game.setId(id);
            	game.setName(name);
            	game.setType(type);
            	game.setPrice(Integer.parseInt(price));
            	game.setStock(Integer.parseInt(stock));
            	
            	if (isConfirmed("Are you sure want to insert new game?")) {
            		GameInterface gameInterface = ConnectDB.getGames();
                	gameInterface.insert(game);
                	view.resetNewGameInput();
                	view.refreshTable();
                	JOptionPane.showMessageDialog(view,"Successfully Added");
            	}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(view,"Add new game failed");
			}
        }
	}
	
	public void updateGame(ManageGameFormSubMenu view) {
		String id = view.getTxtGameId().getText();
		String name = view.getTxtGameName().getText();
		String type = view.getjComGameType().getSelectedItem().toString();
		String price = view.getTxtGamePrice().getText();
		
		if(id.equals("")){
            JOptionPane.showMessageDialog(view, "Please fill the game id!");
        }else if(name.length() < 5 || name.length() > 30) {
            JOptionPane.showMessageDialog(view, "Game name must consist of 5-30 characters");
        }else if((!isNumeric(price)) || Double.parseDouble(price) < 1) {
        	JOptionPane.showMessageDialog(view, "Game price must more than 0 and numeric");
        }else {
        	try {
        		GameInterface gameInterface  = ConnectDB.getGames();
        		Game game = gameInterface.findById(id);
        		
        		if (game == null) {
        			JOptionPane.showMessageDialog(view, "Game with given ID is not found");
					return;
				}
        		
            	if (isConfirmed("Are you sure want to update game?")) {
            		game.setName(name);
                	game.setType(type);
                	game.setPrice(Integer.parseInt(price));
                	gameInterface.update(game);
                	
                	view.resetGameInput();
                	view.refreshTable();
                	JOptionPane.showMessageDialog(view,"Successfully Updated");
            	}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(view,"Update game failed");
			}
        }
	}
	
	public void deleteGame(ManageGameFormSubMenu view) {
		String id = view.getTxtGameId().getText();
		
		if(id.equals("")){
            JOptionPane.showMessageDialog(view, "Please fill the game id!");
        }else {
        	try {
        		GameInterface gameInterface = ConnectDB.getGames();
        		Game game = gameInterface.findById(id);
        		
        		if (game == null) {
        			JOptionPane.showMessageDialog(view, "Game with given ID is not found");
					return;
				}
        		
            	if (isConfirmed("Are you sure want to delete game?")) {
            		gameInterface.delete(id);
                	view.resetGameInput();
                	view.refreshTable();
                	JOptionPane.showMessageDialog(view,"Successfully Deleted");
            	}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(view,"Delete game failed");
			}
        }
	}
	
	public void addStockGame(ManageGameFormSubMenu view) {
		String id = view.getTxtGameId().getText();
		String newStock = view.getInAddStock().getValue().toString();
		
		if(id.equals("")){
            JOptionPane.showMessageDialog(view, "Please fill the game id!");
        }else if((!isNumeric(newStock)) || Double.parseDouble(newStock) < 1) {
        	JOptionPane.showMessageDialog(view, "Add stock must more than 0 and numeric");
        }else {
        	try {
        		GameInterface gameInterface = ConnectDB.getGames();
        		Game game = gameInterface.findById(id);
        		
        		if (game == null) {
        			JOptionPane.showMessageDialog(view, "Game with given ID is not found");
					return;
				}
        		
            	if (isConfirmed("Are you sure want to add game stock?")) {
            		int allStock = game.getStock() + Integer.parseInt(newStock);
                	game.setStock(allStock);
                	gameInterface.update(game);
                	
                	view.resetGameInput();
                	view.refreshTable();
                	JOptionPane.showMessageDialog(view,"Stock Successfully Added");
            	}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(view,"Add stock failed");
			}
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
	
	public String getNewId() throws SQLException {
		GameInterface gameInterface = ConnectDB.getGames();
		int lastId = gameInterface.countAll();
		int newId = lastId + 1;
    	return newId < 10 ? "GA00" + newId : newId < 100 ? "GA0" + newId : String.valueOf(newId);
	}
	
	public static boolean isNumeric(String str) { 
	  try {  
	    Double.parseDouble(str);  
	    return true;
	  } catch(NumberFormatException e){  
	    return false;  
	  }  
	}
	
	public boolean isConfirmed(String message) {
		int option = JOptionPane.showConfirmDialog(null, message, "Yes", JOptionPane.YES_NO_OPTION);
		if (option == 0) return true;
		return false;
	}
}
