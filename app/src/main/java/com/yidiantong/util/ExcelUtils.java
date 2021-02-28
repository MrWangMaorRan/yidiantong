package com.yidiantong.util;

import android.util.Log;

import com.yidiantong.BactIntefacer;
import com.yidiantong.MainActivity;
import com.yidiantong.bean.XlseBean;
import com.yidiantong.util.log.LogUtils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ExcelUtils {

//    private static Cell cell;

    /**
     * 读取Excel文件
     * @param file
     * @throws
     */
    public static void readExcel(File file, BactIntefacer bactIntefacer) throws FileNotFoundException {

        if(file == null) {
            Log.e("NullFile","读取Excel出错，文件为空文件");
            return;
        }
        InputStream stream = new FileInputStream(file);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(stream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getPhysicalNumberOfRows();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            ArrayList<XlseBean> xlseBeans = new ArrayList<>();
            for (int r = 0; r<rowsCount; r++) {
                XlseBean xlseBean = new XlseBean();

                Row row = sheet.getRow(r);
                int cellsCount = row.getPhysicalNumberOfCells();
                //每次读取一行的内容
                for (int c = 0; c<cellsCount; c++) {
                    //将每一格子的内容转换为字符串形式
                    String value = getCellAsString(row, c, formulaEvaluator);
                    String cellInfo = "r:"+r+"; c:"+c+"; v:"+value;
                 if (c%2!=0){
                     double v = Double.parseDouble(value);
                        DecimalFormat df = new DecimalFormat("###.#################");
                       String  amount = df.format(v);
                       xlseBean.setPhonenum(amount);
                        Log.i("文件内容",amount);
                 }else {
                     xlseBean.setName(value);
                     Log.i("文件名字",value);
                 }
                    xlseBeans.add(xlseBean);

                }
            }
                bactIntefacer.getSd(xlseBeans);
        } catch (Exception e) {
            /* proper exception handling to be here */
            Log.e("异常","异常");
        }

    }

    /**
     * 读取excel文件中每一行的内容
     * @param row
     * @param c
     * @param formulaEvaluator
     * @return
     */
    private static String getCellAsString(Row row, int c, FormulaEvaluator formulaEvaluator) {
        String value = "";
        try {
           Cell cell = row.getCell(c);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = ""+cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    double numericValue = cellValue.getNumberValue();
                    if(HSSFDateUtil.isCellDateFormatted(cell)) {
                        double date = cellValue.getNumberValue();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                         value = formatter.format(HSSFDateUtil.getJavaDate(date));
//                        BigDecimal bigDecimal = new BigDecimal(v);
//                        value = bigDecimal.toPlainString();
                    } else {
                        value = ""+numericValue;
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = ""+cellValue.getStringValue();
                    break;
                default:
                    break;
            }
        } catch (NullPointerException e) {
            /* proper error handling should be here */
          //  LogUtils.e(e.toString());
        }
        return value;
    }

    /**
     * 根据类型后缀名简单判断是否Excel文件
     * @param file 文件
     * @return 是否Excel文件
     */
    public static boolean checkIfExcelFile(File file){
        if(file == null) {
            return false;
        }
        String name = file.getName();
        //”.“ 需要转义字符
        String[] list = name.split("\\.");
        //划分后的小于2个元素说明不可获取类型名
        if(list.length < 2) {
            return false;
        }
        String  typeName = list[list.length - 1];
        //满足xls或者xlsx才可以
        return "xls".equals(typeName) || "xlsx".equals(typeName);
    }


    }
