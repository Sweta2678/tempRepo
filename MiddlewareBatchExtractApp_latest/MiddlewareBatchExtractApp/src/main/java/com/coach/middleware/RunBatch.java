	/**
	 * @author Raveen Raj
	
	 *
	 */
	package com.coach.middleware;
	
	import java.util.Date;
	import java.util.Properties;
	
	import javax.mail.Message;
	import javax.mail.MessagingException;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.AddressException;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeMessage;
	
	import org.apache.log4j.Logger;
	import org.springframework.batch.core.JobParameters;
	import org.springframework.batch.core.JobParametersBuilder;
	import org.springframework.context.ApplicationContext;
	import org.springframework.context.support.ClassPathXmlApplicationContext;
	
import com.coach.middleware.batch.dao.DeltaExtractDaoImpl;
	import com.coach.middleware.extractloader.ExtractFactory;
	import com.coach.middleware.extractloader.IExtractLoader;
	import com.coach.middleware.util.CoachExtractproperties;
	 
	public class RunBatch {
		public static int fetchsize = 2500;
		public JobParameters param = null;
		public JobParametersBuilder builder = new JobParametersBuilder();
		private static final Logger logger = Logger.getLogger(RunBatch.class);
		Date starttime;
		Date endTime;
		String jobStatus;
		public RunBatch(String[] args) throws Exception{
			Long val = System.currentTimeMillis();
			starttime = new Date();
			String mode="";
			String extractName=args[0];
			String propertyPath = "";
			String mailContent="Extract "+extractName+" started at :: " + starttime  ;
			System.out.println(mailContent);
			ExtractFactory factory = ExtractFactory.getExtractFactoy();
			IExtractLoader loader = factory.getExtract(extractName);
			if(args.length>1){
				 mode = args[1];
				 System.out.println("Extract mode : "+mode);
			}
			if(args.length>2){
				propertyPath = args[2];
				System.out.println("propertyPath : "+propertyPath);
			}
			//args[0] Name of Extracts
			//args[1] mode of execution (Full || Delta)
			//args[2] property for the Extract
			String masterPropertyPath = propertyPath.concat("/").concat(extractName).concat(".properties");
			//String masterPropertyPath = "I:/workspace/MiddlewareBatchExtractApp/src/main/resources/com/coach/middleware/batch/config/properties/"+args[0]+".properties";
			System.out.println("Loads propery file in path :"+masterPropertyPath);
			boolean status = loader.launchExtract(extractName, mode,masterPropertyPath);
			System.out.println("status :"+status);
			val = System.currentTimeMillis()-val;
			endTime = new Date();
			if(extractName.equalsIgnoreCase("DELTAEXTRACT")){
				if(status)
					jobStatus = " SUCCESS ";
					//mailContent=mailContent.concat(" Extarcted completion status : SUCCESS ").concat(" \n ");
				else
					jobStatus = " FAILED ";
					//mailContent=mailContent.concat(" Extarcted completion status : FAILED ").concat(" \n ");	
				long diff=endTime.getTime()-starttime.getTime();
				long diffinMins=diff/(60*1000)%60;
				long diffSec=diff/1000%60;
				//mailContent=mailContent.concat("Extract "+extractName+" completed at :: "+ endTime).concat(" \n ");	
				//mailContent=mailContent.concat("Total time taken : "+diffinMins+"mins "+diffSec+"sec");
				triggerMail(mailContent,extractName,masterPropertyPath);
			}
			System.out.println("*******Extract "+extractName+" completed********"+ endTime);
			
		}
	  private void triggerMail(String content,String extract,String property) throws AddressException {
		  CoachExtractproperties coachExtractproperties = new CoachExtractproperties(
				  property);
		  String to = CoachExtractproperties.properties.getProperty("to");
		  String from = CoachExtractproperties.properties.getProperty("from");
		  String host = CoachExtractproperties.properties.getProperty("host");
		  String subject = CoachExtractproperties.properties.getProperty("subject");
		  long diff=endTime.getTime()-starttime.getTime();
			long diffinMins=diff/(60*1000)%60;
			long diffSec=diff/1000%60;
			//mailContent=mailContent.concat("Extract "+extractName+" completed at :: "+ endTime).concat(" \n ");	
		  StringBuilder tableContent = new StringBuilder();
		  //tableContent.append("<html><body><style>table,th,td{border:2px solid}</style><table><tr><th>No of Records</th><th>LPSKU</th><th>PRODMAST</th><th>TOTO</th><th>LPPRICES</th></tr><tr><td>1000</td><td>8mins58sec</td><td>43sec</td><td>20Sec</td><td>8mins23sec</td></tr><tr><td>5000</td><td>10mins</td><td>1min13sec</td><td>29sec</td><td>15mins</td></tr><tr><td>10000</td><td>11mins<br>22sec</td><td>2min2sec</td><td>32sec</td><td>36mins</td></tr><tr><td>25000</td><td>16mins<br>14sec</td><td>4min 40sec</td><td>1min</td><td>68mins</td></tr><tr><td>50000</td><td>25mins<br>7sec</td><td>9mins</td><td>1min 22sec</td><td>132mins 34sec</td></tr><tr><td>Full dump</td><td>42mins44sec</td><td>22mins</td><td></td><td></td></tr></table></body></html>");
		  tableContent.append("<html><body><style>table,th,td{border:2px solid}</style><table><tr><th>No of Records</th><th>LPSKU</th><th>PRODMAST</th><th>LPPRICES</th></tr><tr><td>1000</td><td>8mins58sec</td><td>43sec</td><td>8mins23sec</td></tr><tr><td>5000</td><td>10mins</td><td>1min13sec</td><td>15mins</td></tr><tr><td>10000</td><td>11mins<br>22sec</td><td>2min2sec</td><td>36mins</td></tr><tr><td>25000</td><td>16mins<br>14sec</td><td>4min 40sec</td><td>68mins</td></tr><tr><td>50000</td><td>25mins<br>7sec</td><td>9mins</td><td>132mins 34sec</td></tr><tr><td>Full dump</td><td>42mins44sec</td><td>22mins</td><td>240mins 22sec</td></tr></table></body></html>");	 // String to = "rraj@coach.com,jmeganathancoach@coach.com";//change accordingly  
	     // String from = "PMDBBatchJob@coach.com";//change accordingly  
	      //String host = "EXCRELAY.global.coach.com";//or IP address  
	      String[] recepients = to.split(",");
	      InternetAddress[] address = new InternetAddress[recepients.length];
	      int counter=0;
	      for(String recepient :recepients){
	    	  address[counter] = new InternetAddress(recepient.trim());
	    	  counter++;
	      }
	     //Get the session object  
	      Properties properties = System.getProperties();  
	      properties.setProperty("mail.smtp.host", host);  
	      Session session = Session.getDefaultInstance(properties);  
	     //compose the message  
	      try{  
	         MimeMessage message = new MimeMessage(session);  
	         message.setFrom(new InternetAddress(from));  
	         message.addRecipients(Message.RecipientType.TO,address);  
	         message.setSubject(subject);  
	         message.setContent(content.concat("<br>"+"Extract completed at : "+endTime)
	        		 .concat("<br>"+"Extract completion status : "+jobStatus)
	        		 .concat("<br>"+"Total time taken : "+diffinMins+"mins "+diffSec+"sec")
				 .concat("<br>"+"Total no of Records Extracted : "+ DeltaExtractDaoImpl.insert)
	        		 .concat("<br><br><b><I>"+"Statistics for extracts : " + "</I></b><br><br>" +tableContent.toString()),"text/html");  
	         // Send message  
	         Transport.send(message);  
	         logger.info("RunBatch.triggerMail Mail sent successfully");  
	      }catch (MessagingException mex) {mex.printStackTrace();}  
		}
	public static void main(String[] args) {
		  try {
			new RunBatch(args);
		  } catch (Exception e) {
				e.printStackTrace();
		  }
	  }
	}