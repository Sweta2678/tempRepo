package com.coach.middleware.batch.itemProcessor;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemProcessor;

import com.coach.middleware.batch.dao.ESSSTYLECOLORExtractDao;
import com.coach.middleware.batch.dao.LPPRICESExtractDao;
import com.coach.middleware.batch.dao.VO.ESSSTYLECOLORExtractVO;
import com.coach.middleware.util.PMDBUtil;

public class ESSSTYLECOLORExtractItemProcessor implements
ItemProcessor<ESSSTYLECOLORExtractVO, ESSSTYLECOLORExtractVO>{
	private DataSource dataSource;
	boolean fetchAttrStoreDet = true;
	public ESSSTYLECOLORExtractDao ESSSTYLECOLORExtractDao;
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public ESSSTYLECOLORExtractVO process(ESSSTYLECOLORExtractVO item) throws Exception {
		if(fetchAttrStoreDet){
			ESSSTYLECOLORExtractDao.deleteESSSTYLECOLORTable();
			fetchAttrStoreDet=false;
		}
		//System.out.println("Item :***************** "+item.getSTYLE() + " ** "+item.getCOLOR());
		String introdate = item.getINTRODATE();
		String fsintrodate = item.getFSINTRODATE();
		String deletedate = item.getDELETEDATE();
		String fsdeletedate = item.getFSDELETEDATE();
		//System.out.println("**"+introdate+"**"+fsintrodate+"**"+deletedate+"**"+fsdeletedate);
		if(checkForFiscalYear(introdate,fsintrodate,deletedate,fsdeletedate)){
			//System.out.println("Item : "+ item.getSTYLE() + "********"+ item.getCOLOR());
			if(item.getCOLOR()==null || item.getCOLOR().equals("") )
				item.setCOLOR("NA");
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
		return null;
	}

	public ESSSTYLECOLORExtractDao getESSSTYLECOLORExtractDao() {
		return ESSSTYLECOLORExtractDao;
	}

	public void setESSSTYLECOLORExtractDao(
			ESSSTYLECOLORExtractDao eSSSTYLECOLORExtractDao) {
		ESSSTYLECOLORExtractDao = eSSSTYLECOLORExtractDao;
	}


	private boolean checkForFiscalYear(String introdate,String fsintrodate,String deletedate,
			String fsdeletedate) {
		boolean addItem = false;
		if(!introdate.equalsIgnoreCase("")){
			//System.out.println("introdate has value");
			int introYear = Integer.parseInt(introdate.substring(introdate.indexOf("FY")+2, introdate.length()));
			if(introYear<10){
			//	System.out.println("introyear < 10");
				if(!deletedate.equalsIgnoreCase("")){
				//	System.out.println("deletedate has value");
					int deleteYear = Integer.parseInt(deletedate.substring(deletedate.indexOf("FY")+2, deletedate.length()));
					int deleteMonth =Integer.parseInt(deletedate.substring(0, deletedate.indexOf("/")));
					if(deleteYear>=10){
					//	System.out.println("deletedate >10");
						if(deleteYear==10 && deleteMonth<7){
							addItem=false;	
						}else{
						addItem=true;
						}
					}
					else{
					//	System.out.println("deletedate <10");
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
					//System.out.println("fsdeletedate has value");
					int deleteFsYear = Integer.parseInt(fsdeletedate.substring(fsdeletedate.indexOf("FY")+2, fsdeletedate.length()));
					int deleteFsMonth =Integer.parseInt(fsdeletedate.substring(0, fsdeletedate.indexOf("/")));
					if(deleteFsYear>=10){
						//System.out.println("fsdeletedate >10");
						if(deleteFsYear==10 && deleteFsMonth<7){
							addItem=false;	
						}else{
						addItem=true;
						}
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
			//System.out.println("introdate and fsintrodate null");
			addItem=true;
		}
		//System.out.println("----------------");
		return addItem;
		
	}


}
