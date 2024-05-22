package entities;

import java.io.Serializable;

@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@jakarta.persistence.Entity
public class Cash extends Payment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2385254191549287367L;
	@jakarta.persistence.Column(name="recieve_amount")
	private double recieveAmount; 
}
