package Util;

import Pojo.Case;
import Pojo.WriteBackData;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    public static String filePath = PropertiesUtils.prop.getProperty("filePath");
//    public static String loginCase = PropertiesUtils.prop.getProperty("loginCase");
    public static String loginCase_JSON = PropertiesUtils.prop.getProperty("loginCase_JSON");

    public static Map<String,Integer> caseIdRowNum = new HashMap<String, Integer>();
    public static Map<String,Integer> cellNameCellNum = new HashMap<String, Integer>();

    public static List<WriteBackData> listWriteDate = new ArrayList<WriteBackData>();
    static {
        loadMapping();
    }

    private static void loadMapping() {
        FileInputStream fis = null;
        try {
            //加载Excel文件
            fis = new FileInputStream(filePath);
            //打开Excel文件
            Workbook workbook = WorkbookFactory.create(fis);
            //选择sheet页
            Sheet sheet = workbook.getSheet("用例");
            //获取表头
            Row tltleRow = sheet.getRow(0);
            short lastCellNum = tltleRow.getLastCellNum();
            int caseIdCellNum = -1;
            for (int i = 0; i < lastCellNum; i++) {
                Cell cell = tltleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                String cellName = cell.getStringCellValue();
                cellName = cellName.substring(0,cellName.indexOf("("));
                if("CaseId".equals(cellName)){
                    caseIdCellNum = i;
                }
                cellNameCellNum.put(cellName,i);
            }
            //1.caseID和行号的映射
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(caseIdCellNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);//获取CaseID列
                cell.setCellType(CellType.STRING);
                String caseIdNum = cell.getStringCellValue();
                caseIdRowNum.put(caseIdNum,i);
            }
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 通过传入的行和列获取表格数据
     * @param rows
     * @param cells
     * @return
     */
    public static  Object[][] read(int[] rows,int[] cells){

        Object[][] datas = new Object[rows.length][cells.length];
        FileInputStream fis = null;
        try {
            //加载Excel文件
            fis = new FileInputStream(loginCase_JSON);
            //打开Excel文件
            Workbook workbook = WorkbookFactory.create(fis);
            //选择sheet页
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < rows.length; i++) {
                Row row = sheet.getRow(rows[i]);
                for (int j = 0; j < cells.length; j++) {
                    Cell cell = row.getCell(cells[j],Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String cellValue = cell.getStringCellValue();
                    datas[i][j] = cellValue;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return datas;
    }

    /**
     * 根据sheet页名称获取对应sheet页数据
     * @param sheetName  sheet页名称
     * @return
     */
    public static List<Case> read(String sheetName){
        List<Case> list = new ArrayList<Case>();
        FileInputStream fis = null;
        try {
            //加载Excel文件
            fis = new FileInputStream(filePath);
            //打开Excel文件
            Workbook workbook = WorkbookFactory.create(fis);
            //选择sheet页
            Sheet sheet = workbook.getSheet(sheetName);
            //获取表头
            Row titleRow = sheet.getRow(0);
            //获取表头列长
            short titleCellNum = titleRow.getLastCellNum();
            Object[] titleArray = new Object[titleCellNum];
            for (int i = 0; i < titleCellNum; i++) {
                Cell cell = titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);//获取cell列
                cell.setCellType(CellType.STRING);//设置cell列数据类型
                String value = cell.getStringCellValue();//获取cell列的值
                titleArray[i] = value.substring(0,value.indexOf("("));
            }

            //获取内容
            int lastRowNum = sheet.getLastRowNum();//获取行数
            for (int i = 1; i <= lastRowNum; i++) {
                Row dataRow = sheet.getRow(i);
                Class<Case> clazz = Case.class;//反射获取类字节码对象
                Case obj = clazz.newInstance();//创建Case类对象
                short dataCellNum = dataRow.getLastCellNum();
                for (int j = 0; j < dataCellNum; j++) {
                    Cell cell = dataRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String dataValue = cell.getStringCellValue();
                    //通过反射获取并调用Case类中的set方法
                    String methodName = "set" + titleArray[j];//拼接获取Case类的set方法名
                    Method method = clazz.getMethod(methodName, String.class);//获取Case类set方法
                    method.invoke(obj,dataValue);
                }
                list.add(obj);
            }

        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    /**
     * 使用泛型类，根据传入的字节码对象声明类对象
     * 根据sheet页名称获取对应sheet页数据
     * @param sheetName  sheet页名称
     * @return
     */
    public static <T> List<T> read(String sheetName,Class<T> clazz){
        List<T> list = new ArrayList<T>();
        FileInputStream fis = null;
        try {
            //加载Excel文件
            fis = new FileInputStream(filePath);
            //打开Excel文件
            Workbook workbook = WorkbookFactory.create(fis);
            //选择sheet页
            Sheet sheet = workbook.getSheet(sheetName);
            //获取表头
            Row titleRow = sheet.getRow(0);
            //获取表头列长
            short titleCellNum = titleRow.getLastCellNum();
            Object[] titleArray = new Object[titleCellNum];
            for (int i = 0; i < titleCellNum; i++) {
                Cell cell = titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);//获取cell列
                cell.setCellType(CellType.STRING);//设置cell列数据类型
                String value = cell.getStringCellValue();//获取cell列的值
                titleArray[i] = value.substring(0,value.indexOf("("));
            }

            //获取内容
            int lastRowNum = sheet.getLastRowNum();//获取行数
            for (int i = 1; i <= lastRowNum; i++) {
                Row dataRow = sheet.getRow(i);
                T obj = clazz.newInstance();//创建Case类对象
                //判断Excel列值是否为空，为空则跳过不读取(为空时报空指针)
                if(dataRow == null || isEmptyRow(dataRow)){
                    continue;
                }
                short dataCellNum = dataRow.getLastCellNum();
                for (int j = 0; j < dataCellNum; j++) {
                    Cell cell = dataRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String dataValue = cell.getStringCellValue();
                    //通过反射获取并调用Case类中的set方法
                    String methodName = "set" + titleArray[j];//拼接获取Case类的set方法名
                    Method method = clazz.getMethod(methodName, String.class);//获取Case类set方法
                    method.invoke(obj,dataValue);
                }
                list.add(obj);
            }

        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    //判断Excel整列是否为空，为空返回true，否则返回false
    private static boolean isEmptyRow(Row dataRow) {
        short lastCellNum = dataRow.getLastCellNum();
        for (int i = 0; i < lastCellNum; i++) {
            Cell cell = dataRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            String dataValue = cell.getStringCellValue();
            //一次循环只可以判断一列是否为空
            if(StringUtils.isNoneBlank(dataValue)){
                return false;
            }
        }
        return true;
    }

    public static void write(String caseId, String cellName, String result) {
        //1、从caseId获取rowNum
        Integer rowNum = caseIdRowNum.get(caseId);
        //2、从cellName获取cellNum
        Integer cellNum = cellNameCellNum.get(cellName);
        write("用例",rowNum,cellNum,result);
    }

    /**
     * 回写测试结果到Excel文件
     * @param sheetName
     * @param rowNum
     * @param cellNum
     * @param result
     */
    private static void write(String sheetName,int rowNum,int cellNum,String result) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //加载Excel文件
            fis = new FileInputStream(filePath);
            //打开Excel文件
            Workbook workbook = WorkbookFactory.create(fis);
            //选择sheet页
            Sheet sheet = workbook.getSheet(sheetName);
            //获取指定行
            Row row = sheet.getRow(rowNum);
            //获取指定列
            Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            //回写数据
            cell.setCellValue(result);//回写数据到缓存
            fos = new FileOutputStream(filePath);
            workbook.write(fos);//回写数据到文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void batchWrite() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //加载Excel文件
            fis = new FileInputStream(filePath);
            //打开Excel文件
            Workbook workbook = WorkbookFactory.create(fis);
            //选择sheet页
            Sheet sheet = workbook.getSheet("用例");
            for (WriteBackData wbd:listWriteDate) {
                String caseId = wbd.getCaseId();
                String cellName = wbd.getCellName();
                String result = wbd.getResult();
                //1、从caseId获取rowNum
                Integer rowNum = caseIdRowNum.get(caseId);
                //2、从cellName获取cellNum
                Integer cellNum = cellNameCellNum.get(cellName);
                //获取指定行
                Row row = sheet.getRow(rowNum);
                //获取指定列
                Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                //回写数据
                cell.setCellValue(result);//回写数据到缓存
            }
            fos = new FileOutputStream(filePath);
            workbook.write(fos);//回写数据到文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
