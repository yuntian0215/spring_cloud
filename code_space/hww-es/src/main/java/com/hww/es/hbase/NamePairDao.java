package com.hww.es.hbase;

import java.io.IOException;
import java.util.Map;
/**
 * 获取hbase中的数据接口
 * @author User
 *
 */
public interface NamePairDao {
	/**
	 * 
	 * @param rowKey	hbase的rowkey
	 * @return
	 * @throws IOException
	 */
	public Map<String,Integer> getNamePairByRowKey(String rowKey) throws IOException;
	/**
	 * 
	 * @param name 搜索人物
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 * @throws IOException
	 */
	public Map<String,Integer> getNamePairByRowKey(String name,String startTime,String endTime) throws IOException;
}
