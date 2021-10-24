package com.wanwei.common.base.util;

import com.wanwei.common.base.constants.ErrorCodeEnum;
import com.wanwei.common.base.exception.BaseException;
import com.wanwei.common.base.vo.ExcelPar;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.*;


/**
 * excel 工具类
 * @Description:
 * @author qianbaoku
 * @date 2015-3-20
 */
@Slf4j
public class ExcelUtil {
	private static DecimalFormat NUMBER_DF = new DecimalFormat("############.######");
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String TIME_PATTERN = "HH:mm";
	/**
	 * 获取excel表格数据
	 * @param in excel文件输入流
	 * @return
	 */
	public static List<String[]> readExcel(InputStream in) {
		try {
			// 支持excel 2003/2007
			Workbook wb = WorkbookFactory.create(in);
			return readExcelData(wb);
		} catch (Exception e) {
			log.error("", e);
			throw new BaseException(ErrorCodeEnum.READ_EXCEL);
		}
	}

	/**
	 * 获取excel表格数据
	 * @param file excel文件绝对地址
	 * @return
	 */
	public static List<String[]> readExcel(String file) {
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			// 支持excel 2003/2007
			Workbook wb = WorkbookFactory.create(in);
			return readExcelData(wb);
		} catch (Exception e) {
			log.error("", e);
			throw new BaseException(ErrorCodeEnum.READ_EXCEL);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					log.error("", e);
				}
			}
		}
	}


	/**
	 * @Author zhangjing
	 * @Description //获取Workbook数据
	 * @Date 9:32 2018/9/6
	 * @Param [wb]
	 * @return java.util.List<java.lang.String[]>
	 **/
	public static List<String[]> readExcelData(Workbook wb) {
		try {
			List<String[]> data = new ArrayList<String[]>();

			for (int i = 0, le = wb.getNumberOfSheets(); i < le; i++) {
				Sheet sheet = wb.getSheetAt(i);
				Iterator<Row> rowIt = sheet.rowIterator();
				while (rowIt.hasNext()) {
					Row row = rowIt.next();
					if (row == null || row.getLastCellNum() <= 0) {
						continue;
					}
					String[] rowStr = new String[row.getLastCellNum()];
					for (int j = 0, len = row.getLastCellNum(); j < len; j++) {
						Cell cell = row.getCell(j);
						rowStr[j] = getCellValue(cell);
					}

					data.add(rowStr);
				}
			}
			return data;
		} catch (Exception e) {
			log.error("", e);
			throw new BaseException(ErrorCodeEnum.READ_EXCEL);
		}
	}

	/**
	 * 获取工作簿
	 * @param in
	 * @return
	 */
	public static Workbook initWorkbook(InputStream in) {
		try {
			// 支持excel 2003/2007
			Workbook wb = WorkbookFactory.create(in);
			return wb;
		} catch (Exception e) {
			log.error("", e);
			throw new BaseException(ErrorCodeEnum.READ_EXCEL);
		}
	}

	/**
	 * 获取内容
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}
		String cellValue = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			Double dou = cell.getNumericCellValue();
			if (DateUtil.isCellDateFormatted(cell)) {
				Date date = DateUtil.getJavaDate(dou);
				if (dou > 1) {
					if (new Double(dou - dou.intValue()).equals(0d)) {
						cellValue = DateFormatUtils.format(date, DATE_PATTERN);
					} else {
						cellValue = DateFormatUtils.format(date, DATE_TIME_PATTERN);
					}
				} else {
					cellValue = DateFormatUtils.format(date, TIME_PATTERN);
				}
			} else {
				cellValue = NUMBER_DF.format(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_STRING:
			cellValue = cell.getRichStringCellValue().toString();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			cellValue = "" + cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			cellValue = cell.getCellFormula();
			break;
		case Cell.CELL_TYPE_BLANK:
			cellValue = "";
			break;
		default:
			cellValue = "";
			break;
		}
		return cellValue.trim();
	}

	/**
	 * 创建单元格并设置样式,值
	 * 2021-01-16 update by zj 处理不同数据按照不同数据形式导出已经空值处理
	 * @param wb
	 * @param row
	 * @param column
	 * @param value
	 */
	public static Cell createCell(Workbook wb, Row row, int column, Object value, Font font, CellStyle style) {
		Cell cell = row.createCell(column);

		if( value != null){
			if(value instanceof Double) {
				cell.setCellValue((Double) value);
			} else if(value instanceof Integer) {
				cell.setCellValue((Integer) value);
			} else if(value instanceof Date) {
				cell.setCellValue((Date) value);
			} else if(value instanceof Calendar) {
				cell.setCellValue((Calendar) value);
			} else {
				cell.setCellValue(value.toString());
			}

		} else{
			cell.setCellValue("--");
		}
		if(style==null){
			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_BOTTOM);
			cellStyle.setFont(font);
			cell.setCellStyle(cellStyle);
		}else{
			cell.setCellStyle(style);
		}
		return cell;
	}


	public static Cell createCellBorder(Workbook wb, Row row, int column, String value, Font font,Short alignment) {
		    Cell cell = row.createCell(column);
		    cell.setCellValue(value);
			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setFont(font);
			cell.setCellStyle(cellStyle);
			XSSFCellStyle datastyle = (XSSFCellStyle)wb.createCellStyle();
			XSSFColor borderColor = new XSSFColor(new byte[] { 28,28,28});
			datastyle.setBorderColor(XSSFCellBorder.BorderSide.TOP,borderColor);
			datastyle.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM,borderColor);
			datastyle.setBorderColor(XSSFCellBorder.BorderSide.LEFT,borderColor);
			datastyle.setBorderColor(XSSFCellBorder.BorderSide.RIGHT,borderColor);
			datastyle.setBorderBottom(BorderStyle.THIN);
			datastyle.setBorderTop(BorderStyle.THIN);
			datastyle.setBorderLeft(BorderStyle.THIN);
			datastyle.setBorderRight(BorderStyle.THIN);
			if(alignment != null){
				datastyle.setAlignment(alignment);
			}else {
				datastyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			}
			datastyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			datastyle.setFont(font);
			cell.setCellStyle(datastyle);
		    return cell;
	}

	/**
	 * 设置字体
	 * @param wb
	 * @return
	 */
	public static Font createFonts(Workbook wb, short bold, String fontName, boolean isItalic, short hight) {
		Font font = wb.createFont();
		font.setFontName(fontName);
		font.setBoldweight(bold);
		font.setItalic(isItalic);
		font.setFontHeight(hight);
		return font;
	}

	/**
	 * 创建单元格并设置样式,值 自定义文字位置
	 * @param wb
	 * @param row
	 * @param column
	 * @param halign
	 * @param value
	 */
	public static void createCells(Workbook wb, Row row, int column, String value, Font font, short halign) {
		Cell cell = row.createCell(column);

		cell.setCellValue(value);
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(halign);//设置水平对齐方式
		cell.setCellStyle(cellStyle);
	}


	/**
	 * @Author zhangjing
	 * @Description //[data 数据, head 数据的列头]
	 * @Date 14:16 2018/9/6
	 * @Param [data, head]
	 * @return org.apache.poi.ss.usermodel.Workbook
	 **/
	public static Workbook writeExcel(List<Object[]> data, String[] head){
		return writeExcel(data,head,null,null,null,null,null);
	}

	/**
	 * @Author zhangjing
	 * @Description //[data 数据, head 数据的列头,title 标题]
	 * @Date 14:18 2018/9/6
	 * @Param [data, head, title]
	 * @return org.apache.poi.ss.usermodel.Workbook
	 **/
	public static  Workbook writeExcel(List<Object[]> data, String[] head,String title){
		return writeExcel(data,head,title,null,null,null,null);
	}
	/**
	 * @Author zhangjing
	 * @Description // [data 数据, head 数据的列头, title 标题, time 标题下的时间落款, headTop 对于数据列头上的标题, headTopCol 对于数据列头上的标题每个占用的列数]
	 * @Date 14:17 2018/9/6
	 * @Param [data, head, title, time, headTop, headTopCol]
	 * @return org.apache.poi.ss.usermodel.Workbook
	 **/
	public static Workbook writeExcel(List<Object[]> data, String[] head, String title, String time, String[] headTop,int[] headTopCol){
		return writeExcel(data,head,title,time,headTop,headTopCol);
	}
	/**
	 * @Author zhangjing
	 * @Description //[data 数据, head 数据的列头, title 标题, time 标题下的时间落款, endStr 结尾落款]
	 * @Date 14:17 2018/9/6
	 * @Param [data, head, title, time, endStr]
	 * @return org.apache.poi.ss.usermodel.Workbook
	 **/
	public static Workbook writeExcel(List<Object[]> data, String[] head, String title, String time, String endStr) {
		return writeExcel(data,head,title,time,null,null,endStr);
	}
	/**
	 * @Author zhangjing
	 * @Description //[data 数据, head 数据的列头, title 标题, time 标题下的时间落款, headTop 对于数据列头上的标题, headTopCol 对于数据列头上的标题每个占用的列数, endStr 结尾落款]
	 * @Date 14:26 2018/9/6
	 * @Param [data, head, title, time, headTop, headTopCol, endStr]
	 * @return org.apache.poi.ss.usermodel.Workbook
	 **/
	public static Workbook writeExcel(List<Object[]> data, String[] head, String title, String time, String[] headTop,int[] headTopCol,String endStr){
		return writeExcel(data, head, title, time, headTop, headTopCol, endStr, null);
	}
	/**
	 * @Author zhangjing
	 * @Description //[data 数据, head 数据的列头, title 标题, time 标题下的时间落款, headTop 对于数据列头上的标题, headTopCol 对于数据列头上的标题每个占用的列数, endStr 结尾落款,sheetName]
	 * @Date 14:18 2018/9/6
	 * @Param [data, head, title, time, headTop, headTopCol, endStr,sheetName]
	 * @return org.apache.poi.ss.usermodel.Workbook
	 **/
	public static Workbook writeExcel(List<Object[]> data, String[] head, String title, String time, String[] headTop,int[] headTopCol,String endStr,String sheetName) throws BaseException {
		try {

			Workbook wb = new XSSFWorkbook();// 在内存中最多保持100记录，多余的会写入临时文件中disk
			Sheet sheet;
			if(sheetName!=null) {
				sheet= wb.createSheet(sheetName);
			} else {
				sheet= wb.createSheet();
			}
			sheetName = sheet.getSheetName();
			int colNums=0;
			if(head!=null) {
				colNums = head.length;
			} else if(data!=null && data.size()>0 ) {
				colNums = data.get(0).length;
			}
			if (title != null) {
				writeSheetExcelTitle( wb, title, sheetName, colNums);
			}
			if(time!=null) {
				writeSheetExcelTime( wb, time, sheetName,colNums);
			}
			if ( headTop !=null ) {
				writeSheetExcelHeadFirst( wb, headTop,headTopCol,sheetName);
			}
			if (head != null) {
				writeSheetExcelHead( wb,head,sheetName);
			}
			if(data!=null) {
				writeSheetExcelData(wb,data, sheetName);
			}
			if(endStr != null) {
				writeSheetExcelEnd( wb, endStr, sheetName,colNums);
			}
			return wb;
		} catch (Exception e) {
			log.error("", e);
			throw new BaseException(ErrorCodeEnum.EXPORT_EXCEL);
		}
	}

	/**
	 * @Author zhangjing
	 * @Description //对象方式传参，同writeExcel方法相同
	 * @Date 13:26 2018/9/7
	 * @Param [par]
	 * @return org.apache.poi.ss.usermodel.Workbook
	 **/
	public static Workbook writeExcel(ExcelPar par){
		try {
			// 在内存中最多保持100记录，多余的会写入临时文件中disk
			Workbook wb = new XSSFWorkbook();
			Sheet sheet;
			if(par.getSheetName()!=null) {
				sheet= wb.createSheet(par.getSheetName());
			} else {
				sheet= wb.createSheet();
			}
			par.setSheetName( sheet.getSheetName());
			int colNums=0;
			if(par.getHead()!=null) {
				colNums = par.getHead().length-1;
			} else if(par.getData()!=null && par.getData().size()>0 ) {
				colNums = par.getData().get(0).length-1;
			}
			if (par.getTitle() != null) {
				writeSheetExcelTitle( wb, par.getTitle(), par.getSheetName(), colNums);
			}
			if(par.getTime()!=null) {
				writeSheetExcelTime( wb, par.getTime(), par.getSheetName(),colNums);
			}
			if ( par.getHeadTop() !=null ) {
				writeSheetExcelHeadFirst( wb, par.getHeadTop(),par.getHeadTopCol(),par.getSheetName());
			}
			if (par.getHead() != null) {
				writeSheetExcelHead( wb,par.getHead(),par.getSheetName());
			}
			if(par.getData()!=null) {
				writeSheetExcelData(wb,par.getData(), par.getSheetName());
			}
			if(par.getEndStr() != null) {
				writeSheetExcelEnd( wb, par.getEndStr(), par.getSheetName(),colNums);
			}
			return wb;
		} catch (Exception e) {
			log.error("", e);
			throw new BaseException(ErrorCodeEnum.EXPORT_EXCEL);
		}
	}

	/**
	 * @Author zhangjing
	 * @Description //标题写入
	 * @Date 10:29 2018/9/7
	 * @Param [wb, title, sheetName, colNums]
	 * @return void
	 **/
	public static void writeSheetExcelTitle(Workbook wb,String title,String sheetName,int colNums){
		Sheet sheet=wb.getSheet(sheetName);
		int rowNum = sheet.getLastRowNum();
		Row row = sheet.createRow(rowNum);
		Font font = createFonts(wb, Font.BOLDWEIGHT_BOLD, "宋体", false, (short) 350);
		CellStyle datastyle = wb.createCellStyle();
		datastyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		datastyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		datastyle.setFont(font);
		createCell(wb, row, rowNum, title, font,datastyle);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, colNums-1));

	}
	/**
	 * @Author zhangjing
	 * @Description //时间题头写入
	 * @Date 10:30 2018/9/7
	 * @Param [wb, time, sheetName,colsNum]
	 * @return void
	 **/
	public static void writeSheetExcelTime(Workbook wb,String time,String sheetName,int colsNum){
		Sheet sheet=wb.getSheet(sheetName);
		int rowNum = sheet.getLastRowNum();
		// 起始时间
		if(rowNum!=0 || sheet.getRow(0)!=null)rowNum++;
		Row row = sheet.createRow(rowNum);
		Font font = createFonts(wb, Font.BOLDWEIGHT_BOLD, "宋体", false, (short) 200);
		createCells(wb, row, 0, time, font, CellStyle.ALIGN_RIGHT);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, colsNum-1));

	}

	/**
	 * @Author zhangjing
	 * @Description //数据列头的上一级列头
	 * @Date 10:33 2018/9/7
	 * @Param [wb, headTop, headTopCol, sheetName]
	 * @return void
	 **/
	public static void writeSheetExcelHeadFirst(Workbook wb,String[] headTop,int[] headTopCol,String sheetName){
		Sheet sheet=wb.getSheet(sheetName);
		int rowNum = sheet.getLastRowNum();
		if(rowNum!=0 || sheet.getRow(0)!=null)rowNum++;
		Row row = sheet.createRow(rowNum);
		Font font = createFonts(wb, Font.BOLDWEIGHT_BOLD, "宋体", false, (short) 200);
		int colNum =0;
		for (short i = 0; i < headTop.length; i++) {
			for(int j=0;j<headTopCol[i];j++){
				createCell(wb, row,colNum+j , "", font,null);
			}
			createCell(wb, row,colNum , headTop[i], font,null);
			CellRangeAddress add = new CellRangeAddress(rowNum, rowNum, colNum, colNum+headTopCol[i]-1 );
			sheet.addMergedRegion(add);

			colNum+=headTopCol[i];
		}
	}

	/**
	 * @Author zhangjing
	 * @Description //数据列头
	 * @Date 10:34 2018/9/7
	 * @Param [wb, head, sheetName]
	 * @return void
	 **/
	public static void writeSheetExcelHead(Workbook wb,String[] head,String sheetName){
		Sheet sheet=wb.getSheet(sheetName);
		int rowNum = sheet.getLastRowNum();
		// 设置excel头
		if(rowNum!=0 || sheet.getRow(0)!=null)rowNum++;
		Row row = sheet.createRow(rowNum);
		Font font = createFonts(wb, Font.BOLDWEIGHT_BOLD, "宋体", false, (short) 200);
		for (short i = 0; i < head.length; i++) {
			if(sheet.getColumnWidth(i)<head[i].getBytes().length*210){
				sheet.setColumnWidth(i, head[i].getBytes().length*210);
			}

			createCell(wb, row, i, head[i], font,null);
		}
	}

	/**
	 * @Author zhangjing
	 * @Description //数据写入
	 * @Date 10:34 2018/9/7
	 * @Param [wb, data, sheetName]
	 * @return void
	 **/
	public static void writeSheetExcelData(Workbook wb,List<Object[]> data,String sheetName){
		Sheet sheet=wb.getSheet(sheetName);
		int rowNum = sheet.getLastRowNum();
			// 设置数据字体
			Font font = createFonts(wb, Font.BOLDWEIGHT_NORMAL, "宋体", false, (short) 200);
			for (int i = 0, le = data.size(); i < le; i++) {
				rowNum++;
				Object[] rowData = data.get(i);
				Row row = sheet.createRow(rowNum);
				for (int j = 0, len = rowData.length; j < len; j++) {
					Object value = rowData[j] != null ? rowData[j] : "";
					if(sheet.getColumnWidth(j)<value.toString().getBytes().length*210){
						int width = value.toString().getBytes().length*210;
						sheet.setColumnWidth(j, width>4200?4200:width);
					}

					createCell(wb, row, j, value, font,null);
				}
			}

	}

	/**
	 * @Author zhangjing
	 * @Description //落款写入
	 * @Date 10:35 2018/9/7
	 * @Param [wb, endStr, sheetName,colsNum]
	 * @return void
	 **/
	public static  void writeSheetExcelEnd(Workbook wb,String endStr,String sheetName,int colsNum){
		Sheet sheet=wb.getSheet(sheetName);
		int rowNum = sheet.getLastRowNum();
			rowNum++;
			Row row = sheet.createRow(rowNum);
			Font font = createFonts(wb, Font.BOLDWEIGHT_BOLD, "宋体", false, (short) 200);
			createCells(wb, row, 0, endStr, font, CellStyle.ALIGN_RIGHT);
			sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, colsNum-1));
	}




	/**
	 * @Author zhangjing
	 * @Description //导出文件
	 * @Date 10:39 2018/9/6
	 * @Param [wb, fileName, resp, dealer]
	 * @return void
	 **/
	public static void exportExcelFile(Workbook wb, String fileName, HttpServletResponse resp) {
		OutputStream os = null;
		try {
			// 输出
			resp.setContentType("application/vnd.ms-excel");
			resp.setHeader("Content-disposition", "attachment;filename=\"" + new String(fileName.getBytes("gbk"), "ISO8859-1") + ".xlsx\"");
			os = resp.getOutputStream();
			wb.write(os);
		} catch (Exception e) {
			log.error("", e);
			throw new BaseException(ErrorCodeEnum.EXPORT_EXCEL);
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					log.error("", e);
					//throw new BaseException(ErrorCodeEnum.EXPORT_EXCEL);
				}
			}
		}
	}


	/**
	 * @Description //供已使用的方法，其他尽量使用上边方法，方便优化
	 * @Date 20:38 2018/11/22
	 * @Param [sheetName, timer, title, values, wb]
	 * @return org.apache.poi.hssf.usermodel.HSSFWorkbook
	 **/
	public static HSSFWorkbook getHSSFWorkbook(String sheetName, String timer, String []title, String [][]values, HSSFWorkbook wb){

		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		if(wb == null){
			wb = new HSSFWorkbook();
		}
		HSSFRow row = null;
		HSSFCell cell = null;
		//----------------标题样式---------------------
		HSSFCellStyle titleStyle = wb.createCellStyle();        //标题样式
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		Font ztFont = wb.createFont();
		ztFont.setItalic(false);                     // 设置字体为斜体字
		ztFont.setColor(Font.COLOR_NORMAL);            // 将字体设置为“红色”
		ztFont.setFontHeightInPoints((short) 16);    // 将字体大小设置为18px
		ztFont.setFontName("宋体");             // 将“宋体”字体应用到当前单元格上
		ztFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);    //加粗
		titleStyle.setFont(ztFont);

		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
		row = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		cell = row.createCell(0);
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, title.length-1));
		// 设置单元格内容
		cell.setCellValue(sheetName);
		cell.setCellStyle(titleStyle);


		//----------------时间样式---------------------
		HSSFCellStyle titleStyle1 = wb.createCellStyle();        //标题样式
		titleStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		titleStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
		titleStyle1.setFont(ztFont);
		// 时间列，右对齐
		row = sheet.createRow(1);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, title.length-1));
		cell.setCellValue(timer);
		cell.setCellStyle(titleStyle1);

		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		HSSFCellStyle stylec = wb.createCellStyle();
		stylec.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//垂直居中

		row = sheet.createRow(2);
		cell = row.createCell(0);
		//创建标题
		for(int i=0;i<title.length;i++){
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
			sheet.setColumnWidth(i+1, 20*256);
		}
		//创建内容
		for(int i=0;i<values.length;i++){
			row = sheet.createRow(i + 3);
			for(int j=0;j<values[i].length;j++){
				//将内容按顺序赋给对应的列对象
				row.createCell(j).setCellValue(values[i][j]);
				row.getCell(j).setCellStyle(stylec);
			}
		}
		return wb;
	}

}


