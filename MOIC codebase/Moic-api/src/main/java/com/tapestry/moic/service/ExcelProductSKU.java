package com.tapestry.moic.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.entity.FinalizedBuy;
import com.tapestry.moic.service.interfaces.IExcelService;

@Component
public class ExcelProductSKU implements IExcelService {

	@Value("${order.colname}")
	String orderRows;

	public ByteArrayInputStream exportOrder(List<FinalizedBuy> orderList) {
		SimpleDateFormat formatter = new SimpleDateFormat(MoicConstant.MMDDYYYY);
		String[] splitRow = orderRows.split(",");
		Workbook workBook = new XSSFWorkbook();

		Sheet sheet = workBook.createSheet("orderList");
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

		int i = 0;
		for (FinalizedBuy finalizedBuy : orderList) {

			Row dataRow = sheet.createRow(i + 1);

			dataRow.createCell(0).setCellValue(finalizedBuy.getChannel());
			dataRow.createCell(1).setCellValue(finalizedBuy.getPreBuySKU());
			dataRow.createCell(2).setCellValue(finalizedBuy.getSeason());
			dataRow.createCell(3).setCellValue(finalizedBuy.getSKU());
			dataRow.createCell(4).setCellValue(finalizedBuy.getStyleColor());
			dataRow.createCell(5).setCellValue(finalizedBuy.getStyleNumber());
			dataRow.createCell(6).setCellValue(finalizedBuy.getStyleName());
			dataRow.createCell(7).setCellValue(finalizedBuy.getMaterialName());
			dataRow.createCell(8).setCellValue(finalizedBuy.getColorName());
			dataRow.createCell(9).setCellValue(finalizedBuy.getColorCode());
			dataRow.createCell(10).setCellValue(formatter.format(finalizedBuy.getSkuIntroDate()));
			dataRow.createCell(11).setCellValue(finalizedBuy.getDepartment());
			dataRow.createCell(12).setCellValue(finalizedBuy.getParentsClass());
			dataRow.createCell(13).setCellValue(finalizedBuy.getRetailPrice());
			dataRow.createCell(14).setCellValue(finalizedBuy.getTargetCost());
			dataRow.createCell(15).setCellValue(finalizedBuy.getCoo());
			dataRow.createCell(16).setCellValue(finalizedBuy.getTotalBuyQuantity());
			dataRow.createCell(17).setCellValue(formatter.format(finalizedBuy.getScheduledDeliveryDate()));
			dataRow.createCell(18).setCellValue(finalizedBuy.getOrderNotes());
			/*
			 * dataRow.createCell(19).setCellValue(finalizedBuy.getExFactJan());
			 * dataRow.createCell(20).setCellValue(finalizedBuy.getExFactFeb());
			 * dataRow.createCell(21).setCellValue(finalizedBuy.getExFactMar());
			 */
			dataRow.createCell(22).setCellValue(formatter.format(finalizedBuy.getInStoreDate()));
			dataRow.createCell(23).setCellValue(finalizedBuy.getVendor());
			dataRow.createCell(24).setCellValue(finalizedBuy.getPurchaseGroup());
			dataRow.createCell(25).setCellValue(finalizedBuy.getStyleDescription());
			dataRow.createCell(26).setCellValue(finalizedBuy.getStorageLocation());
			dataRow.createCell(27).setCellValue(finalizedBuy.getSite());
			dataRow.createCell(28).setCellValue(finalizedBuy.getStockSegment());
			dataRow.createCell(29).setCellValue(finalizedBuy.getNewness());
			dataRow.createCell(30).setCellValue(finalizedBuy.getShippingInstruction());
			dataRow.createCell(31).setCellValue(finalizedBuy.getExcludeFromSOCreation());
			dataRow.createCell(32).setCellValue(finalizedBuy.getSalesDocType());
			dataRow.createCell(33).setCellValue(finalizedBuy.getSalesOrg());
			dataRow.createCell(34).setCellValue(finalizedBuy.getDistributionChannel());

			dataRow.createCell(35).setCellValue(finalizedBuy.getDivision());
			dataRow.createCell(36).setCellValue(finalizedBuy.getSoldToNumber());
			dataRow.createCell(37).setCellValue(finalizedBuy.getShipToNumber());
			dataRow.createCell(38).setCellValue(formatter.format(finalizedBuy.getSoCancelDate()));
			dataRow.createCell(39).setCellValue(finalizedBuy.getPoNumber());
			dataRow.createCell(40).setCellValue(finalizedBuy.getPoType());
			dataRow.createCell(41).setCellValue(finalizedBuy.getOrderReason());
			dataRow.createCell(42).setCellValue(finalizedBuy.getReqSegment());
			dataRow.createCell(43).setCellValue(finalizedBuy.getTarget());

			sheet.autoSizeColumn(i);
			i = i + 1;
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workBook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				workBook.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return new ByteArrayInputStream(outputStream.toByteArray());

	}

}
