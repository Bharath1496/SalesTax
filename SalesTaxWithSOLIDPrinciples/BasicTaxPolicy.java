package SalesTax;
import java.math.BigDecimal;

public class BasicTaxPolicy implements TaxPolicy {
 private static final BigDecimal BASIC_TAX_RATE = new BigDecimal("0.10");

 @Override
 public BigDecimal calculateTax(Product product) {
     if (product.isExempt()) {
         return BigDecimal.ZERO;
     } else {
         return product.getPrice().multiply(BASIC_TAX_RATE);
     }
 }

}