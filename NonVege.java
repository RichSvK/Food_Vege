public class NonVege extends Food {
	private String addOns;
	public NonVege(String name, String ID, String addOns, String foodName, String dish, int basePrice, String type) {
		super(name, ID, foodName, basePrice, dish, type);
		this.addOns = addOns;
	}
	
	@Override
	public void calculateHarga(Food food) {
		int price = food.getBasePrice();
		if(food.getDish().equalsIgnoreCase("Rice")) price += 5000;
		else price += 3000;
		if(addOns.equalsIgnoreCase("Meatballs")) price += 4000;
		else if(this.addOns.equalsIgnoreCase("Fishballs")) price += 3000;
		else price += 5000;
		
		if(food.getFoodName().equalsIgnoreCase("Beef")) price += food.getName().length() * 2500;
		else if(food.getFoodName().equals("Chicken")) price += food.getName().length() * 1000;
		else price += food.getName().length() * 2000;
		food.setPrice(price);
	}
}