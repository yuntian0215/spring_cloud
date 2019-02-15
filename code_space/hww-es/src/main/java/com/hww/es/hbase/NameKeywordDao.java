package com.hww.es.hbase;

import java.io.IOException;
import java.util.List;

public interface NameKeywordDao {

	/**
	 * 根据rowkey查询
	 * @param rowKey
	 * @return
	 * @throws IOException
	 */
	public List<String> getKeywordsByRowKey(String rowKey) throws IOException;
	
	/**
	 * 根据日期。人名扫描
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws IOException
	 */
	public List<String> getKeywordsByDateScan(String name,String startDate,String endDate) throws IOException;
}
