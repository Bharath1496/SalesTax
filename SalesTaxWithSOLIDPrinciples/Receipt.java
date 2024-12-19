package SalesTax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Receipt {

	
	    private final List<Product> products = new ArrayList<>();
	    private BigDecimal totalTaxes = BigDecimal.ZERO;
	    private BigDecimal totalPrice = BigDecimal.ZERO;
	    private final TaxPolicy taxPolicy;

	    public Receipt(TaxPolicy taxPolicy) {
	        this.taxPolicy = taxPolicy;
	    }

	    public void addProduct(Product product) {
	        BigDecimal tax = taxPolicy.calculateTax(product);
	        BigDecimal finalPrice = product.getPrice().add(tax);

	        products.add(product);
	        totalTaxes = totalTaxes.add(tax);
	        totalPrice = totalPrice.add(finalPrice);
	    }

	    public void printReceipt() {
	        for (Product product : products) {
	            System.out.printf("%s: %.2f%n", product.getName(), product.calculateFinalPrice(taxPolicy));
	        }
	        System.out.printf("Sales Taxes: %.2f%n", totalTaxes);
	        System.out.printf("Total: %.2f%n", totalPrice);
	    }
	}


