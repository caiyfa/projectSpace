package psn.cyf.test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONArray;
import org.json.JSONObject;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiComponent {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        long t1=System.currentTimeMillis();
        String [][]res=	getFirstSheetContent("C:\\Users\\Dell\\Documents\\WeChat Files\\wxid_w8b8gk8j4w5w22\\FileStorage\\File\\2020-10\\Ebay反写.xlsx");
        List<String> values=new ArrayList<>();

        for(int i=0,rows=res.length;i<rows;i++) {
            for(int j=0,cols=res[i].length;j<cols;j++) {
//                System.out.print(res[i][j]+" ");
                if(res[i][j]!=null&&res[i][j].trim().length()!=0&&!"vaule".equals(res[i][j].trim())){
                    values.add(res[i][j].trim());
                }
            }


        }
//        List<String> resArr=new ArrayList<>();
        JSONArray jsonArray=new JSONArray();
        for (String str:values){
            String t=str.substring(0,str.indexOf("\""));
            String[] valuesWithOutKey=t.split(",");
            JSONObject jsonObject=new JSONObject(new LinkedHashMap<>());
            for(int i=0;i<6;i++){
                switch (i){
                    case 0:{
                        jsonObject.put("MFID",valuesWithOutKey[i]);
                        break;
                    }
                    case 1:{
                        jsonObject.put("EBAYID",valuesWithOutKey[i]);
                        break;
                    }
                    case 2:{
                        jsonObject.put("SKUID",valuesWithOutKey[i]);
                        break;
                    }
                    case 3:{
                        jsonObject.put("CUSTOM-LABEL",valuesWithOutKey[i]);
                        break;
                    }
                    case 4:{
                        jsonObject.put("TASKID",valuesWithOutKey[i]);
                        break;
                    } case 5:{
                        jsonObject.put("TASK STATUS",valuesWithOutKey[i]);
                        break;
                    }

                }
            }
       String tmp= str.substring(str.indexOf("[")+1,str.lastIndexOf("]"));


//            tmp= tmp.replace("\"\"","\"");
//            JSONObject jsonObjectTmp=new JSONObject(tmp);
////            for(String key:jsonObjectTmp.keySet()){
////                jsonObject.put(key,jsonObjectTmp.get(key));
////            }
            tmp= tmp.replace("\"\"","\"");
            jsonObject.put("ERROR",tmp);

            jsonArray.put(jsonObject);
//            resArr.add(tmp+",");
        }
//         StringBuffer buffer=new StringBuffer("[");
//        resArr.forEach((value)->buffer.append(value));
//        buffer.setLength(buffer.length()-1);
//
//        buffer.append("]");
//        jsonArray.toString()

        FileWriter writer=new FileWriter("D:\\Temp\\xls\\tmp.txt");
        writer.write(jsonArray.toString());
        writer.flush();
        writer.close();


    }
    public static String[][] getXlsContents(String fileName){
        return null;
    }
    public static String[][] getFirstSheetContent(String fileName) throws IOException, InvalidFormatException{
        return getAppointedSheetContent(fileName, 0);
    }
    public static String[][] getAppointedSheetContent(String fileName,int sheetIndex) throws IOException, InvalidFormatException{
        long t1=System.currentTimeMillis();
        InputStream is = new FileInputStream(new File(fileName));
        Workbook wb = WorkbookFactory.create(is);
//		System.out.println(wb);
        Sheet sheet = wb.getSheetAt(sheetIndex);
        List<ArrayList<String>>  dimensionalList=new ArrayList<ArrayList<String>>(sheet.getLastRowNum());//竖向输出
//		List<ArrayList<String>>  dimensionalList=new ArrayList<ArrayList<String>>();//横向输出1
        for (int rowIndex = 0, rows = sheet.getLastRowNum(); rowIndex <= rows; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if(row==null) {
                continue;
            }
            ArrayList<String> cellList=new ArrayList<String>(row.getLastCellNum());//竖向输出
            dimensionalList.add(cellList);//竖向输出
            for(int cellIndex=0,cells=row.getLastCellNum();cellIndex<cells;cellIndex++) {
                Cell cell=row.getCell(cellIndex);
                if(cell==null) {
//					System.out.print(" * ");
                    continue;
                }

                cellList.add(getValue(cell));//竖向输出
//				System.out.print(getValue(cell)+" ");
				/*//没有向数组中塞值就不可能取得到，在这里避免数组越界的情况// 横向输出1
				if(cellIndex>=dimensionalList.size()) {
					dimensionalList.add(cellIndex, new ArrayList<String>());//横向输出1
				}

				ArrayList<String> tmpList=dimensionalList.get(cellIndex);//横向输出1
					tmpList.add(rowIndex, getValue(cell));*/

            }
//			System.out.println();
        }
        long t2=System.currentTimeMillis();
        System.out.println("解析xlsx文档耗时："+(t2-t1)+"ms.");
//for(int col=0,cols=dimensionalList.size();col<cols;col++) {
//	int rows2=dimensionalList.get(col).size();
//	for(int row=0;row<rows2;row++) {
//		System.out.print(dimensionalList.get(col).get(row)+" ");
//	}
//	System.out.println();
//}
        String[][] res=new String[dimensionalList.size()][];
        for(int row=0,rows=dimensionalList.size();row<rows;row++) {
            int cols=dimensionalList.get(row).size();
            res[row]=new String[cols];
            for(int col=0 ;col<cols;col++) {
                res[row][col]=dimensionalList.get(row).get(col);

            }
        }
        return res;

    }

    private static String getValue(Cell cell) {
        int type=cell.getCellType();
        switch(type) {
            case Cell.CELL_TYPE_STRING:{
                return cell.getStringCellValue();
            }
            case Cell.CELL_TYPE_NUMERIC:{
//			return sdf.format(cell.getDateCellValue());
//			System.out.println();
                if(DateUtil.isCellDateFormatted(cell)) {
                    return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                }
                return cell.getNumericCellValue()+"";
            }
            default:
                return "";
        }

    }

}
