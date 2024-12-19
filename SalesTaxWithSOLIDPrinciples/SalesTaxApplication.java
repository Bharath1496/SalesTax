package SalesTax;

import java.util.Scanner;

public class SalesTaxApplication {
	
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        InputParser parser = new InputParser();
	        CombinedTaxPolicy taxPolicy = new CombinedTaxPolicy();
	        taxPolicy.addPolicy(new BasicTaxPolicy());
	        taxPolicy.addPolicy(new ImportTaxPolicy());

	        Receipt receipt = new Receipt(taxPolicy);

	        System.out.println("Welcome to the Sales Tax Calculator.");
	        System.out.println("Input Format: <quantity> <imported (if applicable)> <book/food/medicine> <name> at <price>");
	        System.out.println("Example: 1 imported book at 12.49 or 1 bottle of perfume at 18.99");
	        System.out.println("Enter items one by one. Leave an empty line to finish.");

	        while (true) {
	            String input = scanner.nextLine().trim();
	            if (input.isEmpty()) {
	                break;
	            }

	            try {
	                Product product = parser.parseInput(input);
	                receipt.addProduct(product);
	            } catch (IllegalArgumentException e) {
	                System.out.println(e.getMessage());
	            }
	        }

	        receipt.printReceipt();
	    }
	
}
