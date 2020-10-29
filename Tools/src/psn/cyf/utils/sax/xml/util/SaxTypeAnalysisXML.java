package psn.cyf.utils.sax.xml.util;

import java.io.File;
import java.io.FileInputStream;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


/**
 * 使用SAX技术对xml文档进行窗口式读取
 * 主要针对大文件进行读取
 */
public class SaxTypeAnalysisXML  {
    public static void main(String[] args) {
        File file = new File("D:\\Temp\\xml\\BSP_STAT.xml");
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            SaxHandler handler = new SaxHandler( );
            parser.parse(new FileInputStream(file), handler);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}


