package com.tapestry.moic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tapestry.moic.entity.JoorOrderStyleItem;
import com.tapestry.moic.entity.key.JoorOrderStyleItemKeys;

@Repository
public interface JoorOrderStyleItemRepository extends JpaRepository<JoorOrderStyleItem, JoorOrderStyleItemKeys>{
	

	@Query(value = "select distinct joi.styletagvalue from joororderstyleitem joi where joi.styletagcode = 'Fashion Season' or joi.styletaggroup = 'Fashion Season'", nativeQuery = true)
	List<String> getTagValuesByTagCodeFashionSeason();
	
	@Query(value = "select distinct orderid from joororderstyleitem where styletagvalue =?1", nativeQuery = true)
	List<Long> getOrderIdBySeasonCode(String styletagvalue);

	@Query(value = "select distinct joi.styletagvalue from joororderstyleitem joi where joi.orderId =?1 and joi.styletaggroup = 'Fashion Season'", nativeQuery = true)
	List<String> getDistinctFashionSeasonByOrderId(Long orderId);
	
	@Query(value = "select styletagvalue from joororderstyleitem where itemcolorcode =?1 and itemnumber =?2 and itemsize =?3 and itemstyleid =?4 and orderid =?5 and (styletaggroup  =?6 or styletagname = ?6 )", nativeQuery = true)
	String getStyleItemTagValue(String itemColorCode, String itemNumber, String itemSize, Long itemStyleId, Long orderId, String clssOrSubClass);
	
	@Transactional
	@Modifying
	@Query(value = "delete from joororderstyleitem joi where joi.orderId =?1", nativeQuery = true)
	void deleteOrderStyleItemByOrderId(Long orderId);
}
