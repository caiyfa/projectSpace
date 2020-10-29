package psn.cyf.business.service.ctrl;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import psn.cyf.base.ctrl.SuperCtrl;
import psn.cyf.base.ui.eventhandler.FileDragOverEventHandler;
import psn.cyf.base.vo.BaseVO;


import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TestWebServiceCtrl extends SuperCtrl {
    public AnchorPane anchorPane;
    public TextField url;

    public Button linkBtn;
    public TextArea resultArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        //anchorPane.setOnDragOver(new FileDragOverEventHandler(new String[]{".xml",".XML"}));

    }

    public  void onBtnLink(){

        resultArea.clear();





    }
    public void doTestWebService(String url,String nameSpace,String method,String filePath){
        {
            Service service = new Service();
            try {

                Call call = (Call) service.createCall();
                call.setTargetEndpointAddress(new URL(url));
                // 注册命名空间下的接口方法名
                call.setOperationName(new QName(nameSpace, method));
                // 设置 SOAPACTION 属性
                //call.setProperty(Call.SOAPACTION_URI_PROPERTY,	"http://back.invoce.itf.nc/ItfInvoceBack/invoceInfoBack");
                // call.addParameter(new
                // QName("http://back.invoce.itf.nc/ItfInvoceBack",
                // "string"),XMLType.SOAP_STRING, ParameterMode.IN);
                // call.addParameter("parameters",XMLType.XSD_STRING,
                // ParameterMode.IN);
                // 添加参数 命名空間下的參數
                call.addParameter("xmlStr", XMLType.XSD_STRING, ParameterMode.IN);
                call.addParameter("pluginCode", XMLType.XSD_STRING, ParameterMode.IN);
                call.setReturnType(org.apache.axis.Constants.XSD_STRING);
                String backMsg = getXML(filePath);
                // 设置客户端调用超时时间 为100分钟
                call.setTimeout(6000);
                String res = "";
                long t1=System.currentTimeMillis();
                res = (String) call.invoke(new String[] { backMsg ,"PFXX"});
                long t2=System.currentTimeMillis();
                if(res==null||res.trim().length()==0){
                    resultArea.appendText("接口未返回结果!");
                }else{
                    resultArea.appendText("接口返回["+res+"]");
                }
                resultArea.appendText(res);
            } catch (Exception e) {
                showMessage(e.getMessage());

            }

        }
    }
    private String getXML(String filePath) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)),"GBK"));
        String line =null;
        StringBuffer sb=new StringBuffer();
        while ((line=bufferedReader.readLine())!=null){

             sb.append(line);
        }
        return sb.toString();
    }

    @Override
    protected Node[] getDecorateArr() {
        return new Node[]{ linkBtn};
    }
    @Override
    protected TextInputControl[] getTextInputCtrl() {
        return new TextInputControl[]{url};
    }
    @Override
    protected Button[] getEditStateButton() {
        return new Button[0];
    }

    @Override
    protected Button[] getNormalStateButton() {
        return new Button[0];
    }



    @Override
    protected Class<? extends BaseVO> getBaseClass() {
        return null;
    }



    @Override
    protected Pane getPane() {
        return anchorPane;
    }
}
