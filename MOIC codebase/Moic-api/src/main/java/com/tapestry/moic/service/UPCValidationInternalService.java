package com.tapestry.moic.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tapestry.moic.repository.FinalizedBuyRepository;
import com.tapestry.moic.service.interfaces.IUPCValidationService;

@Component
public class UPCValidationInternalService implements IUPCValidationService {
	
	@Autowired
	FinalizedBuyRepository finalizedBuyRepository;

	private Logger LOGGER = LoggerFactory.getLogger(UPCValidationInternalService.class);

	@Value("${upc.download.sheetname}")
	String upcSheetName;
	@Value("${upc.download.columnname}")
	String upcColumnname;



	@Override
	public ByteArrayInputStream downloadUPCValidationBySeason(List<String> season) {

		LOGGER.debug("Inside downloadUPCValidationBySeason( " + season + " ) columnname from tmpl : " + upcColumnname);

		Set<String> uniqueSKUSet = finalizedBuyRepository.getAllSKUsBySeasonCodeAndUPCIsNull(season);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String[] splitRow = upcColumnname.split(",");
		Workbook workBook = new XSSFWorkbook();

		Sheet sheet = workBook.createSheet(upcSheetName);
		Row row = sheet.createRow(0);
		CellStyle headerCellStyle = workBook.createCellStyle();
		headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());

		int count = 0;
		Cell cell;
		for (String str : splitRow) {
			cell = row.createCell(count);
			cell.setCellValue(str);
			cell.setCellStyle(headerCellStyle);
			count++;
		}

		int result = 0;
		if(!uniqueSKUSet.isEmpty()) {
			for (String skuId : uniqueSKUSet) {

				Row dataRow = sheet.createRow(result + 1);
				LOGGER.debug("Inside downloadUPCValidationBySeason() dataRow" + dataRow.getPhysicalNumberOfCells());

				dataRow.createCell(0).setCellValue(skuId);

				sheet.autoSizeColumn(result);
				result = result+ 1;
			}
		}

		try {
			workBook.write(outputStream);
		} catch (IOException e) {
			LOGGER.error("Inside downloadUPCValidationBySeason() IOException in File writing :" + e.getMessage());
		} finally {
			try {
				workBook.close();
			} catch (IOException e) {
				LOGGER.error("Inside downloadUPCValidationBySeason() IOException in File closing :" + e.getMessage());
			}
		}
		return new ByteArrayInputStream(outputStream.toByteArray());
	}
}