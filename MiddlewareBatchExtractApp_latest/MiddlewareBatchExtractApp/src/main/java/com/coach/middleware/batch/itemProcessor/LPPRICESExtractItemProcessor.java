package com.coach.middleware.batch.itemProcessor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.coach.middleware.batch.dao.LPPRICESExtractDao;
import com.coach.middleware.batch.dao.RowMapper.LPPRICESExtractRowMapper;
import com.coach.middleware.batch.dao.VO.CjiPriceMOAVO;
import com.coach.middleware.batch.dao.VO.FullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.LPPRICESExtractVO;

public class LPPRICESExtractItemProcessor implements
		ItemProcessor<LPPRICESExtractVO, List<LPPRICESExtractVO>> {
	public static PrintWriter fileOutProcessed = null;
	public static PrintWriter fileOutRead = null;
	private static final Logger logger = Logger
			.getLogger(LPPRICESExtractItemProcessor.class);
	public static int index = 0;
	public static List<LPPRICESExtractVO> LPPRICESExtractVOList = new ArrayList();
	Map<String, List<FullPriceMOAVO>> styleAndMoa = new HashMap<String, List<FullPriceMOAVO>>();
	Map<String, List<CjiPriceMOAVO>> CjiStyleAndMoa = new HashMap<String, List<CjiPriceMOAVO>>();
	private static List<LPPRICESExtractVO> stylePricing = new ArrayList<LPPRICESExtractVO>();
	private static List<LPPRICESExtractVO> styleCjiPricing = new ArrayList<LPPRICESExtractVO>();
	private static List<LPPRICESExtractVO> deleteList = new ArrayList<LPPRICESExtractVO>();
	@Autowired
	private DataSource dataSource;
	public static int styleCount = 0;
	public static int recordCount = 0;
	public LPPRICESExtractDao LPPRICESExtractDao;
	private static String thisYear = "";
	private static String nextYear = "";
	private static String thirdYear = "";
	private static ArrayList months = new ArrayList();
	private static ArrayList priceList = new ArrayList();
	private static String cjiprevOwnerId = "";
	private static String prevOwnerId = "";
	private static Connection connection = null;
	boolean fetchAttrStoreDet = true;

	public LPPRICESExtractDao getLPPRICESExtractDao() {
		return LPPRICESExtractDao;
	}

	public void setLPPRICESExtractDao(LPPRICESExtractDao LPPRICESExtractDao) {
		this.LPPRICESExtractDao = LPPRICESExtractDao;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<LPPRICESExtractVO> process(LPPRICESExtractVO item) {
		List<FullPriceMOAVO> priceMoa = null;
		List<CjiPriceMOAVO> CjipriceMoa = null;
		try {
			if (index == 0) {
				if(fetchAttrStoreDet){
					LPPRICESExtractDao.deleteLPPRICESTable();
					fetchAttrStoreDet=false;
				}
				fileOutProcessed = new PrintWriter(new BufferedWriter(new FileWriter(
						"logs/StyleListProcessed.txt", true)));
				fileOutRead =new PrintWriter(new BufferedWriter(new FileWriter(
						"logs/StyleListRead.txt", true)));
				recordCount= recordCount + LPPRICESExtractRowMapper.LppriceVoList.size();
				logger.info("Process step started :: No of Records read : "
						+ LPPRICESExtractRowMapper.LppriceVoList.size());
				if (LPPRICESExtractRowMapper.LppriceVoList.size() > 0){
					priceMoa = gatherFullPriceMoa(LPPRICESExtractRowMapper.styleList);
					CjipriceMoa = gatherCjiPriceMoa(LPPRICESExtractRowMapper.styleList);
				}
				if (priceMoa != null) {
					gatherMoaForEachStyles(priceMoa);
					gatherCjiMoaForEachStyles(CjipriceMoa);
					calculatePriceSummary();
					//prevOwnerId = "";
					styleAndMoa.clear();
					priceMoa = null;
					calculateCjiPriceSummary();
					CjiStyleAndMoa.clear();
					CjipriceMoa=null;
				}
				index = 1;
			}
		} catch (Exception e) {
			logger.error("Exception in processing the records : "
					+ e.getLocalizedMessage());
		}
		return LPPRICESExtractVOList;
	}



	private void gatherMoaForEachStyles(List<FullPriceMOAVO> priceMoa) {
		try {
			logger.info("Processing MOA started : " + new Date());
			List<String> styleList = LPPRICESExtractRowMapper.styleList;
			List<FullPriceMOAVO> moaList = null;
			for (String style : styleList) {
				moaList = new ArrayList<FullPriceMOAVO>();
				for (FullPriceMOAVO vo : priceMoa) {
					if (style.equals(vo.getOWNERUNIQUEID())) {
						moaList.add(vo);
					}
				}
				styleAndMoa.put(style, moaList);
				moaList = null;
			}
			//styleList.clear();
			styleCount = styleCount + styleAndMoa.size();
			logger.info("MOA mapped with respective styles : "
					+ styleAndMoa.size());
		} catch (Exception e) {
			logger.error("Exception in parsing the MOA list in gatherMoaForEachStyles :"+e.getLocalizedMessage());
		}
	}

	public List<LPPRICESExtractVO> getLPPRICESExtractVOList() {
		return LPPRICESExtractVOList;
	}

	private List<FullPriceMOAVO> gatherFullPriceMoa(List<String> ownerId) {
		List<FullPriceMOAVO> moa = LPPRICESExtractDao
				.getFullPriceMOAList(ownerId);
		return moa;
	}
	private List<CjiPriceMOAVO> gatherCjiPriceMoa(List<String> ownerId) {
		List<CjiPriceMOAVO> moa = LPPRICESExtractDao
				.getCjiPriceMOAList(ownerId);
		return moa;
	}
	public void calculatePriceSummary() {
		try {
		//	System.out.println(" LPPRICESExtractRowMapper.LppriceVoList : "+ LPPRICESExtractRowMapper.LppriceVoList.size());
			for (LPPRICESExtractVO voObj : LPPRICESExtractRowMapper.LppriceVoList) {
				String style = voObj.getStyle();
				String sku = voObj.getSku();
				//logger.info(style + " "+sku);
				fileOutRead.println(frameStyleColor(style, sku));
				String ownerId = voObj.getOwnerUniqueID();
				Float stdCost = null;
				if (!ownerId.equals(prevOwnerId)) {
					//System.out.println("Test : "+ownerId);
					fileOutProcessed.println(frameStyleColor(style, sku));
					stylePricing.clear();
					List<FullPriceMOAVO> pricingHistory = styleAndMoa
							.get(ownerId);
					//System.out.println("pricingHistory size : "+pricingHistory.size());
					Calendar cal1 = Calendar.getInstance();
					cal1.setTimeInMillis(0);
					cal1.set(getStartOfFiscalYear(), 5, 1); // 0 is passed to
					//logger.info("Style/sku : "+style+" "+sku +" No of MOA rows --------------------------:"+pricingHistory.size())		;								// get current year;
					Date fiscalFrom = cal1.getTime();
					int row=1;
					for (FullPriceMOAVO price : pricingHistory) {
						/*for ( int i=1;i<= pricingHistory.size();i++) {
							FullPriceMOAVO price =pricingHistory.get(i);*/
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						Date fromDate = null;
						Date toDate = null;
						Float retailPrice = null;
						Float wholeSalePrice = null;
						Float cjiPrice = null;
						if (price.getSTARTDATE() != null)
							fromDate = price.getSTARTDATE();
						if (price.getENDDATE() != null)
							toDate = price.getENDDATE();
						if (price.getRETAILPRICE() != null)
							retailPrice = price.getRETAILPRICE().floatValue();
						if (price.getWHOLESALEPRICE() != null)
							wholeSalePrice = price.getWHOLESALEPRICE()
									.floatValue();
						Date date = null;
						Calendar cal = Calendar.getInstance();
						cal.setTimeInMillis(0);
						// to get the last year from current year(i.e.,to get
						// 2017 for
						// if the current year 2015)
						cal.set(getFiscalYear(2), 5, 30); // 2 is passed to get
															// the
															// third year from
															// current
															// year
						date = cal.getTime(); // get back a Date object
						if (fromDate != null) {
							if (toDate == null) {
								//System.out.println("=================>End date null");

								if (fromDate.after(fiscalFrom) && row==1) {
									//System.out.println("Assign fiscalfrom to startdate");
									fromDate = fiscalFrom;
								}
								getThisYearAttributes(ownerId, style, sku,
										fromDate, date, retailPrice, stdCost,
										wholeSalePrice, cjiPrice);
							} else if (fromDate.before(toDate)) {
								//System.out.println("=================>End date not null");
								if (fromDate.after(fiscalFrom) && row==1) {
									//System.out.println("Else Assign fiscalfrom to startdate");
									fromDate = fiscalFrom;
								}
								getThisYearAttributes(ownerId, style, sku,
										fromDate, date, retailPrice, stdCost,
										wholeSalePrice, cjiPrice);
							}

						}
						row++;
						}
					months.clear();
					pricingHistory = null;
					prevOwnerId = ownerId;
					// System.out.println(" Vo size for style :"+Jes.size());

				} else {/*
					fileOutProcessed.println(frameStyleColor(style, sku));
					for (int i = 0; i < stylePricing.size(); i++) {
						LPPRICESExtractVO vo;
						try {
							vo = (LPPRICESExtractVO) stylePricing.get(i)
									.clone();
							vo.setEsssku(frameStyleColor(style, sku));
							vo.setSku(sku);
							vo.setStyle(style);
							LPPRICESExtractVOList.add(vo);
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
					}
				*/}
			}
			//System.out.println("Processing MOA completed  : " + new Date());
			logger.info("Processing MOA completed  : " + new Date());
		} catch (Exception e) {
			logger.error("Filed to process the pricing MOA : "
					+ e.getLocalizedMessage());
		}
	}

	private void getThisYearAttributes(String ownerId, String style,
			String sku, Date fromDate, Date toDate, Float retailPrice,
			Float stdCost, Float wholeSalePrice, Float cjiPrice) {
		try {
			thisYear = "FY" + Integer.toString(getFiscalYear(0)).substring(2);
			nextYear = "FY"
					+ Integer.toString(getFiscalYear(0) + 1).substring(2);
			thirdYear = "FY"
					+ Integer.toString(getFiscalYear(0) + 2).substring(2);
			Calendar start = Calendar.getInstance();
			start.setTime(fromDate);
			Calendar end = Calendar.getInstance();
			end.setTime(toDate);
			for (Date date = start.getTime(); !start.after(end); start.add(
					Calendar.DATE, 1), date = start.getTime()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH) + 1;
				String fiscalYear = getFiscalYear(month, year);
				int fiscalPeriod = getFiscalPeriod(month, year);
				int period = 0;
				String ownerYearPeriodStr = sku + "-" + ownerId + "-"
						+ fiscalYear + "-" + "P" + fiscalPeriod;

				if (fiscalYear.equals(thisYear) || fiscalYear.equals(nextYear)
						|| fiscalYear.equals(thirdYear)) {
					// System.out.println("ownerYearPeriodStr =============="+ownerYearPeriodStr);
					if (month >= 7) {
						period = month - 6;
					} else if (month <= 6) {
						period = 6 + month;
					}
					if (!months.contains(ownerYearPeriodStr)) {
						months.add(ownerYearPeriodStr);
						LPPRICESExtractVO pr = new LPPRICESExtractVO();
						pr.setOwnerUniqueID(ownerId);
						pr.setEsssku(frameStyleColor(style, sku));
						pr.setSku(sku);
						pr.setStyle(style);
						pr.setYearPeriodStr(ownerYearPeriodStr);
						pr.setStartDate(fromDate);
						pr.setEndDate(toDate);
						pr.setFiscalPeriod(framePeriod(fiscalPeriod));
						if (month >= 7)
							pr.setCentury(year + 1);
						else
							pr.setCentury(year);
						pr.setRetailPrice(getFloatValue(retailPrice));
						// pr.setCjiPrice(getFloatValue(cjiPrice));
						pr.setStdCost(getFloatValue(stdCost));
						pr.setWholeSalePrice(getFloatValue(wholeSalePrice));
						//pr.setCjiPrice(getFloatValue(cjiPrice));
						pr.setFiscalYear(fiscalYear);
						LPPRICESExtractVOList.add(pr);
						stylePricing.add(pr);
						pr = null;
						fromDate = null;// toDate=null;p=0;price=0.0;
					} else if (months.contains(ownerYearPeriodStr)) {
						Iterator it = LPPRICESExtractVOList.iterator();
						LPPRICESExtractVO summary;
						while (it.hasNext()) {
							summary = (LPPRICESExtractVO) it.next();
							if (summary.getYearPeriodStr().equals(
									ownerYearPeriodStr)) {
								summary.setRetailPrice(getFloatValue(retailPrice));
								summary.setStdCost(getFloatValue(stdCost));
								//summary.setCjiPrice(getFloatValue(cjiPrice));
								summary.setWholeSalePrice(getFloatValue(wholeSalePrice));
							}
						}
					}
				}
				
			}
			/*System.out.println("List :");
			for(Object p : months){
				System.out.println("P :"+p);
			}*/
		} catch (Exception e) {
			logger.error(" The creating price history fails due to  : "
					+ e.getLocalizedMessage());
		}
	}
	private void getThisYearCjiAttributes(String ownerId, String style,
			String sku, Date fromDate, Date toDate, Float cjiPrice) {
		try {
			thisYear = "FY" + Integer.toString(getFiscalYear(0)).substring(2);
			nextYear = "FY"
					+ Integer.toString(getFiscalYear(0) + 1).substring(2);
			thirdYear = "FY"
					+ Integer.toString(getFiscalYear(0) + 2).substring(2);
			Calendar start = Calendar.getInstance();
			start.setTime(fromDate);
			Calendar end = Calendar.getInstance();
			end.setTime(toDate);
			for (Date date = start.getTime(); !start.after(end); start.add(
					Calendar.DATE, 1), date = start.getTime()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH) + 1;
				String fiscalYear = getFiscalYear(month, year);
				int fiscalPeriod = getFiscalPeriod(month, year);
				int period = 0;
				String ownerYearPeriodStr = sku + "-" + ownerId + "-"
						+ fiscalYear + "-" + "P" + fiscalPeriod;

				if (fiscalYear.equals(thisYear) || fiscalYear.equals(nextYear)
						|| fiscalYear.equals(thirdYear)) {
				
					if (month >= 7) {
						period = month - 6;
					} else if (month <= 6) {
						period = 6 + month;
					}
					Iterator it = LPPRICESExtractVOList.iterator();
					LPPRICESExtractVO summary;
					while (it.hasNext()) {
						summary = (LPPRICESExtractVO) it.next();
						if (summary.getYearPeriodStr().equals(
								ownerYearPeriodStr)) {
							summary.setCjiPrice(getFloatValue(cjiPrice));
							//styleCjiPricing.add(summary);
						}
					}
					//logger.info("styleCjiPricing size:"+styleCjiPricing.size());
				
				}
				
			}
			
		} catch (Exception e) {
			logger.error(" The creating price history fails due to  : "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	private static int getFiscalPeriod(int month, int year) {
		int period = 0;
		if (month >= 7) {
			period = month - 6;
		} else if (month <= 6) {
			period = 6 + month;
		}
		return period;
	}

	private static String getFiscalYear(int month, int year) {
		int p = 0;
		String fiscalYear = "";
		if (month >= 7) {
			p = month - 6;
			year = year + 1;
			fiscalYear = "FY" + (Integer.toString(year).substring(2, 4));
			// fiscalYear.substring(arg0, arg1)
		} else if (month <= 6) {
			p = 6 + month;
			fiscalYear = "FY" + Integer.toString(year).substring(2, 4);
		}
		return fiscalYear;
	}

	// to get the current fiscal year, value passed will be 0
	public static int getFiscalYear(int yearCount) {
		Calendar rightNow = Calendar.getInstance();
		int currentMonth = rightNow.get(Calendar.MONTH);
		int currentYear = rightNow.get(Calendar.YEAR);
		int tempYear = 0;
		if (currentMonth >= Calendar.JULY) { // && currentMonth <=
												// Calendar.JANUARY) {
			tempYear = currentYear + 1;
		} else {
			tempYear = currentYear;
		}
		// tempYear = tempYear.substring(2);
		return tempYear + yearCount;
	}

	private String framePeriod(int period) {

		if (period > 9) {
			String returnString = "Period " + period;

			return returnString;
		} else if (period == 0) {

			return "Period 01";
		} else {
			String returnString = "Period 0" + period;

			return returnString;
		}
	}

	public static String frameStyleColor(String styleNumber, String colorCode) {
		String styleColor = "";

		// 1. Validation
		if (styleNumber.length() >= 6
				&& styleNumber.substring(5).equals("") == false) {
			// append style & color
			styleColor = new StringBuffer(styleNumber).append(
					rightTrim(colorCode)).toString();
		} else if (styleNumber.length() >= 5
				&& styleNumber.substring(4).equals("") == false) {
			// append style, space and color
			styleColor = new StringBuffer(styleNumber).append(space(1))
					.append(rightTrim(colorCode)).toString();
		} else if (styleNumber.length() >= 4
				&& styleNumber.substring(3).equals("") == false) {
			// append style, 2 space and color
			styleColor = new StringBuffer(styleNumber).append(space(2))
					.append(rightTrim(colorCode)).toString();
		} else {
			// append style, 3 space and color
			styleColor = new StringBuffer(styleNumber).append(space(3))
					.append(rightTrim(colorCode)).toString();
		}

		// return the style color

		return styleColor;
	}

	public static boolean isNullOrEmpty(String value) {

		if (value != null && value.trim().length() > 0) {
			return false;
		}

		return true;
	}

	public static String space(int numberOfSpaces) {
		// return the string
		StringBuffer returnString = new StringBuffer(5);

		for (int i = 0; i < numberOfSpaces; i++) {
			returnString.append(" ");
		}

		String returnString2 = returnString.toString();

		return returnString2;
	}

	public static String rightTrim(String value) {
		// validate
		if (isNullOrEmpty(value)) {
			return "";
		}
		// compute the length
		int valueArrayCount = value.length();
		int i = 0;

		// iterate the array
		for (i = valueArrayCount; i > 0; i--) {
			// check for non empty string
			if ((value.substring(i - 1, i)).equals(" ") == true) {
				value = value.substring(0, i - 1);
				// exit the for loop
				// break;
			} else {
				// no space available
				break;
			}
		}

		// remove the blanks from the end
		/*
		 * if (i > 0) { value = value.substring(0, i); }
		 */

		// return the value
		/*
		 * if (logger.isDebugEnabled()) { logger.debug("rightTrim(String) -
		 * end"); }
		 */
		return value;
	}

	public static Float getFloatValue(Object value) {

		// return value
		Float returnValue = new Float("0.0");
		try {
			if (value != null) {
				returnValue = new Float(Float.parseFloat(value.toString()));
			}
		} catch (NumberFormatException formatException) {
			// logger.error("getFloatValue(String)", formatException);
		}
		// return the value

		return returnValue;
	}

	private int getStartOfFiscalYear() {
		Calendar rightNow = Calendar.getInstance();
		int currentMonth = rightNow.get(Calendar.MONTH);
		int currentYear = rightNow.get(Calendar.YEAR);
		int tempYear = 0;
		if (currentMonth <= Calendar.JUNE) {
			tempYear = currentYear - 1;
		} else {
			tempYear = currentYear;
		}
		return tempYear;
	}
	
	private void calculateCjiPriceSummary() {
		try {
			for (LPPRICESExtractVO voObj : LPPRICESExtractRowMapper.LppriceVoList) {
				String style = voObj.getStyle();
				String sku = voObj.getSku();
				
				fileOutRead.println(frameStyleColor(style, sku));
				String ownerId = voObj.getOwnerUniqueID();
				Float stdCost = null;
			if (!ownerId.equals(cjiprevOwnerId)) {
				//System.out.println("-----------------------"+style+" "+sku);
					fileOutProcessed.println(frameStyleColor(style, sku));
					styleCjiPricing.clear();
					deleteList.clear();
					List<CjiPriceMOAVO> pricingHistory = CjiStyleAndMoa
							.get(ownerId);
					Calendar cal1 = Calendar.getInstance();
					cal1.setTimeInMillis(0);
					cal1.set(getStartOfFiscalYear(), 5, 1); // 0 is passed to
					//logger.info("Style/sku : "+style+" "+sku +" No of MOA rows --------------------------:"+pricingHistory.size())		;								// get current year;
					Date fiscalFrom = cal1.getTime();
					int row=1;
					for (CjiPriceMOAVO price : pricingHistory) {
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						Date cjifromDate = null;
						Date cjitoDate = null;
						Float cjiPrice = null;
						if (price.getCJISTARTDATE() != null)
							cjifromDate = price.getCJISTARTDATE();
						if (price.getCJIENDDATE() != null)
							cjitoDate = price.getCJIENDDATE();
						if (price.getCJIPRICE() != null)
							cjiPrice = price.getCJIPRICE().floatValue();
						Date date = null;
						Calendar cal = Calendar.getInstance();
						cal.setTimeInMillis(0);
						cal.set(getFiscalYear(2), 5, 30); 
						date = cal.getTime(); // get back a Date object
						if (cjifromDate != null) {
							if (cjitoDate == null) {
								if (cjifromDate.after(fiscalFrom) && row==1) {
									cjifromDate = fiscalFrom;
								}
								getThisYearCjiAttributes(ownerId, style, sku,
										cjifromDate, date,  cjiPrice);
							} else if (cjifromDate.before(cjitoDate)) {
								if (cjifromDate.after(fiscalFrom) && row==1) {
									cjifromDate = fiscalFrom;
								}
								getThisYearCjiAttributes(ownerId, style, sku,
										cjifromDate, date,  cjiPrice);
							}

						}
						row++;
						}
					LPPRICESExtractVO temp;
					for (int i = 0; i < LPPRICESExtractVOList.size(); i++) {
						temp = LPPRICESExtractVOList.get(i);
						if (temp.getEsssku().equals(
								frameStyleColor(style, sku))) {
							styleCjiPricing.add(temp);
						}
						/*if (temp.getOwnerUniqueID().equals(ownerId)) {
							deleteList.add(temp);
						}*/
						
					}
					/*LPPRICESExtractVOList.removeAll(deleteList);
					LPPRICESExtractVOList.addAll(styleCjiPricing);*/
					
					pricingHistory = null;
					cjiprevOwnerId = ownerId;
					// System.out.println(" Vo size for style :"+Jes.size());
				
				} else {
					//System.out.println(style+" "+sku )		;	
					fileOutProcessed.println(frameStyleColor(style, sku));
					for (int i = 0; i < styleCjiPricing.size(); i++) {
						LPPRICESExtractVO vo;
						try {
							vo = (LPPRICESExtractVO) styleCjiPricing.get(i)
									.clone();
							vo.setEsssku(frameStyleColor(style, sku));
							vo.setSku(sku);
							vo.setStyle(style);
							LPPRICESExtractVOList.add(vo);
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
					}
				}
			
			//System.out.println("styleCjiPricing size:"+styleCjiPricing.size());
			//System.out.println("LPPRICESExtractVOList size:"+LPPRICESExtractVOList.size());
			}
			//System.out.println("Processing MOA completed  : " + new Date());
			logger.info("Processing CJI MOA completed  : " + new Date());
		} catch (Exception e) {
			logger.error("Filed to process the pricing CJI  MOA : "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	private void gatherCjiMoaForEachStyles(List<CjiPriceMOAVO> cjipriceMoa) {

		try {
			logger.info("Processing CJI MOA started : " + new Date());
			List<String> styleList = LPPRICESExtractRowMapper.styleList;
			List<CjiPriceMOAVO> moaList = null;
			for (String style : styleList) {
				moaList = new ArrayList<CjiPriceMOAVO>();
				for (CjiPriceMOAVO vo : cjipriceMoa) {
					if (style.equals(vo.getOWNERUNIQUEID())) {
						moaList.add(vo);
					}
				}
				CjiStyleAndMoa.put(style, moaList);
				moaList = null;
			}
			styleList.clear();
			logger.info("MOA mapped with respective styles : "
					+ CjiStyleAndMoa.size());
		} catch (Exception e) {
			logger.error("Exception in parsing the CJI MOA list in gatherMoaForEachStyles :"+e.getLocalizedMessage());
		}
	}


}
