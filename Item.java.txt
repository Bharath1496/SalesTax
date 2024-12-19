package SalesTax;
import java.math.BigDecimal;

public class Item {
	 private String name;
	    private boolean isExempt;
	    private boolean isImported;
	    private BigDecimal price;

	    public Item(String name, boolean isExempt, boolean isImported, BigDecimal price) {
	        this.name = name;
	        this.isExempt = isExempt;
	        this.isImported = isImported;
	        this.price = price;
	    }

	    public String getName() {
	        return name;
	    }

	    public boolean isExempt() {
	        return isExempt;
	    }

	    public boolean isImported() {
	        return isImported;
	    }

	    public BigDecimal getPrice() {
	        return price;
	    }

	    public void setPrice(BigDecimal price) {
	        this.price = price;
	    }
}
