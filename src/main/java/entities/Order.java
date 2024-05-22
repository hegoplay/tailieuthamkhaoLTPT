package entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@jakarta.persistence.Entity
@jakarta.persistence.Table(name="orders")
public class Order implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8747332395950179810L;
	@jakarta.persistence.Id
	@Column(name="order_id")
	private int id;
	@Column(name="order_date")
	private LocalDateTime orderDate;
	private double total;
	@jakarta.persistence.OneToOne(mappedBy="order",cascade = CascadeType.ALL)		
	private Payment payment;
	@jakarta.persistence.ManyToOne
	@jakarta.persistence.JoinColumn(name="customer_id")
	private Customer customer;
	@jakarta.persistence.OneToMany(mappedBy="order",cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;
}
