package com.hww.es.service.impl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hww.es.pojo.es.EsNews;
import com.hww.es.service.IUploadService;
import com.hww.es.service.es.SearchService;

/**
 * @ClassName:  UploadServiceImpl   
 * @Description:文件导出   
 * @author: lvjie
 * @date:   2018年10月24日 上午9:54:00  
 * @Copyright: 2018
 */
@Service
public class UploadServiceImpl implements IUploadService{
	@Autowired
	private SearchService searchService;//查询新闻
	@Value("${upload}")
    private String UPLOAD_FOLDER;
	/**
	 * 生成Excel
	 */
	@Override
	public String exportExcel(HttpServletResponse response,String ids) throws Exception {
		String[] split = ids.split(",");
		ArrayList<EsNews> list = new ArrayList<EsNews>();
		for(int i=0;i<split.length;i++){
			String id = split[i];
			EsNews news = searchService.searchNewsDetail("htmlbean", id);//查询新闻
			list.add(news);
		}
		
		
		//第一步，创建一个workbook对应一个excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        XSSFSheet sheet = workbook.createSheet("sheet1");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        XSSFRow row = sheet.createRow(0);
        //第四部，创建单元格，并设置值表头，设置表头居中
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//创建居中格式
        
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("标题");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("内容");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("作者");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("时间");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("情感倾向");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("语种");
        cell.setCellStyle(style);
        
        //第五部，写入实体数据
        for(int i=0;i<list.size();i++){
        	row = sheet.createRow(i+1);//创建单元格
        	//创建单元格设值
        	row.createCell(0).setCellValue(list.get(i).getTitle());//标题
        	row.createCell(1).setCellValue(list.get(i).getContent());//内容
            row.createCell(2).setCellValue(list.get(i).getAuthor());//作者
            row.createCell(3).setCellValue(list.get(i).getPublishDate());//时间
            row.createCell(4).setCellValue(list.get(i).getAttribute());//情感倾向
            row.createCell(5).setCellValue(list.get(i).getLanguage());//语种
        }
        
        //第6步，输出Excel
//        OutputStream output = response.getOutputStream();
//        response.reset();
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
//        String fileName = df.format(new Date());
//        response.setHeader("Content-disposition", "attachment; filename = "+fileName+".xlsx");
//        response.setContentType("application/msexcel");
//        workbook.write(output);
//        output.close();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String fileName = df.format(new Date());
        String path = UPLOAD_FOLDER+fileName+".xlsx";
        //输出Excel文件  
        FileOutputStream output=new FileOutputStream(path);  
        workbook.write(output);  
        output.flush();
        output.close();
        return "/file/"+fileName+".xlsx";
	}
	/**
	 * 生成word
	 */
	@Override
	public void exportWord(HttpServletResponse response, String id)
			throws Exception {
		EsNews news = searchService.searchNewsDetail("htmlbean", id);//查询新闻
		 //Blank Document  
        XWPFDocument document= new XWPFDocument();  
  
        //Write the Document in file system  
//        FileOutputStream out = new FileOutputStream(new File("D:\\create_table.docx"));  
  
        //添加标题  
        XWPFParagraph titleParagraph = document.createParagraph();  
        //设置段落居中  
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);  
  
        XWPFRun titleParagraphRun = titleParagraph.createRun();  
        titleParagraphRun.setText("海外舆情");  
        titleParagraphRun.setColor("000000");  
        titleParagraphRun.setFontSize(20);  
  
        //段落  
        XWPFParagraph firstParagraph = document.createParagraph();  
        XWPFRun run = firstParagraph.createRun();  
        run.setText(news.getTitle());//标题  
        run.setColor("696969");  
        run.setFontSize(16);  
  
        //设置段落背景颜色  
        CTShd cTShd = run.getCTR().addNewRPr().addNewShd();  
        cTShd.setVal(STShd.CLEAR);  
        cTShd.setFill("97FFFF");  
  
        //换行  
        XWPFParagraph paragraph1 = document.createParagraph();  
        XWPFRun paragraphRun1 = paragraph1.createRun();  
        paragraphRun1.setText("\r");  
  
        //基本信息表格  
        XWPFTable infoTable = document.createTable();  
        //去表格边框  
        infoTable.getCTTbl().getTblPr().unsetTblBorders();  
  
        //列宽自动分割  
        CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();  
        infoTableWidth.setType(STTblWidth.DXA);  
        infoTableWidth.setW(BigInteger.valueOf(9072));  
  
        //工作经历表格  
        XWPFTable ComTable = document.createTable();  
  
        //列宽自动分割  
        CTTblWidth comTableWidth = ComTable.getCTTbl().addNewTblPr().addNewTblW();  
        comTableWidth.setType(STTblWidth.DXA);  
        comTableWidth.setW(BigInteger.valueOf(9072));  
  
