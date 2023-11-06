/**
 * 
 */
/**
 * @author AB263159
 *
 */
package com.coach.middleware.batch.itemReader;




import java.util.*;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemReader;  
import org.springframework.batch.item.ParseException;  
import org.springframework.batch.item.UnexpectedInputException;  
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.coach.middleware.batch.dao.TotoExtractDAO;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;
import com.coach.middleware.batch.itemProcessor.TotoExtractItemProcessor;
  
  
public class TotoExtractItemReader implements ItemReader<String>{  
   
 public String read() throws Exception, UnexpectedInputException,  
   ParseException {  
	 
	 
		 
	 return null;
	 
 }
   
}  