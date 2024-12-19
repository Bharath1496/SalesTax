package SalesTax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Receipt {
	 private List<Item> items;
	    private BigDecimal totalTaxes;
	    private BigDecimal totalPrice;

	    public Receipt() {
	        this.items = new ArrayList<>();
	        this.totalTaxes = BigDecimal.ZERO;
	        this.totalPrice = BigDecimal.ZERO;
	    }

	    public void addItem(Item item, BigDecimal tax) {
	        BigDecimal totalItemPrice = item.getPrice().add(tax);
	        item.setPrice(totalItemPrice);

	        items.add(item);
	        totalTaxes = totalTaxes.add(tax);
	        totalPrice = totalPrice.add(totalItemPrice);
	    }

	    public void printReceipt() {
	        for (Item item : items) {
	            System.out.printf("%d %s: %.2f%n", 1, item.getName(), item.getPrice());
	        }
	        System.out.printf("Sales Taxes: %.2f%n", totalTaxes);
	        System.out.printf("Total: %.2f%n", totalPrice);
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        List<Item> items = new ArrayList<>();

	        System.out.println("Welcome to the Sales Tax Calculator.");
	        System.out.println("Input Format: <quantity> <imported (if applicable)> <book/food/medicine> <name> at <price>");
	        System.out.println("Example: 1 imported book at 12.49 or 1 bottle of perfume at 18.99");
	        System.out.println("For items such as books, food, and medicines , please enter the keyword before the product name");
	        System.out.println("Enter items one by one. Leave an empty line to finish.");

	        while (true) {
	        	String input = scanner.nextLine().trim();
	            if (input.isEmpty()) {
	                break;  
	            }
	            try {
	                items.add(parseInput(input));
	            } catch (IllegalArgumentException e) {
	                System.out.println(e.getMessage());
	                System.out.println("Please re-enter the item in the correct format: <quantity><imported (if applicable)><book/food/medicine><name><at><price>");
	            }
	        }

	        Receipt receipt = new Receipt();

	        for (Item item : items) {
	            BigDecimal tax = SalesTaxCalculator.calculateTax(item);
	            receipt.addItem(item, tax);
	        }

	        receipt.printReceipt();
	    }
	    

	    private static Item parseInput(String input) {
	        String[] parts = input.split(" ");
	        
	        if (parts.length < 3 || !input.contains(" at ")) {
	            throw new IllegalArgumentException("Invalid input format. Ensure the input contains 'at' followed by the price.");
	        }
	        
	        int quantity;
	        try {
	            quantity = Integer.parseInt(parts[0]);
	        } catch (NumberFormatException e) {
	            throw new IllegalArgumentException("Invalid quantity format. Quantity must be a numeric value.");
	        }
	        
	        boolean isImported = input.toLowerCase().contains("imported");
	        
	        
	        String namePart = input.substring(input.indexOf(' ') + 1, input.lastIndexOf(" at ")).trim();
	        BigDecimal price;
	        
	        try {
	            price = new BigDecimal(input.substring(input.lastIndexOf(" at ") + 3).trim());
	        } catch (NumberFormatException e) {
	            throw new IllegalArgumentException("Invalid price format. Price should be a numeric value.");
	        }
	        
	        boolean isExempt = isExemptItem(namePart);
	        
	        return new Item(namePart, isExempt, isImported, price);
	    }




	    private static boolean isExemptItem(String name) {
	        String[] exemptKeywords = {"book", "food", "medicine"};
	        for (String keyword : exemptKeywords) {
	            if (name.toLowerCase().contains(keyword)) {
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    

}
