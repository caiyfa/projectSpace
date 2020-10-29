package psn.cyf.utils.xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) throws  Exception {



        String xmlFile="D:\\Temp\\1.classpath";
        Document document=getDomBuilder().parse(new File(xmlFile));
        /*Element element= document.getDocumentElement();
        NodeList nodeList= document.getElementsByTagName("classpathentry");
        for (int i=0,len= nodeList.getLength();i<len;i++){
            System.out.print(nodeList.item(i).getAttributes().getNamedItem("kind").getNodeValue()+" ");
            System.out.println(nodeList.item(i).getAttributes().getNamedItem("path").getNodeValue());
        }*/
        new Test().traversal(document);


//        NodeList nodeList= element.getChildNodes();
//        Node node=nodeList.item(0);
//    遍历文档    document.getDocumentElement().appendChild(document.createTextNode("\n\t"));
////        document.appendChild(document.createComment("测试"))  ;
//        documentToFile(document,new File("D:\\Temp\\1.classpath"));
        System.out.println();

    }

    /**
     * 遍历文档
     * @param document 文档
     */
void  traversal(Document document){
    traversal(document.getChildNodes());
}

    /**
     * 遍历节点
     * @param nodeList 节点列表
     */
void traversal(NodeList nodeList){
    for (int i=0,len= nodeList.getLength();i<len;i++){
        Node node=nodeList.item(i);
        if(node==null){
            return;
        }
        System.out.print(node.getNodeName());
        NamedNodeMap attributes=node.getAttributes();
        if(attributes!=null){
            for( int j=0,jlen=attributes.getLength();j<jlen;j++){
                System.out.print(attributes.item(j).getNodeName()+" ");
                System.out.println(attributes.item(j).getNodeValue());
            }
        }

        NodeList childNodes=nodeList.item(i).getChildNodes();
        if(childNodes!=null&&childNodes.getLength()!=0){
            traversal(childNodes);
        }else {
            System.out.println(node.getNodeValue());
        }


    }
}




    /**
     * @param document 文档
     * @return	将XML文档实例转换为字符串
     * @throws TransformerException
     */
    public static String documentToString(Document document) throws TransformerException {

        StringWriter writer=new StringWriter();
        getTransformer().transform(new DOMSource(document), new StreamResult(writer));
//        System.out.print(writer.getBuffer().toString());
        return writer.getBuffer().toString();
    }
    public static void documentToFile(Document document,File file) throws IOException, TransformerException {
        FileWriter writer=new FileWriter(file);
        getTransformer().transform(new DOMSource(document),new StreamResult(writer));
        writer.flush();;
        writer.close();
    }
    /**
     * //翻译器
     */
    private static Transformer transformer=null;
    private static Transformer getTransformer() throws TransformerException{
        if(transformer==null){
            TransformerFactory tff= TransformerFactory.newInstance();
            transformer=tff.newTransformer();
        }
        transformer.setOutputProperty("encoding", "UTF-8");
        //翻译器元素之间换行
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        return transformer;
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
    }/**
     * @return 获取XML文档实例
     * @throws ParserConfigurationException
     */
    public static Document createDocument() throws ParserConfigurationException{
        return getDomBuilder().newDocument();
    }
    public static Map<String,String> getAttributeMap(Node node){
        Map<String,String> map=new ConcurrentHashMap<>();
        if(node.getAttributes()!=null)
            for(int i=0,size=node.getAttributes().getLength();i<size;i++){
                map.put(node.getAttributes().item(i).getNodeName(),node.getAttributes().item(i).getNodeValue());
            }
        return map;
    }
}
