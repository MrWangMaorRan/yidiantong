package com.yidiantong.util;

import android.os.Looper;
import android.util.Log;

import com.yidiantong.BactIntefacer;
import com.yidiantong.app.MainApplication;
import com.yidiantong.bean.XlseBean;
import com.yidiantong.widget.ToastUtil;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ExcelUtils {


//    private static Cell cell;

    /**
     * 读取Excel文件
     * @param file
     * @throws
     */
    public static void readExcel(File file, BactIntefacer bactIntefacer) {


        if (file == null) {
            Log.e("NullFile", "读取Excel出错，文件为空文件");
            return;
        }
        Thread thread = new Thread() {

            private XlseBean xlseBean;

            @Override
            public void run() {
                super.run();
                try {
                    InputStream stream = new FileInputStream(file);

                    XSSFWorkbook workbook = new XSSFWorkbook(stream);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    int rowsCount = sheet.getPhysicalNumberOfRows();
                    FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
                    ArrayList<XlseBean> xlseBeans = new ArrayList<>();
                    Log.i("rowsCount", rowsCount + "");
                    ArrayList<String> strings = new ArrayList<>();
                    for (int r = 0; r < rowsCount; r++) {
                        xlseBean = new XlseBean();
                        Row row = sheet.getRow(r);
                        int cellsCount = row.getPhysicalNumberOfCells();
                        Log.i("cellsCount", cellsCount + "");
                        //每次读取一行的内容
                        for (int c = 0; c < cellsCount; c++) {
//                            //将每一格子的内容转换为字符串形式
                            String value = getCellAsString(row, c, formulaEvaluator);

                            strings.add(value);
                            //Log.i("文件内容", value);
                            String cellInfo = "r:" + r + "; c:" + c + "; v:" + value;
//                            if (c%2!=0){
//                                double v = Double.parseDouble(value);
//                                DecimalFormat df = new DecimalFormat("###.#################");
//                                String  amount = df.format(v);
//                                xlseBean.setPhonenum(amount);
//                                Log.i("文件内容",amount);
//                            }else {
//                                xlseBean.setName(value);
//                                Log.i("文件名字",value);
//                            }
//                            xlseBeans.add(xlseBean);
                        }
                    }
                    String regx = "[+-]*\\d+\\.?\\d*[Ee]*[+-]*\\d+";
                    strings.remove(0);
                    for (int i = 0; i < strings.size(); i++) {
                        xlseBean = new XlseBean();
                        Log.i("集合内容", strings.get(i));
                        String s = strings.get(i);
                        Log.i("字符串内容",s);

                        Pattern pattern = Pattern.compile(regx);
                        boolean isNumber = pattern.matcher(s).matches();
                        if (!isNumber) {
                            Looper.prepare();
                            ToastUtil.showShort(MainApplication.con, "文件格式不正确，请点击“下载模板”查看正确格式");
                            Looper.loop();
                        } else {
                            double v = Double.parseDouble(strings.get(i));
                            DecimalFormat df = new DecimalFormat("###.#################");
                            String amount = df.format(v);
                            boolean phone = isPhone(amount);
                            Log.i("phone1111111", phone + "");
                            if (phone == true) {
                                xlseBean.setPhonenum(amount);
                                xlseBeans.add(xlseBean);
                                Log.i("xlseBean", xlseBean.getPhonenum());
                            } else {
                                Looper.prepare();
                                ToastUtil.showShort(MainApplication.con, "电话号码格式不正确："+amount);
                                Looper.loop();
                            }
                        }
                    }
                    bactIntefacer.getSd(xlseBeans);
                } catch (Exception e) {
                    /* proper exception handling to be here */
                    Log.e("异常", "异常");
                }
            }
        };
        thread.start();
    }

    public static boolean isPhone (String phone){
        String regex = "^((13[0-9])|(14[5-9])|(15([0-3]|[5-9]))|(16[6-7])|(17[1-8])|(18[0-9])|(19[1|3])|(19[5|6])|(19[8|9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        return p.matcher(phone).matches();
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
