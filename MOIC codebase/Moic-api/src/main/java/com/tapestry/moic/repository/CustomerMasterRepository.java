package com.tapestry.moic.repository;

import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.CustomerMaster;
import com.tapestry.moic.entity.key.CustomerMasterKeys;

@Repository
public interface CustomerMasterRepository extends CrudRepository<CustomerMaster, CustomerMasterKeys> {

	List<CustomerMaster> findByIsActive(Boolean isActive);

	List<CustomerMaster> findByIsActiveAndDownloadFlag(Boolean isActive, Boolean downloadFlag);
	
	List<CustomerMaster> findAllOrderedBySoldToNumber();
	
	List<CustomerMaster>  findBySoldToNumberOrderByCreateDateDesc(String soldToNumber);
	
	List<CustomerMaster>  findBySoldToNumberAndShipToNumberAndIsActiveOrderByCreateDateDesc(String soldToNumber, String shipToNumber, Boolean isActive);
	
	@Query(value="select c1.* from  customermaster c1 where c1.sendtojoor = true and c1.isActive = true and c1.createdate >= (NOW() - INTERVAL '24 HOURS') OR c1.modifieddate >= (NOW() - INTERVAL '24 HOURS') and c1.suggestedretailpricecurrency is not null and c1.wholesalepricecurrency is not null and c1.channelid is not null order by c1.soldtonumber", nativeQuery=true)
	List<CustomerMaster> findAllCustomerGroupBySoldToNumberOrderByCreateDate();

	List<CustomerMaster>  findBySoldToNumberAndChannelIDOrderByCreateDateDesc(String soldToNumber, Integer channelID);
	
	@Query(value="select * from customermaster where userid=?1  and (modifieddate between ?2 and ?3 )", nativeQuery=true)
	List<CustomerMaster> findCustomerAuditDetail(String userId, Date fromdate,Date todate);
	
	@Query(value="select * from customermaster where soldtonumber=?1 and isactive=true and sendtojoor=true and suggestedretailpricecurrency is not null and wholesalepricecurrency is not null and channelid is not null", nativeQuery = true)
	List<CustomerMaster> findBySoldToNumberAndIsActiveAndSendToJoor(String soldToNumber);
	
}
