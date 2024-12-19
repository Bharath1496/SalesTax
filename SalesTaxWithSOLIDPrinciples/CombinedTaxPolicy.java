package SalesTax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


public class CombinedTaxPolicy implements TaxPolicy {
 private final List<TaxPolicy> policies = new ArrayList<>();

 public void addPolicy(TaxPolicy policy) {
     policies.add(policy);
 }

 @Override
 public BigDecimal calculateTax(Product product) {
     BigDecimal totalTax = BigDecimal.ZERO;
     for (TaxPolicy policy : policies) {
         totalTax = totalTax.add(policy.calculateTax(product));
     }
     return roundUpToNearestFiveCents(totalTax);
 }

 private BigDecimal roundUpToNearestFiveCents(BigDecimal amount) {
     BigDecimal roundingFactor = new BigDecimal("0.05");
     return amount.divide(roundingFactor, 0, RoundingMode.UP).multiply(roundingFactor);
 }
}