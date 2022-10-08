package access;

import java.util.List;

import components.Game;

public interface GameInterface {
	public List<Game> getAll();

    public void insert(Game game);

    public void update(Game game);

    public void delete(String id);

    public Game findById(String id);
    
    public int countAll();
}
