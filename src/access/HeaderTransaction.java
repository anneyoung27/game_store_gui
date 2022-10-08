package access;

import java.util.List;

public interface HeaderTransaction {
	public List<components.HeaderTransaction> getAll();
	
	public List<components.HeaderTransaction> getAllByUser(String userId);

    public void insert(components.HeaderTransaction hTransaction);

    public void update(components.HeaderTransaction hTransaction);

    public void delete(String id);

    public components.HeaderTransaction findById(String id);
    
    public int countAll();
}
