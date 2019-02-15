package com.hww.es.hbase;


import java.io.IOException;
import java.util.List;

import org.apache.hadoop.hbase.client.Result;

/**
 * Hbase基础操作
 * @author fuxuan
 *
 */
public interface HBaseDao {

	/**
	 * 根据rowkey查一个
	 * @param tableName
	 * @param rowkey
	 * @return
	 * @throws IOException
	 */
	public Result get(String tableName,String rowkey) throws IOException;

	/**
	 * 多个rowkey批量查
	 * @param tableName
	 * @param rowkeys
	 * @return
	 * @throws IOException
	 */
	public Result[] get(String tableName, List<String> rowkeys) throws IOException;
}
