package com.yuntian.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.Arrays;
/**
 * <p>处理微信URL校验</p>
 * 2019年4月3日下午2:45:28
 * @author lvjie
 */
@Component
public class CheckUtil {

	public static String token;//开发者自定义token
	@Value("${weixin.token}")
	public void setToken(String token) {
		CheckUtil.token = token;
	}
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		//定义数组存放 token,timestamp,nonce
		String[] arr = {token,timestamp,nonce};
		//对数组进行排序
		Arrays.sort(arr);
		//生成字符串
		StringBuffer sb = new StringBuffer();
		for(String s:arr){
			sb.append(s);
		}
		//sha1加密
		String temp = getSha1(sb.toString());
		//将加密的字符串与微信签名比较返回结果
		return temp.equals(signature);
	}
	//sha1加密
	public static String getSha1(String str){
		if(str==null||str.length()==0){
			return null;
		}
		char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));
			
			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j*2];
			int k = 0;
			for(int i=0;i<j;i++){
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}
}
