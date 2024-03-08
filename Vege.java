public class Vege extends Food{
	public Vege(String name, String ID, String foodName, int basePrice, String dish, String type) {
		super(name, ID, foodName, basePrice, dish, type);
	}

	@Override
	public void calculateHarga(Food food) {
		int price = food.getBasePrice();
		if(food.getDish().equalsIgnoreCase("Rice")) price += 5000;
		else price += 3000;
		
		if(food.getFoodName().equalsIgnoreCase("Potato")) price += food.getName().length() * 2000;
		else price += food.getName().length() * 1000;
		food.setPrice(price);
	}
}