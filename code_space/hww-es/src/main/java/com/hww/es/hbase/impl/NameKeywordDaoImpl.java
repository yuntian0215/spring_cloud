package com.hww.es.hbase.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;
import com.hww.es.hbase.NameKeywordDao;
import com.hww.es.util.HbaseConf;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;

@Repository
public class NameKeywordDaoImpl implements NameKeywordDao{

	Configuration conf = HbaseConf.getHbaseConfiguration();
	
	@Override
	public List<String> getKeywordsByRowKey(String rowKey) throws IOException {
		HTable table = new HTable(conf, "namekeyword");
		Get get = new Get(Bytes.toBytes(rowKey));
		Result result = table.get(get);
		//String name = Bytes.toString(result.getValue(Bytes.toBytes("base_info"), Bytes.toBytes("name")));//人名
		String keywords = Bytes.toString(result.getValue(Bytes.toBytes("base_info"), Bytes.toBytes("keywords")));//关键字
		//String date = Bytes.toString(result.getValue(Bytes.toBytes("base_info"), Bytes.toBytes("date")));//日期
//		List<Cell> cells = result.listCells();
//		//遍历出result中所有的键值对
//		for(KeyValue kv : result.list()){
//			String family = new String(kv.getFamily());
//			System.out.println(family);
//			String qualifier = new String(kv.getQualifier());
//			System.out.println(qualifier);
//			System.out.println(new String(kv.getValue()));
//		}
		table.close();
		if(keywords == null) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		String[] keywordArray = keywords.split(",");
		for(String keyword : keywordArray) {
			list.add(keyword);
		}
		return list;
	}

	@Override
	public List<String> getKeywordsByDateScan(String name, String startDate, String endDate) throws IOException {
		List<String> list = new ArrayList<String>();
		HTable table = new HTable(conf, "namekeyword");
		Scan scan = new Scan();
		scan.setStartRow(Bytes.toBytes(startDate));
		scan.setStopRow(Bytes.toBytes(endDate));
		RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator("#" + name + "#"));
		scan.setFilter(rowFilter);
		ResultScanner scanner = table.getScanner(scan);

		for (Result res : scanner) {
			String keywords = Bytes.toString(res.getValue(Bytes.toBytes("base_info"), Bytes.toBytes("keywords")));
			String[] keywordArray = keywords.split(",");
			for(String keyword : keywordArray) {
				list.add(keyword);
			}
		}
		table.close();
		return list;
	}
}
