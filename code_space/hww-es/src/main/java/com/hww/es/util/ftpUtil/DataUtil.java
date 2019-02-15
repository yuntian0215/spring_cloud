package com.hww.es.util.ftpUtil;

import org.apache.commons.lang.StringUtils;

/**
 * <p>创建PDF时需要的工具类</p>
 * 2018年12月4日下午1:35:44
 * @author lvjie
 */
public class DataUtil {
	/**
	 * <p>分割路径</p>
	 * @param path
	 * @return 返回分割后的路径
	 * 2018年12月4日下午1:35:17
	 * @author lvjie
	 */
	public static String[] separatePath(String path){
        if(StringUtils.isBlank(path)){
            return null;
        }
        String[] sep = path.split("\\.");
        return new String[]{sep[0],sep[1]};
    }
}
