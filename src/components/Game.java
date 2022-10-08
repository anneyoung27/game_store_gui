package components;

public class Game {
	private String id;
	private String name;
	private String type;
	private int price;
	private int stock;
	
	public Game(String id, String name, String type, int price, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.stock = stock;
	}
	
	public Game() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}
