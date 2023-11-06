package com.tapestry.moic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tapestry.moic.entity.JoorOrderItem;
import com.tapestry.moic.entity.key.JoorOrderItemsKeys;

@Repository
public interface JoorOrderItemRepository extends JpaRepository<JoorOrderItem, JoorOrderItemsKeys> {

	JoorOrderItem findByOrderIDAndItemName(long orderId, String itemName);

	List<JoorOrderItem> findByCombinedSku(String combinedSku);
	
	List<Long> getOrderIDByCombinedSku(String combinedSku);
	
	List<JoorOrderItem> findByImpactedByMoqSkuChange(Boolean impactedByMoqSkuChange);
	
	@Transactional
	@Modifying
	@Query(value = "delete from joororderitem joi where joi.orderId =?1", nativeQuery = true)
	void deleteOrderItemsByOrderId(Long orderId);
	
	@Transactional
	@Modifying
	@Query(value = "update joororderitem set impactedbymoqskuchange = false", nativeQuery = true)
	void setAllImpactedByMoqSkuChangeToFalse();
}
