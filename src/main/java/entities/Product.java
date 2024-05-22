package entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@Table(name="products")
public class Product implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3914766307812265317L;
	@jakarta.persistence.Id
	@Column(name="product_id")
	private int id;
	private String name;
	private double price;
	@jakarta.persistence.OneToMany(mappedBy="product")
	private List<OrderDetail> orderDetails;
}
	