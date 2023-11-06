import java.text.SimpleDateFormat;

import com.coach.middleware.extractloader.ProductRenameExtract;


public class LaunchJobTest {
	
	public static void main(String args[]) {	
	ProductRenameExtract productrenameExtract = new ProductRenameExtract();	
	productrenameExtract.launchExtract("ProductRename","full","");
		
		System.out.println(new SimpleDateFormat("dd-MMM-yy HH:mm:ss a").format("16-NOV-11 12.00.00.000000000 AM"));

		
		String data ="06/26/2020 15:56:07 PM";
		
		String data1 = data.substring(0,19);
		
		
		
		System.out.println(data1);
		
		
	}
}