        //表格第一行  
        XWPFTableRow comTableRowOne = ComTable.getRow(0);  
        comTableRowOne.getCell(0).setText("原始来源");  
        comTableRowOne.addNewTableCell().setText("原文链接");  
        comTableRowOne.addNewTableCell().setText("作者");  
        comTableRowOne.addNewTableCell().setText("发布时间");
        comTableRowOne.addNewTableCell().setText("文章字数");
  
        //表格第二行  
        XWPFTableRow comTableRowTwo = ComTable.createRow();  
        comTableRowTwo.getCell(0).setText(news.getSource());  //来源
        comTableRowTwo.getCell(1).setText(news.getSourceUrl()); //原文链接 
        comTableRowTwo.getCell(2).setText(news.getAuthor());//作者  
        comTableRowTwo.getCell(3).setText(news.getPublishDate());
        comTableRowTwo.getCell(4).setText(news.getLenContent()+"个");
        
        //工作经历表格  
        XWPFTable ComTable1 = document.createTable();  
  
        //重新建一个表，存放详情  
        CTTblWidth comTableWidth1 = ComTable1.getCTTbl().addNewTblPr().addNewTblW();  
        comTableWidth1.setType(STTblWidth.DXA);  
        comTableWidth1.setW(BigInteger.valueOf(9072));
        //表格第三行  
        XWPFTableRow xqRow = ComTable1.createRow(); 
        xqRow.getCell(0).setText(news.getContent());//主题内容
        
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();  
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);  
  
        //添加页眉  
        CTP ctpHeader = CTP.Factory.newInstance();  
        CTR ctrHeader = ctpHeader.addNewR();  
        CTText ctHeader = ctrHeader.addNewT();  
        String headerText = "人民日报海外版";  
        ctHeader.setStringValue(headerText);  
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);  
        //设置为右对齐  
        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);  
        XWPFParagraph[] parsHeader = new XWPFParagraph[1];  
        parsHeader[0] = headerParagraph;  
        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);  
  
        //添加页脚  
        CTP ctpFooter = CTP.Factory.newInstance();  
        CTR ctrFooter = ctpFooter.addNewR();  
        CTText ctFooter = ctrFooter.addNewT();  
        String footerText = "http://10.0.12.1:8080/yuqing/index.html";  
        ctFooter.setStringValue(footerText);  
        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);  
        headerParagraph.setAlignment(ParagraphAlignment.CENTER);  
        XWPFParagraph[] parsFooter = new XWPFParagraph[1];  
        parsFooter[0] = footerParagraph;  
        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);  
  
        /**
         * 输出文档
         */
        OutputStream output = response.getOutputStream();
        response.reset();
//        long filename = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String fileName = df.format(new Date());
        response.setHeader("Content-disposition", "attachment; filename = "+fileName+".docx");
        response.setContentType("application/octet-stream");
        document.write(output);
        output.close();
        
        
//        document.write(out);  
//        out.close();  
        System.out.println("upload word success.");
	}
	/**
	 * 生成CSV
	 */
	@Override
	public String exportCsv(HttpServletResponse response, String ids)
			throws Exception {

		String[] split = ids.split(",");
		ArrayList<EsNews> list = new ArrayList<EsNews>();
		for(int i=0;i<split.length;i++){
			String id = split[i];
			EsNews news = searchService.searchNewsDetail("htmlbean", id);//查询新闻
			list.add(news);
		}
		
		//第一步，创建一个workbook对应一个excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        XSSFSheet sheet = workbook.createSheet("sheet1");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        XSSFRow row = sheet.createRow(0);
        //第四部，创建单元格，并设置值表头，设置表头居中
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//创建居中格式
        
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("标题");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("内容");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("作者");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("时间");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("情感倾向");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("语种");
        cell.setCellStyle(style);
        
        //第五部，写入实体数据
        for(int i=0;i<list.size();i++){
        	row = sheet.createRow(i+1);//创建单元格
//        	row = sheet.createRow(1);//创建单元格
        	//创建单元格设值
        	row.createCell(0).setCellValue(list.get(i).getTitle());//标题
        	row.createCell(1).setCellValue(list.get(i).getContent());//内容
            row.createCell(2).setCellValue(list.get(i).getAuthor());//作者
            row.createCell(3).setCellValue(list.get(i).getPublishDate());//时间
            row.createCell(4).setCellValue(list.get(i).getAttribute());//情感倾向
            row.createCell(5).setCellValue(list.get(i).getLanguage());//语种
        }
        
        //第6步，输出Excel
//        OutputStream output = response.getOutputStream();
//        response.reset();
////        long filename = System.currentTimeMillis();
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
//        String fileName = df.format(new Date());
//        response.setHeader("Content-disposition", "attachment; filename = "+fileName+".csv");
//        response.setContentType("application/msexcel");
//        workbook.write(output);
//        output.close();
        
        long filename = System.currentTimeMillis();
        String path = UPLOAD_FOLDER+filename+".csv";
        //输出Excel文件  
        FileOutputStream output=new FileOutputStream(path);  
        workbook.write(output);  
        output.flush();
        output.close();
        return "/file/"+filename+".csv";
	}
}
