package com.tapestry.moic.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.dto.PreBuyDto;
import com.tapestry.moic.entity.MOQ;

public class ExcelHelper {

	private static Logger logger = LoggerFactory.getLogger(ExcelHelper.class);

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!(MoicConstant.EXCELTYPE.equals(file.getContentType())
				|| MoicConstant.EXCELTYPE2.equals(file.getContentType()))) {
			logger.debug(MessageFormat.format("Format not matched : Type1: {0} Type2: {1}. CurrentFileType: {2}", MoicConstant.EXCELTYPE ,MoicConstant.EXCELTYPE2,file.getContentType()));
			return false;

		}

		return true;
	}

	public static boolean checkFileName(String filename, MultipartFile file) {
	
		String filename1 = filename + ".xlsx";
		String filename2 = filename + ".xls";

		if (!(filename1.equals(file.getOriginalFilename()) || filename2.equals(file.getOriginalFilename()))) {			
			logger.debug(MessageFormat.format("File name not matched {0}", file.getOriginalFilename()));
			return false;
		}

		return true;

	}

	public static boolean isRowEmpty(Row row) {
		boolean isEmpty = true;
		DataFormatter dataFormatter = new DataFormatter();

		if (row != null) {
			for (Cell cell : row) {
				if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
					isEmpty = false;
					break;
				}
			}
		}

		return isEmpty;
	}

	public static String checkStringNull(Cell currentCell) {
		DataFormatter formatter = new DataFormatter();
		// When passed a null or blank cell, this method will return an emptyString
		// ("").
		// Formulas in formula type cells will not be evaluated.

		String strValue = formatter.formatCellValue(currentCell);
		if (strValue.trim().length() > 255) {
			throw new RuntimeException(currentCell.getAddress() + " is greater then 255: ");
		}
		return strValue;
	}
	
	public static boolean covertBoolean(Cell currentCell) {
		// When passed a null or blank cell, this method will return an emptyString
		// ("").
		// Formulas in formula type cells will not be evaluated.
		String value = checkStringNull(currentCell);
		if (value.trim().length() > 1) {
			if (value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("true")) {
				return true;
			} else if (value.equalsIgnoreCase("no") || value.equalsIgnoreCase("false")) {
				return false;
			}else {
				throw new RuntimeException(currentCell.getAddress() + " Must have value yes/no OR true/false. ");
			}
		}

		return false;
	}
	
	

	public static boolean checkMandatory(Cell currentCell) {
		DataFormatter formatter = new DataFormatter();
		// When passed a null or blank cell, this method will return an emptyString
		// ("").
		// Formulas in formula type cells will not be evaluated.
		String strValue = formatter.formatCellValue(currentCell);
		if (currentCell ==null) {			
			throw new RuntimeException("Mandatory field Can't be Blank.");
		}else if(strValue.trim().length() < 1) {
			throw new RuntimeException("Mandatory field Can't be Blank.");
		}
		return true;
	}

	public static Date dateFormat(Date date) {

		Date dateValue = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			String dateString = format.format(date);
			dateValue=format.parse(dateString);

		} catch (Exception e) {
			logger.error(MessageFormat.format("Date ParseException : {0}", e.getMessage()));			
		}
		return dateValue;
	}

	private static List<String> getHeaderData(Sheet sheet) {
		List<String> headers = new ArrayList<>();
		Row row;
		int headerId = 0;
		if (sheet.getRow(headerId) != null && sheet.getRow(headerId).cellIterator() != null) {
			row = sheet.getRow(headerId);

			for (int header = 0; header <= row.getLastCellNum(); header++) {
				if (row.getCell((short) header) != null && row.getCell((short) header).toString().length() != 0) {
					headers.add(row.getCell((short) header).toString());
				}
			}

		}
		return headers;
	}

	public static boolean checkSheetAndHeaders(String sheetName, String moicHeader[], MultipartFile file) {

		boolean result = true;
		List<String> headers = null;
		Workbook workbook = null;
		logger.debug(MessageFormat.format("Check Sheet And Headers values : Headers:{0}. sheetName:{1}",moicHeader, sheetName));
		try {
			InputStream inputStream = file.getInputStream();
			if (MoicConstant.EXCELTYPE.equals(file.getContentType()))
				workbook = new XSSFWorkbook(inputStream);
			if (MoicConstant.EXCELTYPE2.equals(file.getContentType()))
				workbook = new HSSFWorkbook(inputStream);
			if(workbook!=null) {
			Sheet sheet = workbook.getSheet(sheetName.trim());
			if (sheet != null) {
				headers = getHeaderData(sheet);
				if ((moicHeader != null ) && (moicHeader.length == headers.size())) {
					
					for (int col = 0; col < moicHeader.length; col++) {
						if (!moicHeader[col].equals(headers.get(col))) {							
							logger.debug(MessageFormat.format("{0} ColumnName Not matched {1}",moicHeader[col],headers.get(col)));
							result = false;
							break;
						}
						
						logger.debug(MessageFormat.format("{0} ColumnName  matched {1}",moicHeader[col],headers.get(col)));

					}

				} else { // sheet headers check					
					if (moicHeader != null) {
					logger.debug(MessageFormat.format("{0} Workbook Headers and template headers Size {1} not matched.",moicHeader.length,headers.size()));
					}
					workbook.close();
					return false;
				}

			} else { // sheet name check
				logger.debug("Workbook SHEET name not found :");
				workbook.close();
				return false;
			}

			workbook.close();
		}

		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(MoicConstant.SOMETHING_WENT_WRONG + "\nThe file encountered some issues while processing.");
		}

		return result;
	}

	@SuppressWarnings("deprecation")
	public static List<MOQ> excelToMOQs(String sheetName, MultipartFile file,int headersLength) {

		Workbook workbook = null;

		try {
			logger.debug(" Inside excelToMOQs....");
			if (MoicConstant.EXCELTYPE.equals(file.getContentType()))
				workbook = new XSSFWorkbook(file.getInputStream());
			if (MoicConstant.EXCELTYPE2.equals(file.getContentType()))
				workbook = new HSSFWorkbook(file.getInputStream());

			Sheet sheet = workbook.getSheet(sheetName);

			Iterator<Row> rows = sheet.iterator();

			List<MOQ> moqs = new ArrayList<MOQ>();
			logger.debug(" Inside excelToMOQs.....sheet..." + sheet);
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				if(ExcelHelper.isRowEmpty(currentRow)) {break;}
				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				MOQ moqInfo = new MOQ();
				
				List<Cell> cells = new ArrayList<Cell>();
				int lastColumn = Math.max(currentRow.getLastCellNum(), headersLength);
				for (int cn = 0; cn < lastColumn; cn++) {
					Cell c = currentRow.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
					cells.add(c);
				}
				int cellIdx = 0;				
				for(Cell currentCell :cells) {
				
					logger.debug(" cellIdx processing : " + cellIdx);
					switch (cellIdx) {			
						
					case 0:
						checkMandatory(currentCell);
						moqInfo.setSeasonCode(checkStringNull(currentCell));						 
						break;
					case 1:
						checkMandatory(currentCell);
						moqInfo.setSku(checkStringNull(currentCell));
						break;
					case 2:
						checkMandatory(currentCell);
						moqInfo.setDelete(ExcelHelper.covertBoolean(currentCell));
						break;
					case 3:
						checkMandatory(currentCell);
						Date rawDate = new Date(currentCell.toString());
						moqInfo.setEffectiveDate(dateFormat(rawDate));
						break;
			
					default:
						break;
					}

					cellIdx++;
				}
				moqInfo.setCreateDate(new Date());
				moqInfo.setCreatedBy("MOQ Upload");	
				moqs.add(moqInfo);			

			}

			workbook.close();
			return moqs;
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(MoicConstant.SOMETHING_WENT_WRONG + "\nThe file encountered some issues while processing.");
		}
	}

	public static List<PreBuyDto> excelToPreBuys(String sheetName, MultipartFile file, int headersLength) {

			Workbook workbook = null;
		try {
			logger.debug(" Inside excelToPreBuys.");

			if (MoicConstant.EXCELTYPE.equals(file.getContentType()))
				workbook = new XSSFWorkbook(file.getInputStream());
			if (MoicConstant.EXCELTYPE2.equals(file.getContentType()))
				workbook = new HSSFWorkbook(file.getInputStream());

			Sheet sheet = workbook.getSheet(sheetName.trim());

			Iterator<Row> rows = sheet.iterator();

			List<PreBuyDto> preBuys = new ArrayList<PreBuyDto>();
			
			int rowNumber = 0;
			
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				logger.debug(MessageFormat.format("Rown Number: {0}",currentRow.getRowNum()+2));
				if (ExcelHelper.isRowEmpty(currentRow)) {
					break;
				}
				if (rowNumber == 0) {

					rowNumber++;
					continue;
				}

				PreBuyDto preBuy = new PreBuyDto();

				List<Cell> cells = new ArrayList<Cell>();
				int lastColumn = Math.max(currentRow.getLastCellNum(), headersLength);
				for (int cn = 0; cn < lastColumn; cn++) {
					Cell c = currentRow.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
					cells.add(c);
				}
				int cellIdx = 0;				
				for(Cell currentCell :cells) {
					logger.debug(MessageFormat.format("Cell Id: {0}", cellIdx));
					switch (cellIdx) {
					case 0:
						checkMandatory(currentCell);
						preBuy.setSeasonCode(checkStringNull(currentCell));
						break;
					case 1:
						checkMandatory(currentCell);
						preBuy.setSku(checkStringNull(currentCell));
						break;
					case 2:
						checkMandatory(currentCell);
						preBuy.setIsPreBuySKU(ExcelHelper.covertBoolean(currentCell));
						break;
					case 3:
						checkMandatory(currentCell);
						preBuy.setCoo(checkStringNull(currentCell));
						break;
					default:
						break;
					}

					cellIdx++;
				}

				preBuys.add(preBuy);
			}

			workbook.close();
			return preBuys;
		} catch (Exception e) {
//			logger.error(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(MoicConstant.SOMETHING_WENT_WRONG + "\nThe file encountered some issues while processing.");
		}

	}

}
