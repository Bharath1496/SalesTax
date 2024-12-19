package SalesTax;
import java.math.BigDecimal;

public class InputParser {
	
	    public Product parseInput(String input) {
	        if (!input.contains(" at ")) {
	            throw new IllegalArgumentException("Invalid input format. Missing 'at'.");
	        }

	        String[] parts = input.split(" at ");
	        String description = parts[0].trim();
	        BigDecimal price = new BigDecimal(parts[1].trim());

	        boolean isImported = description.toLowerCase().contains("imported");
	        boolean isExempt = isExemptProduct(description);

	        String name = description.replace("imported", "").trim();

	        return new Product(name, isExempt, isImported, price);
	    }

	    private boolean isExemptProduct(String description) {
	        String[] exemptKeywords = {"book", "food", "medicine"};
	        for (String keyword : exemptKeywords) {
	            if (description.toLowerCase().contains(keyword)) {
	                return true;
	            }
	        }
	        return false;
	    }
	

}
