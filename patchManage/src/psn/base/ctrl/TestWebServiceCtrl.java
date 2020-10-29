package psn.base.ctrl;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import psn.base.eventhandler.FileDragOverEventHandler;
import psn.base.vo.BaseVO;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TestWebServiceCtrl extends SuperCtrl {
    public AnchorPane anchorPane;
    public TextField ipAndPort;
    public TextField targetEndpointAddress;
    public TextField nameSpace;
    public TextField methodName;
    public TextField filePath;
    public Button linkBtn;
    public TextArea resultArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        anchorPane.setOnDragOver(new FileDragOverEventHandler(new String[]{".xml",".XML"}));
        targetEndpointAddress.setEditable(false);
        nameSpace.setEditable(false);
        methodName.setEditable(false);
        filePath.setEditable(false);
    }

    public  void onBtnLink(){
        if(ipAndPort.getText().trim().length()==0){
            showMessage("请输入ip和端口");
            return;
        }
        if(targetEndpointAddress.getText().trim().length()==0){
            showMessage("请输入接口调用路径");
            return;
        }
        if(nameSpace.getText().trim().length()==0){
            showMessage("请输入命名空间");
            return;
        }
        if(methodName.getText().trim().length()==0){
            showMessage("请输入方法名");return;

        }
        if(filePath.getText().trim().length()==0){
            showMessage("请选择文件");return;
        }
        String URL=ipAndPort.getText().trim()+targetEndpointAddress.getText().trim();
        String nameSpace=this.nameSpace.getText().trim();
        String method=this.methodName.getText().trim();
        resultArea.clear();
        doTestWebService(URL,nameSpace,method,filePath.getText().trim());





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
    public void onFileDropped(DragEvent dragEvent){
        Dragboard dragboard=dragEvent.getDragboard();
        if (dragEvent.isAccepted()) {
            List<File> fileList = dragboard.getFiles();
            filePath.setText(fileList.get(0).getAbsolutePath());
        }

    }
    @Override
    protected Node[] getDecorateArr() {
        return new Node[]{ipAndPort,targetEndpointAddress,nameSpace,methodName,filePath,linkBtn};
    }
    @Override
    protected TextInputControl[] getTextInputCtrl() {
        return new TextInputControl[]{ipAndPort,targetEndpointAddress,nameSpace,methodName};
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
