package baith2.Product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class Product {
	@Id
    private String code;
	@NotBlank(message = "Description is required")
    private String description;
	@NotBlank(message = "Price is required")
    private String price;
    
    public String toString() {
    	return code + " " + description + " " + price;
    }
}
