package com.shopping.app.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.shopping.app.demo.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	
	@Query("select i from Inventory i ")
	public Inventory getInventory();
}
