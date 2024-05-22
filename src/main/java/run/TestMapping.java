package run;

import java.util.Map;

import dao.CustomerDAO;
import jakarta.persistence.Persistence;

public class TestMapping {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Persistence.createEntityManagerFactory("JPA_ORM_MSSQL");
		Map<String, Long> noOfCustomerFollowPaymentTypeByOrderTotal = CustomerDAO.getNoOfCustomerFollowPaymentTypeByOrderTotal(1);
		noOfCustomerFollowPaymentTypeByOrderTotal.forEach((k,v)->System.out.println(k + " " + v));
	}
}
