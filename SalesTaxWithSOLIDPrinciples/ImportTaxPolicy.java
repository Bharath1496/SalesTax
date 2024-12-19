package SalesTax;
import java.math.BigDecimal;


public class ImportTaxPolicy implements TaxPolicy {
    private static final BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.05");

    @Override
    public BigDecimal calculateTax(Product product) {
        if (product.isImported()) {
            return product.getPrice().multiply(IMPORT_TAX_RATE);
        } else {
            return BigDecimal.ZERO;
        }
    }

}