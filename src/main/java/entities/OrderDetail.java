package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@Table(name="order_detail")
public class OrderDetail implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4723909467004374385L;
	@jakarta.persistence.Id
	@jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name="order_detail_id")
	private int id;
	private double price;
	@ManyToOne
	@jakarta.persistence.JoinColumn(name="order_id")
	private Order order;
	@ManyToOne
	@jakarta.persistence.JoinColumn(name="product_id")
	private Product product;
}
