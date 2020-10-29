package psn.cyf.utils.xml;

import com.sun.org.apache.xerces.internal.dom.DeferredTextImpl;
import com.sun.org.apache.xerces.internal.dom.TextImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import psn.cyf.utils.http.HttpUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class XmlUtil {
    public static void main(String[] args) throws  Exception {
       String res= HttpUtil.request("http://localhost:8080/uapws/service/nc.itf.invoce.back.ItfInvoceBack?wsdl");

         Document document=createDocument(res);
        Node root= document.getDocumentElement();
        NodeList nodeList= root.getChildNodes() ;
        for(int i=0,len=nodeList.getLength();i<len;i++){
            System.out.println(nodeList.item(i).getNodeName()+"  "+nodeList.item(i).getNodeType()+" "+(nodeList.item(i) instanceof TextImpl)+""+ nodeList.item(i).getNodeValue());
        }

        /*Map<String,String> attributeMap=getAttributeMap(root);
        for(String key:attributeMap.keySet()){
            System.out.println(key +"  "+attributeMap.get(key));
        }*/
    }

    /**
     * @param document
     * @return	将XML文档实例转换为字符串
     * @throws TransformerException
     */
    public static String documentToString(Document document) throws TransformerException{

        StringWriter writer=new StringWriter();
        getTransformer().transform(new DOMSource(document), new StreamResult(writer));
//        System.out.print(writer.getBuffer().toString());
        return writer.getBuffer().toString();
    }
    /**
     * //翻译器
     */
    private static Transformer transformer=null;
    private static Transformer getTransformer() throws TransformerException {
        if(transformer==null){
            TransformerFactory tff= TransformerFactory.newInstance();
            transformer=tff.newTransformer();
        }
        transformer.setOutputProperty("encoding", "UTF-8");
        return transformer;
    }
    /**
     * @return 获取XML文档实例
     * @throws ParserConfigurationException
     */
    public static Document createDocument() throws ParserConfigurationException{

        return getDomBuilder().newDocument();
    }
    public static Document createDocument(String xml) throws  Exception{
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(xml.getBytes("UTF-8"));
        return getDomBuilder().parse(byteArrayInputStream);
    }
    /**
     * 创建器
     */
    private static DocumentBuilder builder=null;
    private static DocumentBuilder getDomBuilder() throws ParserConfigurationException {
        if(builder==null){
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            builder=factory.newDocumentBuilder();
        }
        return builder;
    }
}
