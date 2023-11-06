package com.coach.middleware.batch.itemProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemProcessor;

import com.coach.middleware.batch.dao.ESSSTYLEExtractDao;
import com.coach.middleware.batch.dao.VO.ESSSTYLEExtractVO;
import com.coach.middleware.batch.dao.VO.LPPRICESExtractVO;

public class ESSSTYLEExtractItemProcessor implements
ItemProcessor<ESSSTYLEExtractVO, ESSSTYLEExtractVO>{
	private DataSource dataSource;
	public ESSSTYLEExtractDao ESSSTYLEExtractDao;
	boolean fetchAttrStoreDet = true;
	public static List<String> ESSSTYLEExtractVOList = new ArrayList();
	private List swDeptList = new ArrayList();
	private String swDepartments;
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public ESSSTYLEExtractVO process(ESSSTYLEExtractVO item) throws Exception {
		if(fetchAttrStoreDet){
			ESSSTYLEExtractDao.deleteESSSTYLETable();
			fetchAttrStoreDet=false;
		}
		
		if (item.getDEPARTMENT() != null && !item.getDEPARTMENT().isEmpty()) {
			if (swDeptList.contains(item.getDEPARTMENT().trim())) {
				return null;
			}
		}
		//System.out.println("Item :***************** "+item.getSTYLE());
		String introdate = item.getINTRODATE();
		String fsintrodate = item.getFSINTRODATE();
		String deletedate = item.getDELETEDATE();
		String fsdeletedate = item.getFSDELETEDATE();
		//System.out.println("**"+introdate+"**"+fsintrodate+"**"+deletedate+"**"+fsdeletedate);
		if(checkForDeleteFiscalYear(introdate,fsintrodate,deletedate,fsdeletedate)){
			if(!ESSSTYLEExtractVOList.contains(item.getSTYLE().toString())){
			ESSSTYLEExtractVOList.add(item.getSTYLE().toString());
			if(item.getDEPARTMENT()==null || item.getDEPARTMENT().equals("") )
				item.setDEPARTMENT("NA");
			if(item.getCLASS()==null || item.getCLASS().equals("") )
				item.setCLASS("NA");
			if(item.getDEPTCLASS()==null || item.getDEPTCLASS().equals("") )
				item.setDEPTCLASS("NA");
			if(item.getSUBCLASS()==null || item.getSUBCLASS().equals("") )
				item.setSUBCLASS("NA");
			return item;
			}
		}	
		return null;
	}

	public ESSSTYLEExtractDao getESSSTYLEExtractDao() {
		return ESSSTYLEExtractDao;
	}

	public void setESSSTYLEExtractDao(
			ESSSTYLEExtractDao ESSSTYLEExtractDao) {
		this.ESSSTYLEExtractDao = ESSSTYLEExtractDao;
	}
	private boolean checkForDeleteFiscalYear(String introdate,String fsintrodate,String deletedate,
			String fsdeletedate) {
		boolean addItem = false;
		if(!introdate.equalsIgnoreCase("")){
			//System.out.println("introdate has value");
			int introYear = Integer.parseInt(introdate.substring(introdate.indexOf("FY")+2, introdate.length()));
			if(introYear<10){
				//System.out.println("introyear < 10");
				if(!deletedate.equalsIgnoreCase("")){
					//System.out.println("deletedate has value");
					int deleteYear = Integer.parseInt(deletedate.substring(deletedate.indexOf("FY")+2, deletedate.length()));
					if(deleteYear>10){
						//System.out.println("deletedate >10");
						addItem=true;
					}
					else{
						//System.out.println("deletedate <10");
						addItem=false;
					}
				}
				else{
					//System.out.println("deletedate null");
					addItem=true;
				}
			}
			if(introYear>9){
				//System.out.println("introdate > 9");
				addItem=true;
			}
		}
		if(!addItem && !fsintrodate.equalsIgnoreCase("")){
			int introFsYear = Integer.parseInt(fsintrodate.substring(fsintrodate.indexOf("FY")+2, fsintrodate.length()));
			if(introFsYear<10){
				//System.out.println("fsintroyear < 10");
				if(!fsdeletedate.equalsIgnoreCase("")){
				//	System.out.println("fsdeletedate has value");
					int deleteFsYear = Integer.parseInt(fsdeletedate.substring(fsdeletedate.indexOf("FY")+2, fsdeletedate.length()));
					if(deleteFsYear>10){
					//	System.out.println("fsdeletedate >10");
						addItem=true;
					}
				}
				else{
					//System.out.println("fsdeletedate is null");
					addItem=true;
				}
			}else{
				//System.out.println("fsintroyear >10");
				addItem=true;
			}
		}
		if(fsintrodate.equalsIgnoreCase("") && introdate.equalsIgnoreCase("")){
			addItem=true;
		}
		return addItem;
		
	}
	
	public void setSWDepartmentlist() {
		if(swDepartments != null && !swDepartments.isEmpty())
		{
			String[] swDeptArray = swDepartments.split(",");
			swDeptList = Arrays.asList(swDeptArray); 
		}			
	}

	/**
	 * @return the swDepartments
	 */
	public String getSwDepartments() {
		return swDepartments;
	}

	/**
	 * @param swDepartments the swDepartments to set
	 */
	public void setSwDepartments(String swDepartments) {
		this.swDepartments = swDepartments;
	}

}
