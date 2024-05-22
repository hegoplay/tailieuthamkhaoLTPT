package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import connect.ConnectDB;
import entities.Customer;
import entities.Order;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CustomerDAO{
	
	public static boolean updateAddress(String customerId, String newAddress) {
		// TODO Auto-generated method stub
		EntityTransaction transaction = ConnectDB.instance.manager.getTransaction();
		try {
			transaction.begin();
			Customer customer = ConnectDB.instance.manager.find(Customer.class, customerId);
			customer.setAddress(newAddress);
			transaction.commit();
			return true;
		}
		catch (Exception e) {
			transaction.rollback();
		}
		return false;
	}
	public static List<Customer> listCustomers(String productName, String fromOrderDate, String toOrderDate){
		// TODO Auto-generated method stub
		LocalDateTime fromLDT = LocalDateTime.parse(fromOrderDate);
		LocalDateTime toLDT = LocalDateTime.parse(toOrderDate);
		CriteriaBuilder cb = ConnectDB.instance.manager.getCriteriaBuilder();
		CriteriaQuery<Customer> root = cb.createQuery(Customer.class);
		Root<Customer> rootOrder = root.from(Customer.class);
		Join<Object, Object> join = rootOrder.join("orders");
		Join<Object, Object> join2 = join.join("orderDetails");
		Predicate p = cb.between(join.get("orderDate"), fromLDT, toLDT);
		p = cb.and(p, cb.like(join2.get("product").get("name"),"%" + productName + "%" ));
		root.select(rootOrder).where(p).distinct(true);
		return ConnectDB.instance.manager.createQuery(root).getResultList();
	}
	public static Map<String,Long> getNoOfCustomerFollowPaymentTypeByOrderTotal(double total){
		CriteriaBuilder cb = ConnectDB.instance.manager.getCriteriaBuilder();
		CriteriaQuery<Tuple> root = cb.createQuery(Tuple.class);
		Root<Order> rootOrder = root.from(Order.class);
		Join<Object, Object> payJoin = rootOrder.join("payment");
		Join<Object, Object> custJoin = rootOrder.join("customer");
		CriteriaQuery<Tuple> groupBy = root.multiselect(payJoin.type(),cb.count(custJoin.get("id")))
		.where(cb.greaterThanOrEqualTo(rootOrder.get("total"),total)).
		groupBy(payJoin.type());
		List<Tuple> list = ConnectDB.instance.manager.createQuery(groupBy).getResultList();
		Map<String,Long> map = new java.util.HashMap<>();
		for (Tuple t : list) {
			map.put(t.get(0).toString(), (Long) t.get(1));
		}
		return map;
	}
}
