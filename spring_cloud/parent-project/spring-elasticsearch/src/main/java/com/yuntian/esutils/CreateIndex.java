package com.yuntian.esutils;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>创建索引</p>
 * 2019/3/14/0014 14:01
 *
 * @author lvjie
 */
public class CreateIndex {
    public static Client client;
    static {
        Settings settings = Settings.builder()
                .put("cluster.name", "hwwyq-es").build();//链接服务器名称
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(
                            new InetSocketTransportAddress(InetAddress
                                    .getByName("223.203.100.135"), 9300))      //服务器的第一个节点服务器
                    .addTransportAddress(
                            new InetSocketTransportAddress(InetAddress
                                    .getByName("223.203.100.131"), 9300))      //服务器的第二个节点服务器
                    .addTransportAddress(
                            new InetSocketTransportAddress(InetAddress
                                    .getByName("223.203.100.132"), 9300));      //服务器的第三个节点服务器

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //创建索引
    public void createOneIndex() throws Exception {
        IndicesExistsResponse resp = client.admin().indices().prepareExists("index1").execute().actionGet();
        if(resp.isExists()){   //先查询有没有该索引库，有就删除
            client.admin().indices().prepareDelete("index1").execute().actionGet();
        }
        client.admin().indices().prepareCreate("index1").execute().actionGet();//没有就创建

        new XContentFactory();  //处理web2.5兼容
        //创建索引    基本都是固定格式
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
                .startObject("htmlbean")
                .startObject("properties")
                .startObject("name").field("type", "string")//名称
                .endObject()
                .startObject("title").field("type", "string")//标题
                .field("analyzer", "ik_max_word")
                .field("search_analyzer", "ik_max_word")
                .endObject()
                .startObject("author").field("type", "string")//作者
                .field("index", "not_analyzed")//设置不
                .endObject()
                .startObject("age").field("type", "string")//年龄
                .field("index", "not_analyzed")
                .endObject()
                .endObject().endObject().endObject();
        PutMappingRequest mapping = Requests.putMappingRequest("index1").type("htmlbean").source(builder);
        client.admin().indices().putMapping(mapping).actionGet();

    }
    //往索引中添加数据
    public void addHtml(){
        for(int i=0;i<20;i++){
            Map<String, String> dataMap =new HashMap<String, String>();
            dataMap.put("name", "张三"+i);//名称
            dataMap.put("title", "这是一个测试标题"+i);//标题
            dataMap.put("author", "艾夫斯基"+i);//作者
            dataMap.put("age", (20+i)+"");//年纪
            //写索引
            client.prepareIndex("index1", "htmlbean").setSource(dataMap).execute().actionGet();
        }
    }

    public static void main(String[] args) {
        CreateIndex create = new CreateIndex();
        try {
          //  create.createOneIndex();
            create.addHtml();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
