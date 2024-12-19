package SalesTax;
import java.math.BigDecimal;


public interface TaxPolicy {
	 BigDecimal calculateTax(Product product);
}
