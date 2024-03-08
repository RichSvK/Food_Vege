public abstract class Food {
	private String name;
	private String ID;
	private int price;
	private String dish;
	private String foodName;
	private int basePrice;
	private String type;
	
	public Food(String nama, String ID, String foodName, int basePrice, String dish, String type) {
		this.name = nama;
		this.ID = ID;
		this.foodName = foodName;
		this.basePrice = basePrice;
		this.dish = dish;
		this.type = type;
	}
	
	// Abtract Method
	public abstract void calculateHarga(Food makanan);

	public String getName() {
		return name;
	}

	public String getID() {
		return ID;
	}
	
	public String getType() {
		return type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDish() {
		return dish;
	}

	public String getFoodName() {
		return foodName;
	}

	public int getBasePrice() {
		return basePrice;
	}
}