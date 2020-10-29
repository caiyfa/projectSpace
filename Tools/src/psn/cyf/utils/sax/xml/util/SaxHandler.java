package psn.cyf.utils.sax.xml.util;

import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import psn.cyf.base.vo.BaseVO;
import psn.cyf.exception.RunException;
import psn.cyf.utils.sax.xml.util.vo.EventVO;
import psn.cyf.utils.sax.xml.util.vo.ParcelVO;
import psn.cyf.utils.sax.xml.util.vo.Status3x3VO;


class SaxHandler extends DefaultHandler {
    private String currentTag = null;
    private String currentValue = null;
    //全局数据
    private String direction=null;
    private String clientCode=null;
    private String clientSubCode=null;
    private ParcelVO parcelVO=null;
    /**
     * 当前所处路径
     */
    private StringBuffer currPath=new StringBuffer();

    public SaxHandler( ) {
    }


    /**
     * 解析文档结束
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    /**
     * 解析文档结束
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    /**
     * 开始某个元素
     * 这里主要作用是记录位置信息，
     * 将当前标签名放入currentTag缓存字符串
     * @param uri uri
     * @param localName localName
     * @param qName qName
     * @param attributes attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        currPath.append(".").append(qName);
        currentTag=qName;
        if("parcel".equals(qName)){
            parcelVO=new ParcelVO();
        }
    }

    /**
     * 该方法是获取标签内容的方法
     * @param ch 内容数组
     * @param start 开始
     * @param length    结束
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //判断当前值是否有意义
        if(isMeaningFulValue(ch, start, length)){
            //填充全局变量 direction/clientCode,clientSubCode
            paddingGlobalVariable();
            try {
                paddingParcleVO();
            } catch (RunException e) {
                e.printStackTrace();
            }
        }

        //清理缓存
        currentTag=null;
        currentValue=null;
    }
    /**
     *
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //更新当前所在路径
        String oldPath=currPath.toString();
        currPath=new StringBuffer(oldPath.substring(0,currPath.toString().lastIndexOf("."+qName)));
        if("parcel".equals(qName)){
//            System.out.println(this.parcelVO.toString());
            try {
                JSONObject jsonObject=toJson(parcelVO);
                System.out.println(jsonObject);
            } catch (RunException e) {
                e.printStackTrace();
            }

            this.parcelVO=null;
        }


    }
    private JSONObject toJson(ParcelVO parcelVO) throws RunException {
        JSONObject resJson=new JSONObject();
        resJson.put("direction",direction);
        resJson.put("clientCode",clientCode);
        resJson.put("clientSubCode",clientSubCode);
       String[]  parcelVOFields=parcelVO.getFieldNames();
       for(String parcelField:parcelVOFields){
           //判断元素内容是否是BaseVO变量

               if("event".equals(parcelField)){
                   BaseVO eventVO=parcelVO.getEvent();
                   resJson.put("event",transferBaseVO(eventVO));
               }else {
                   resJson.put(parcelField,parcelVO.getFieldValue(parcelField));
               }


       }
        return resJson;
    }
    private JSONObject transferBaseVO(BaseVO baseVO) throws RunException {
        JSONObject jsonObject=new JSONObject();
        String[]  fieldNames=baseVO.getFieldNames();
        for(String field:fieldNames){
           Object value=baseVO.getFieldValue(field);
            if(value instanceof BaseVO){
                jsonObject.put(field,transferBaseVO((BaseVO) baseVO.getFieldValue(field)));
            }else{
                jsonObject.put(field,baseVO.getFieldValue(field));
            }
        }
        return jsonObject;
    }
    /**
     * 填充parcel 内容
     */
    private void paddingParcleVO() throws RunException {
        //在parcel中但在event外。主要判断是否是基础数据。下层无标签嵌套
        if(isInTag("parcel")&&!isInTag("event")){

                parcelVO.setFieldValue(currentTag,currentValue);

        }
        if( isInTag("event")&&("eventTimestamp".equals(currentTag)||"tour".equals(currentTag))){

            parcelVO.getEvent().setFieldValue(currentTag,currentValue);
        }
        if(isFollowTag("status3x3",currentTag)){
            parcelVO.getEvent().getStatus3x3().setFieldValue(currentTag,currentValue);
        }
        if(isFollowTag("status2x2",currentTag)){
            parcelVO.getEvent().getStatus2x2().setFieldValue(currentTag,currentValue);
        }
        if(isFollowTag("scanningUnit",currentTag)){
            parcelVO.getEvent().getScanningUnit().setFieldValue(currentTag,currentValue);
        }
    }




    /**
     * 填充全局变量
     */
    private void paddingGlobalVariable(){
        if("direction".equals(currentTag)){
            direction=currentValue;
        }
        if("clientCode".equals(currentTag)){
            clientCode=currentValue;
        }
        if("clientSubCode".equals(currentTag)){
            clientSubCode=currentValue;
        }
    }
    private boolean isMeaningFulValue(char[] ch, int start, int length){
        //存在当前标签
        if(currentTag!=null){
            //给当前值赋值
            currentValue=new String(ch,start,length);
            //判断当前值是否有意义空格、换行符等制表符剔除
            if(currentValue!=null&&currentValue.trim().length()!=0){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }

    }

    /**
     * 判断是否是最后一个标签
     * @param lastTag 上一个标签
     * @param paramTag  标签名
     * @return boolean
     */
    private boolean isFollowTag(String lastTag,String paramTag){
        return currPath.indexOf("."+lastTag+"."+paramTag)>-1;
    }
    /**
     * 存在该标签，并且该标签不是最后一个标签
     * @param paramTag  标签名
     * @return boolean
     */
    private boolean isInTag(String paramTag){
        String tag="."+paramTag;
        //存在该标签，并且该标签不是最后一个标签
        return currPath.indexOf(tag)>-1&& currPath.lastIndexOf(".")>currPath.indexOf(tag);
    }

}