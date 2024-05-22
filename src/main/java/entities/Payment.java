package entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="payments")
@Getter
@Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="payment_type")
public class Payment implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8300576120319842676L;

	@jakarta.persistence.Id
	@jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)	
	protected int id;
	@Column(name="payment_date")
	protected LocalDateTime paymentDate;
	protected double amount;
	@OneToOne
	@JoinColumn(name="order_id")	
	protected Order order;
}
