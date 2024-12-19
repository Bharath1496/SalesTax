package SalesTax;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class SalesTaxCalculator {
    private static final BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.05");  
    private static final BigDecimal ROUNDING_FACTOR = new BigDecimal("0.05");  

    public static BigDecimal calculateTax(Item item) {
        BigDecimal tax = BigDecimal.ZERO;

        if (item.isImported()) {
            if (item.isExempt()) {
                tax = tax.add(item.getPrice().multiply(IMPORT_TAX_RATE));
            } else {
                tax = tax.add(item.getPrice().multiply(new BigDecimal("0.10"))); 
                tax = tax.add(item.getPrice().multiply(IMPORT_TAX_RATE));           
            }
        }
        else if(!item.isImported())
        {
        	if(!item.isExempt())
        	{
                tax = tax.add(item.getPrice().multiply(new BigDecimal("0.10"))); 
        	}
        }

        return roundUpToNearestFiveCents(tax);
    }

    private static BigDecimal roundUpToNearestFiveCents(BigDecimal amount) {
        return amount.divide(ROUNDING_FACTOR, 0, RoundingMode.UP).multiply(ROUNDING_FACTOR);
    }
}
