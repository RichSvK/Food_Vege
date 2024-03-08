import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Scanner input = new Scanner(System.in);
	static int menu = 0;
	public static void main(String[] args) {
		int choice = 0;
		ArrayList<Food> listFood = new ArrayList<Food>();
		
		do {
			choice = menu();
			switch(choice) {
				case 1: insertMenu(listFood);
					break;
				
				case 2: viewMenu(listFood);
					break;
					
				case 3: sellMenu(listFood);
					break;
					
				default:
					System.out.println("Program Finished");
					return;
			}
			System.out.println("Press [Enter] to continue...");
			input.nextLine();
			
			// Print Line
			for(int i = 0; i < 20; i++) System.out.println();
		} while(choice != 4);
	}
	
	static int menu() {
		int choice = 0;
		System.out.println("Five Food Street");
		System.out.println("================");
		System.out.println("1. Insert New Menu");
		System.out.println("2. View All Menu");
		System.out.println("3. Sell Menu Item");
		System.out.println("4. Exit");
		
		// Check user input
		do {
			System.out.print(">> ");
			try {
				choice = input.nextInt();
			} catch (Exception e) {
				System.err.println("Invalid Input");
			}
			input.nextLine();
		} while(choice < 1 || choice > 4);
		return choice;
	}
	
	static void insertMenu(ArrayList<Food> listFood) {
		String name = null;
		String dish = null;
		String type = null;
		int basePrice = 0;
		String foodName = null;
		boolean loop = false;
		String ID = null;
		
		// Menu name input
		do {
			System.out.print("Input menu name [8 - 20]: ");
			name = input.nextLine();
		} while(name.length() < 8 || name.length() > 20);
		
		// Main dish
		do {
			System.out.print("Input main dish [Rice | Noodle][Case Insensitive]: ");
			dish = input.nextLine();
		} while (!dish.equalsIgnoreCase("Rice") && !dish.equalsIgnoreCase("Noodle"));
		
		// Food type input
		do {
			System.out.print("Input menu type [Vege | Non-Vege][Case Sensitive]: ");
			type = input.nextLine();
		} while (!type.equals("Vege") && !type.equals("Non-Vege"));
		
		// Base price input
		do {
			System.out.print("Input base price [5000 - 25000][Multiple of 1000]: ");
			
			// Check user input
			try {
				basePrice = input.nextInt();
				loop = false;
			} catch (Exception e) {
				loop = true;
				System.err.println("Invalid Input");
			}
			input.nextLine();
		} while(loop || basePrice % 1000 != 0 || basePrice < 5000 || basePrice > 25000);
		
		if(type.equals("Vege")) {
			// Input foodName
			do {
				System.out.print("Input Vegetable [Potato | Tomato][Case Insensitive]: ");
				foodName = input.nextLine();
			} while(!foodName.equalsIgnoreCase("Potato") && !foodName.equalsIgnoreCase("Tomato"));
			
			// Vege Food Menu ID
			ID = "VV%03d".formatted(menu + 1);
			
			// Create instance of Vege food
			Vege vegeFood = new Vege(name, ID, foodName, basePrice, dish, type);
			vegeFood.calculateHarga(vegeFood);
			listFood.add(vegeFood);
			System.out.println("Vege food added successfully");
		} else {
			String addOns = null;
			
			// Input foodName
			do {
				System.out.print("Input Meat [Beef | Chicken | Pork][Case Insensitive]: ");
				foodName = input.nextLine();
			} while(!foodName.equalsIgnoreCase("Beef") && !foodName.equalsIgnoreCase("Chicken") && !foodName.equalsIgnoreCase("Pork"));
			
			// Input addOns
			do {
				System.out.print("Input addOns [Meatballs | Fishballs | Fried Potato][Case Insensitive]: ");
				addOns = input.nextLine();
			} while(!addOns.equalsIgnoreCase("Meatballs") && !addOns.equalsIgnoreCase("Fishballs") && !addOns.equalsIgnoreCase("Fried Potato"));
			
			// Non Vege Food ID
			ID = "NV%03d".formatted(menu + 1);
			
			// Create instance of Non vege food
			NonVege nonFood = new NonVege(name, ID, addOns, foodName, dish, basePrice, type);
			nonFood.calculateHarga(nonFood);
			listFood.add(nonFood);
			System.out.println("Non vege food added successfully");
		}
		menu += 1;
	}
	
	static void viewMenu(ArrayList<Food> listFood) {
		int size = listFood.size();
		
		// If there is no food in listFood
		if(size == 0) {
			System.out.println("No menu available");
			return;	
		}
		
		// Table Header
		System.out.println("=============================================================");
		System.out.printf("| No | %-5s | %-20s | %-10s | %-8s |\n", "ID", "Name", "Price", "Type");
		System.out.println("=============================================================");
		
		// Table body
		for(int i = 0; i < size; i++) {
			System.out.printf("| %-2d | %-5s | %-20s | %-10d | %-8s |\n", i + 1, listFood.get(i).getID(), listFood.get(i).getName(), listFood.get(i).getPrice(), listFood.get(i).getType());
		}
		
		// Table close
		System.out.println("=============================================================");
	}
	
	static void sellMenu(ArrayList<Food> listFood) {
		int size = listFood.size();

		// If there is no food in listFood
		if(size == 0) {
			System.out.println("No menu available");
			return;	
		}
		
		viewMenu(listFood);
		int choice = 0;
		
		// Input choice food to be sold from listFood
		do {
			System.out.printf("Input number [1 - %d]: ", size);
			try {
				choice = input.nextInt();
			} catch (Exception e) {
				System.err.println("Invalid Input");
			}
			input.nextLine();
		} while(choice < 1 || choice > size);
		
		int quantity = 0;
		// Input quantity to be sold
		do {
			System.out.print("Input quantity: ");
			try {
				quantity = input.nextInt();
			} catch (Exception e) {
				System.err.println("Invalid Input");
			}
			input.nextLine();
		} while(quantity <= 0);
		
		// Food information
		System.out.printf("ID: %s\n", listFood.get(choice - 1).getID());
		System.out.printf("Menu Name: %s\n", listFood.get(choice - 1).getName());
		System.out.printf("Main Dish: %s\n", listFood.get(choice - 1).getDish());
		System.out.printf("Price: %d\n", listFood.get(choice - 1).getPrice());
		System.out.printf("Grand Total: %d\n", listFood.get(choice - 1).getPrice() * quantity);
		listFood.remove(choice - 1);
		System.out.println("Food Sold");
	}
}