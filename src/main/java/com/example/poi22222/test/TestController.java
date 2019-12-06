package com.example.poi22222.test;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

@Controller
public class TestController {

    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle headStyle = headStyle(wb);
        HSSFCellStyle contentStyle = contentStyle(wb);
        HSSFSheet sheet_1 =  wb.createSheet("人员信息");
        sheet_1.setDefaultColumnWidth(30);
        HSSFRow headRow = sheet_1.createRow(0);
        HSSFCell head_cell_1 = headRow.createCell(0);           //创建标题行第一列
        head_cell_1.setCellValue("姓名");                        //第一列内容
        head_cell_1.setCellStyle(headStyle);                    //将标题行样式添加
        HSSFCell head_cell_2 = headRow.createCell(1);
        head_cell_2.setCellValue("性别");
        head_cell_2.setCellStyle(headStyle);

        HSSFCell head_cell_3 = headRow.createCell(2);
        head_cell_3.setCellValue("年龄");
        head_cell_3.setCellStyle(headStyle);

        for (int i = 1; i < 3; i++) {
            HSSFRow contentRow  = sheet_1.createRow(i);
            HSSFCell cell1 = contentRow.createCell(0);
            cell1.setCellValue("张三");
            cell1.setCellStyle(contentStyle);

            HSSFCell cell2 = contentRow.createCell(1);
            cell2.setCellValue("张四");
            cell2.setCellStyle(contentStyle);

            HSSFCell cell3 = contentRow.createCell(2);
            cell3.setCellValue("张五");
            cell3.setCellStyle(contentStyle);
        }
        CellRangeAddress region  = new CellRangeAddress(0, 2, 0, 0);
        sheet_1.addMergedRegion(region);
        //定义导出文件的名称，看不懂的同学可以先行了解一下文件下载
        String fileName = null;
        try {
            fileName = new String("personRelation.xls".getBytes("UTF-8"),"ISO-8859-1");

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment; filename="+fileName);
        OutputStream os = response.getOutputStream();

        //将工作薄写入到输出流中
        wb.write(os);
        os.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HSSFCellStyle headStyle(HSSFWorkbook wb){
        HSSFCellStyle headStyle = wb.createCellStyle();                       //创建样式对象
        HSSFFont headFont = wb.createFont();                                  //创建字体
        headFont.setFontName("微软雅黑");
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headFont.setColor(HSSFFont.COLOR_RED);

        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headStyle.setFont(headFont);
        return headStyle;
    }

    public static HSSFCellStyle contentStyle(HSSFWorkbook wb){
        HSSFCellStyle contentStyle = wb.createCellStyle();
        HSSFFont contentFont = wb.createFont();
        contentFont.setFontName("微软雅黑");
        contentFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        contentFont.setColor(HSSFFont.COLOR_NORMAL);

        contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        contentStyle.setFont(contentFont);
        return contentStyle;
    }
}
