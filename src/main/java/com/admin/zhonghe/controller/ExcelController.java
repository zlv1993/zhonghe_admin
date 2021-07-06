package com.admin.zhonghe.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@Api(tags = "Excel")
@RequestMapping("/excel")
public class ExcelController {
    @PostMapping("/export")
    @ApiOperation(value="导出",produces = "application/octet-stream")
    public void export(HttpServletResponse httpServletResponse) throws IOException {
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet("信息表");
        List<Map> maps=new ArrayList<>();
        for(int i=1;i<30;i++){
            Map<String,String> map=new HashMap();
            map.put("id",i+"");
            map.put("name","测试名称"+i);
            maps.add(map);
        }
        String fileName="user"+new Date()+".xls";
        String[] hearders={"id","名称"};
        HSSFRow row =sheet.createRow(0);
        for(int i=0;i<hearders.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString hssfRichTextString = new HSSFRichTextString(hearders[i]);
            cell.setCellValue(hssfRichTextString);
        }

        int rowNum=1;
        for (Map map:maps){
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue((String) map.get("id"));
            row1.createCell(1).setCellValue((String) map.get("name"));
            rowNum++;

        }
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setHeader("Content-disposition", "attachment;filename=" + fileName);
        httpServletResponse.flushBuffer();
        workbook.write(httpServletResponse.getOutputStream());

    }

    @PostMapping("/read")
    @ApiOperation(value="读取")
    public void read(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String pattern = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
        Workbook workbook = null;
        List<Map<String,String>> list=new ArrayList<>();
        if(file!=null){
            if("xls".equals(pattern)){
                workbook= new HSSFWorkbook(file.getInputStream());
            }else if("xlsx".equals(pattern)){
                workbook= new XSSFWorkbook(file.getInputStream());
            }else {
                workbook=null;
            }

        }
        if(workbook!=null){
            Sheet sheet = workbook.getSheetAt(0);
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            for(int i=1;i<physicalNumberOfRows;i++){
                Row row = sheet.getRow(i);
               Map<String,String> map=new HashMap();
               map.put("id",row.getCell(0).getStringCellValue());
                map.put("name",row.getCell(1).getStringCellValue());
                list.add(map);

            }

        }

        System.out.println(list);
    }
}
