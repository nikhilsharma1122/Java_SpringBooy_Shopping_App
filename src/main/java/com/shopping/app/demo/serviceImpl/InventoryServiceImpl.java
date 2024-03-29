package com.shopping.app.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.shopping.app.demo.custom.ProductOrderCustom;
import com.shopping.app.demo.custom.impl.ProductOrderCustomImpl;
import com.shopping.app.demo.entity.Coupon;
import com.shopping.app.demo.entity.Inventory;
import com.shopping.app.demo.entity.ProductOrder;
import com.shopping.app.demo.repository.CouponRepository;
import com.shopping.app.demo.repository.InventoryRepository;
import com.shopping.app.demo.repository.ProductOrderRepository;
import com.shopping.app.demo.service.InventoryService;
import com.shopping.app.web.model.CouponSO;
import com.shopping.app.web.model.ErrorResponse;
import com.shopping.app.web.model.InventorySO;
import com.shopping.app.web.model.Orders;
import com.shopping.app.web.model.PaymentFailedSO;
import com.shopping.app.web.model.PaymentSuccessfulSO;
import com.shopping.app.web.model.ProductOrderSO;

@Component
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	public InventoryRepository inventoryRepository;
	
	@Autowired
	public ProductOrderRepository productOrderRepository;
	
	@Autowired
	public CouponRepository couponRepository;
	
	@Autowired
	public ProductOrderCustomImpl productOrderCustom;

	@Override
	public InventorySO getInventory(String ordered) {
		Inventory inv = inventoryRepository.getInventory();
		System.out.println("Inv values: "+inv.toString());
		if(null!=ordered) {
			InventorySO invSo= new InventorySO();
			invSo.setOrdered(Integer.valueOf(ordered));
			invSo.setAvailable(inv.getAvailable()-Integer.valueOf(ordered));
			invSo.setPrice(inv.getPrice());
			return invSo;
		}
		else{
			InventorySO invSo= new InventorySO();
			invSo.setOrdered(inv.getOrdered());
			invSo.setAvailable(inv.getAvailable());
			invSo.setPrice(inv.getPrice());
			return invSo;
		}
	}

	@Override
	public CouponSO getFetchCoupons() {
		// TODO Auto-generated method stub
		CouponSO couponSo= new CouponSO();
		couponSo.setOFF5(5);
		couponSo.setOFF10(10);
		
		return couponSo;
	}

	@Override
	public ResponseEntity<?> createOrder(String userId, String quantity, String coupon) {
		// TODO Auto-generated method stub
		ProductOrder po = productOrderRepository.findByUserId(Integer.valueOf(userId));
		if(Integer.valueOf(quantity)<1) {
			ErrorResponse err = new ErrorResponse("Invalid quantity");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);		
		}
		if(null!=coupon) {
			Coupon cou = couponRepository.findByCouponCode(coupon);
			if(null==cou) {
				ErrorResponse err = new ErrorResponse("Invalid coupon");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);	
			}
			else if(null!=cou && null!=cou.getCouponStatus() && "Used".equalsIgnoreCase(cou.getCouponStatus())) {
				ErrorResponse err = new ErrorResponse("Invalid coupon");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
			}
		}
		if(null!=po) {
			ProductOrderSO prdOrSo= new ProductOrderSO();
			prdOrSo.setOrderId(po.getOrderId());
			prdOrSo.setUserId(Integer.valueOf(userId));
			prdOrSo.setQuantity(Integer.valueOf(quantity));
			prdOrSo.setCoupon(coupon);
			int totalprice= po.getAmount()*(Integer.valueOf(quantity));
			int couponDis = extractIntegerFromStringEnd(coupon);
			int discountPrice = totalprice - (totalprice*couponDis)/100;
			prdOrSo.setAmount(discountPrice);
			po.setCoupon(coupon);
			productOrderRepository.saveAndFlush(po);
			Coupon cou = couponRepository.findByCouponCode(coupon);
			cou.setCouponStatus("Used");
			couponRepository.saveAndFlush(cou);
			return ResponseEntity.ok(prdOrSo);
		}
		else {
			return null;
		}
	}
	public  int extractIntegerFromStringEnd(String str) {
        // Define a regular expression pattern to match digits at the end of the string
        Pattern pattern = Pattern.compile("\\d+$");
        Matcher matcher = pattern.matcher(str);

        // Find the last occurrence of the pattern in the string
        if (matcher.find()) {
            // Extract the matched digits and parse them to an integer
            String numStr = matcher.group();
            int intValue = Integer.parseInt(numStr);
            return intValue;
        } else {
            // If no digits found at the end of the string, return 0 or throw an exception based on your requirements
            // Here, returning 0 as a default value
            return 0;
        }
    }

	@Override
	public ResponseEntity<?> makePayment(String userId, String orderId, String amount) {
		// TODO Auto-generated method stub
		// Mock responses
        String[] transactionIds = {"tran010100001", "tran010100002", "tran010100003",
                "tran010100004", "tran010100005", "tran010100006"};

        String[] statuses = {"successful", "failed", "failed", "failed", "failed", "failed"};

        String[] descriptions = {null, "Payment Failed as amount is invalid",
                "Payment Failed from bank", "Payment Failed due to invalid order id",
                "No response from payment server", "Order is already paid for"};

        // Generate a random index
        int randomIndex = new Random().nextInt(transactionIds.length);

        // Prepare response
        String transactionId = transactionIds[randomIndex];
        String status = statuses[randomIndex];
        String description = descriptions[randomIndex];  
    	
        if (status.equals("successful")) {
        	ProductOrder po = productOrderRepository.findByOrderId( Integer.valueOf(orderId));
        	po.setOrderId(Long.valueOf(orderId));
        	po.setCoupon("OFF5");
        	po.setAmount(Integer.valueOf(amount));
        	po.setUserId(Integer.valueOf(userId));
        	po.setStatus("successful");
        	po.setTransaction(transactionId);
        	po.setOrderDate(new Date());
        	productOrderRepository.saveAndFlush(po);
        	return ResponseEntity.status(HttpStatus.OK)
                    .body(new PaymentSuccessfulSO(Long.valueOf(userId), Integer.valueOf(orderId), transactionId, status));
        } 
        else if(transactionId.equalsIgnoreCase("tran010100005")) {
        	productOrderCustom.saveProductOrder(Long.valueOf(orderId), "OFF5", Integer.valueOf(amount), new Date(), transactionId, status, Integer.valueOf(userId));
        	return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                    .body(new PaymentFailedSO(Long.valueOf(userId), Integer.valueOf(orderId), transactionId, status, description));
        }
        else if(transactionId.equalsIgnoreCase("tran010100006")) {
        	productOrderCustom.saveProductOrder(Long.valueOf(orderId), "OFF5", Integer.valueOf(amount), new Date(), transactionId, status, Integer.valueOf(userId));
        	return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body(new PaymentFailedSO(Long.valueOf(userId), Integer.valueOf(orderId), transactionId, status, description));
        }
        else {
        	productOrderCustom.saveProductOrder(Long.valueOf(orderId), "OFF5", Integer.valueOf(amount), new Date(), transactionId, status, Integer.valueOf(userId));
        	 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                     .body(new PaymentFailedSO(Long.valueOf(userId), Integer.valueOf(orderId), transactionId, status, description));
        }
	}

	@Override
	public List<Orders> getOrders() {
		// TODO Auto-generated method stub
		List<ProductOrder> po = productOrderRepository.getOrders();
		List<Orders> orderlist = new ArrayList<>();
		for(ProductOrder val:po) {
			Orders or = new Orders(val.getOrderId(), val.getAmount(), val.getOrderDate(), val.getCoupon());
			orderlist.add(or);
		}
		return 	orderlist;
		
	}

	@Override
	public ResponseEntity<?> getOrdersByOrderId(String orderId) {
		// TODO Auto-generated method stub
		List<ProductOrder> po = productOrderRepository.getOrderId(Long.valueOf(orderId));
		if(null!=po) {
		List<Orders> orderlist = new ArrayList<>();
		for(ProductOrder val:po) {
			Orders or = new Orders(val.getOrderId(), val.getAmount(), val.getOrderDate(), val.getCoupon(), val.getTransaction(), val.getStatus());
			orderlist.add(or);
		}
		return ResponseEntity.status(HttpStatus.OK)
                .body(orderlist);
		}else {
			ErrorResponse err = new ErrorResponse(Long.valueOf(orderId), "Order not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		}
	}

	
}
