package com.shopping.app.demo.custom.impl;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.shopping.app.demo.custom.ProductOrderCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Component
public class ProductOrderCustomImpl implements ProductOrderCustom{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	@Transactional
	public void saveProductOrder(long orderId, String coupon, int amount, Date orderDate, String transaction,
			String status, int userId) {
		Query query = entityManager.createNativeQuery("insert into product_order values (:transaction, :amount, :orderDate , :coupon, :userId , :status, :orderId)");
		query.setParameter("orderId",orderId );
		query.setParameter("coupon", coupon);
		query.setParameter("amount", amount);
		query.setParameter("orderDate", orderDate);
		query.setParameter("transaction", transaction);
		query.setParameter("status", status);
		query.setParameter("userId",userId );
		query.executeUpdate();
		
	}

}
