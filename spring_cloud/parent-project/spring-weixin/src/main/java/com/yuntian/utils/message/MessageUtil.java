package com.yuntian.utils.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
/**
 * <p>返回消息转格式</p>
 * 2019年4月3日下午3:55:15
 * @author lvjie
 */
public class MessageUtil {
	// 请求消息类型：文本
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    // 请求消息类型：图片
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    // 请求消息类型：地理位置
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    // 请求消息类型：链接
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    // 请求消息类型：事件推送
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    // 事件类型：subscribe(订阅)
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    // 事件类型：unsubscribe(取消订阅)
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    // 事件类型：scan(用户已关注时的扫描带参数二维码)
    public static final String EVENT_TYPE_SCAN = "scan";
    // 事件类型：LOCATION(上报地理位置)
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    // 事件类型：CLICK(自定义菜单)
    public static final String EVENT_TYPE_CLICK = "CLICK";
 
    // 响应消息类型：文本
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    // 响应消息类型：图片
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    // 响应消息类型：图文
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";
 
    /**
     * 将XML转换成Map集合
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String>xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
         
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();            // 使用dom4j解析xml
        InputStream ins = request.getInputStream(); // 从request中获取输入流
        Document doc = reader.read(ins);
         
        Element root = doc.getRootElement();         // 获取根元素
        List<Element> list = root.elements();        // 获取所有节点
         
        for (Element e : list) {
            map.put(e.getName(), e.getText()); 
            System.out.println(e.getName() + "--->" + e.getText());
        }
        ins.close();
        return map;
    }
 
    /**
     * 对象到xml的处理
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;
 
                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }
 
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
 
    /**
     * 文本消息对象转换成xml
     * 
     * @param textMessage 文本消息对象
     * @return xml
     */
    public static String messageToXml(TextMeaasge textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }
    
    /**
     * 图文消息对象转换成xml
     * 
     * @param newsMessage 图文消息对象
     * @return xml
     */
    public static String messageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }
 
    /**
     * 图片消息对象转换成xml
     * 
     * @param imageMessage 图片消息对象
     * @return xml
     */
    public static String messageToXml(ImageMessage imageMessage) {
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }
}

