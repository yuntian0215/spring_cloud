package com.hww.es.util;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Es的Client
 * @author lvjie
 * @2018年1月30日
 */
@Configuration
public class EsClient {
	/**
     * el集群地址1
     */
    @Value("${elasticsearch.ip1}")
    private String hostName1;
    /**
     * el集群地址2
     */
    @Value("${elasticsearch.ip2}")
    private String hostName2;
    /**
     * el集群地址3
     */
    @Value("${elasticsearch.ip3}")
    private String hostName3;
    /**
     * 端口
     */
    @Value("${elasticsearch.port}")
    private String port;
    /**
     * 集群名称
     */
    @Value("${elasticsearch.cluster.name}")
    private String clusterName;
    @Bean
	public Client client() throws Exception{
		Settings settings = Settings.builder()
				.put("cluster.name", clusterName).build();//链接服务器名称
		Client client= new PreBuiltTransportClient(settings)
				.addTransportAddress(
						new InetSocketTransportAddress(InetAddress
								.getByName(hostName1), Integer.valueOf(port)))      //服务器的第一个节点服务器
				.addTransportAddress(
						new InetSocketTransportAddress(InetAddress
								.getByName(hostName2), Integer.valueOf(port)))      //服务器的第二个节点服务器
				.addTransportAddress(
						new InetSocketTransportAddress(InetAddress
								.getByName(hostName3), Integer.valueOf(port)));      //服务器的第三个节点服务器
		return client;
	}
}
