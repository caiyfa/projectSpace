package test;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
 

public class TESTWebservice {
	public static void main(String[] args) {
		Service service = new Service();
		try {
			String url = "http://localhost:8092/uapws/service/nc.itf.pfxx.synchdata.IPfxxWs";
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(url));
			// 注册命名空间下的接口方法名
			call.setOperationName(new QName("http://synchdata.pfxx.itf.nc/IPfxxWs", "synchronizeData"));
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
			String backMsg = getXML();
			// 设置客户端调用超时时间 为100分钟
			call.setTimeout(6000);
			String res = "";
			long t1=System.currentTimeMillis();
			res = (String) call.invoke(new String[] { backMsg ,"PFXX"});
			long t2=System.currentTimeMillis();
			System.out.println("处理耗时:"+(t2-t1)+"ms ，处理结果："+res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print(e);
			
		}

	}

	private static String getXML()    {
	StringBuffer sb=new StringBuffer();
	 
	sb.append("<?xml version=\"1.0\" encoding='UTF-8'?>");
	sb.append("<ufinterface account=\"develop\" billtype=\"vouchergl\" businessunitcode=\"develop\" filename=\"\" groupcode=\"STMG_1\" isexchange=\"\" orgcode=\"00101\" receiver=\"0001A11000000000033B\" replace=\"\" roottag=\"\" sender=\"001\">");
	sb.append("	<voucher>");
	sb.append("		<voucher_head>");
	sb.append("			<pk_voucher>1001A210000000001E7T</pk_voucher>");
	sb.append("			<pk_vouchertype>01</pk_vouchertype>");
	sb.append("			<year>2019</year>");
	sb.append("			<pk_system>GL</pk_system>");
	sb.append("			<voucherkind>0</voucherkind>");
	sb.append("			<pk_accountingbook>00101-0001</pk_accountingbook>");
	sb.append("			<discardflag>N</discardflag>");
	sb.append("			<period>03</period>");
	sb.append("			<no>1</no>");
	sb.append("			<attachment>0</attachment>");
	sb.append("			<prepareddate>2019-03-01 14:09:08</prepareddate>");
	sb.append("			<pk_prepared>sk</pk_prepared>");
	sb.append("			<pk_casher></pk_casher>");
	sb.append("			<signflag>N</signflag>");
	sb.append("			<pk_checked></pk_checked>");
	sb.append("			<tallydate></tallydate>");
	sb.append("			<pk_manager></pk_manager>");
	sb.append("			<memo1></memo1>");
	sb.append("			<memo2></memo2>");
	sb.append("			<reserve1></reserve1>");
	sb.append("			<reserve2>N</reserve2>");
	sb.append("			<siscardflag />");
	sb.append("			<pk_org>00101</pk_org>");
	sb.append("			<pk_org_v>00101</pk_org_v>");
	sb.append("			<pk_group>STMG_1</pk_group>");
	sb.append("			<details>");
	sb.append("				<item>");
	sb.append("					<detailindex>1</detailindex>");
	sb.append("					<accsubjcode>6001</accsubjcode>");
	sb.append("					<explanation>123</explanation>");
	sb.append("					<verifydate>2019-03-01 14:09:08</verifydate>");
	sb.append("					<price>0.00000000</price>");
	sb.append("					<excrate2>1</excrate2>");
	sb.append("					<debitquantity>0.00000000</debitquantity>");
	sb.append("					<debitamount>-40</debitamount>");
	sb.append("					<groupdebitamount>-40</groupdebitamount>");
	sb.append("					<globaldebitamount>-40</globaldebitamount>");
	sb.append("					<localdebitamount>-40</localdebitamount>");
	sb.append("					<creditquantity>0.00000000</creditquantity>");
	sb.append("					<creditamount>0.00000000</creditamount>");
	sb.append("					<groupcreditamount>0.00000000</groupcreditamount>");
	sb.append("					<globalcreditamount>0.00000000</globalcreditamount>");
	sb.append("					<localcreditamount>0.00000000</localcreditamount>");
	sb.append("					<billtype></billtype>");
	sb.append("					<bankaccount></bankaccount>");
	sb.append("					<checkstyle></checkstyle>");
	sb.append("					<checkno></checkno>");
	sb.append("					<checkdate></checkdate>");
	sb.append("					<bill_id></bill_id>");
	sb.append("					<bill_date></bill_date>");
	sb.append("					<pk_currtype>CNY</pk_currtype>");
	sb.append("					<pk_accasoa>6001</pk_accasoa>");
	sb.append("					<ass>");
	sb.append("						<item>");
	sb.append("							<pk_Checktype>0001</pk_Checktype>");
	sb.append("							<pk_Checkvalue>A.00</pk_Checkvalue></item>");
	sb.append("						<item>");
	sb.append("							<pk_Checktype>ST-GL002</pk_Checktype>");
	sb.append("							<pk_Checkvalue>A0001</pk_Checkvalue></item>");
	sb.append("					</ass>");
	sb.append("				</item>");
	sb.append("				<item>");
	sb.append("					<detailindex>2</detailindex>");
	sb.append("					<accsubjcode>6001</accsubjcode>");
	sb.append("					<explanation>123</explanation>");
	sb.append("					<verifydate>2019-03-01 14:09:08</verifydate>");
	sb.append("					<price>0.00000000</price>");
	sb.append("					<excrate2>1</excrate2>");
	sb.append("					<debitquantity>0.00000000</debitquantity>");
	sb.append("					<debitamount>-40</debitamount>");
	sb.append("					<groupdebitamount>-40</groupdebitamount>");
	sb.append("					<globaldebitamount>-40</globaldebitamount>");
	sb.append("					<localdebitamount>-40</localdebitamount>");
	sb.append("					<creditquantity>0.00000000</creditquantity>");
	sb.append("					<creditamount>0.00000000</creditamount>");
	sb.append("					<groupcreditamount>0.00000000</groupcreditamount>");
	sb.append("					<globalcreditamount>0.00000000</globalcreditamount>");
	sb.append("					<localcreditamount>0.00000000</localcreditamount>");
	sb.append("					<billtype></billtype>");
	sb.append("					<bankaccount></bankaccount>");
	sb.append("					<checkstyle></checkstyle>");
	sb.append("					<checkno></checkno>");
	sb.append("					<checkdate></checkdate>");
	sb.append("					<bill_id></bill_id>");
	sb.append("					<bill_date></bill_date>");
	sb.append("					<pk_currtype>CNY</pk_currtype>");
	sb.append("					<pk_accasoa>6001</pk_accasoa>");
	sb.append("					<ass>");
	sb.append("						<item>");
	sb.append("							<pk_Checktype>0001</pk_Checktype>");
	sb.append("							<pk_Checkvalue>A.00001</pk_Checkvalue></item>");
	sb.append("						<item>");
	sb.append("							<pk_Checktype>ST-GL002</pk_Checktype>");
	sb.append("							<pk_Checkvalue>A0001</pk_Checkvalue></item>");
	sb.append("					</ass>");
	sb.append("				</item>");
	sb.append("				<item>");
	sb.append("					<detailindex>3</detailindex>");
	sb.append("					<accsubjcode>6001</accsubjcode>");
	sb.append("					<explanation>123</explanation>");
	sb.append("					<verifydate>2019-03-01 14:09:08</verifydate>");
	sb.append("					<price>0.00000000</price>");
	sb.append("					<excrate2>1</excrate2>");
	sb.append("					<debitquantity>0.00000000</debitquantity>");
	sb.append("					<debitamount>-20</debitamount>");
	sb.append("					<groupdebitamount>-20</groupdebitamount>");
	sb.append("					<globaldebitamount>-20</globaldebitamount>");
	sb.append("					<localdebitamount>-20</localdebitamount>");
	sb.append("					<creditquantity>0.00000000</creditquantity>");
	sb.append("					<creditamount>0.00000000</creditamount>");
	sb.append("					<groupcreditamount>0.00000000</groupcreditamount>");
	sb.append("					<globalcreditamount>0.00000000</globalcreditamount>");
	sb.append("					<localcreditamount>0.00000000</localcreditamount>");
	sb.append("					<billtype></billtype>");
	sb.append("					<bankaccount></bankaccount>");
	sb.append("					<checkstyle></checkstyle>");
	sb.append("					<checkno></checkno>");
	sb.append("					<checkdate></checkdate>");
	sb.append("					<bill_id></bill_id>");
	sb.append("					<bill_date></bill_date>");
	sb.append("					<pk_currtype>CNY</pk_currtype>");
	sb.append("					<pk_accasoa>6001</pk_accasoa>");
	sb.append("					<ass>");
	sb.append("						<item>");
	sb.append("							<pk_Checktype>0001</pk_Checktype>");
	sb.append("							<pk_Checkvalue>L.001</pk_Checkvalue></item>");
	sb.append("						<item>");
	sb.append("							<pk_Checktype>ST-GL002</pk_Checktype>");
	sb.append("							<pk_Checkvalue>A0001</pk_Checkvalue></item>");
	sb.append("					</ass>");
	sb.append("				</item>");
	sb.append("				<item>");
	sb.append("					<detailindex>4</detailindex>");
	sb.append("					<accsubjcode>1001</accsubjcode>");
	sb.append("					<explanation>123</explanation>");
	sb.append("					<verifydate>2019-03-01 14:09:08</verifydate>");
	sb.append("					<price>0.00000000</price>");
	sb.append("					<excrate2>1</excrate2>");
	sb.append("					<debitquantity>0.00000000</debitquantity>");
	sb.append("					<debitamount>100</debitamount>");
	sb.append("					<groupdebitamount>100</groupdebitamount>");
	sb.append("					<globaldebitamount>100</globaldebitamount>");
	sb.append("					<localdebitamount>100</localdebitamount>");
	sb.append("					<creditquantity>0.00000000</creditquantity>");
	sb.append("					<creditamount>0.00000000</creditamount>");
	sb.append("					<groupcreditamount>0.00000000</groupcreditamount>");
	sb.append("					<globalcreditamount>0.00000000</globalcreditamount>");
	sb.append("					<localcreditamount>0.00000000</localcreditamount>");
	sb.append("					<billtype></billtype>");
	sb.append("					<bankaccount></bankaccount>");
	sb.append("					<checkstyle></checkstyle>");
	sb.append("					<checkno></checkno>");
	sb.append("					<checkdate></checkdate>");
	sb.append("					<bill_id></bill_id>");
	sb.append("					<bill_date></bill_date>");
	sb.append("					<pk_currtype>CNY</pk_currtype>");
	sb.append("					<pk_accasoa>1001</pk_accasoa>");
	sb.append("				</item>");
	sb.append("			</details>");
	sb.append("		</voucher_head>");
	sb.append("	</voucher>");
	sb.append("</ufinterface>");

	 
return sb.toString();
		
 	}
}
