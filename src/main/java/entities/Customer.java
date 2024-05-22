package entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@Setter
@Table(name="customers")
@ToString(exclude = {"orders"})
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3227258562594234582L;
	@jakarta.persistence.Id
	@Column(name="customer_id")
	private int id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	private String address;
	private String email;
	@jakarta.persistence.OneToMany(mappedBy="customer")
	
	private List<Order> orders;
	
}
