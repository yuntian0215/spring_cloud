package com.hww.es.hbase.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;
import com.hww.es.hbase.HBaseDao;
import com.hww.es.util.HbaseConf;

@Repository
public class HbaseDaoImpl implements HBaseDao {

	Configuration conf = HbaseConf.getHbaseConfiguration();
	
	@Override
	public Result get(String tableName, String rowkey) throws IOException {
		HTable table = new HTable(conf, tableName);
		Get get = new Get(Bytes.toBytes(rowkey));
		Result result = table.get(get);
		table.close();
		return result;
	}

	@Override
	public Result[] get(String tableName, List<String> rowkeys) throws IOException {
		// TODO Auto-generated method stub
		HTable table = new HTable(conf, tableName);
		List<Get> gets = new ArrayList<Get>();
		for(String rowkey : rowkeys) {
			Get get = new Get(Bytes.toBytes(rowkey));
			gets.add(get);
		}
		Result[] results = table.get(gets);
		table.close();
		return results;
	}
}
