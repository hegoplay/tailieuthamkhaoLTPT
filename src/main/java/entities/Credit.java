package entities;

import java.io.Serializable;

@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@jakarta.persistence.Entity
public class Credit extends Payment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6467683931376148641L;
	@jakarta.persistence.Column(name="cardholder_name")
	private String cardholderName;
	
}
