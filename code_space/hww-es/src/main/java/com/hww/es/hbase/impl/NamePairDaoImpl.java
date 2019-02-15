package com.hww.es.hbase.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

import com.hww.es.hbase.NamePairDao;
import com.hww.es.util.HbaseConf;
import com.hww.es.util.MyDateUtil;
/**
 * 根据rowkey读取hbase内数据
 * 
 * @author User
 *
 */
@Repository
public class NamePairDaoImpl implements NamePairDao {

	Configuration conf = HbaseConf.getHbaseConfiguration();

	/**
	 * rowkey 根据hbase的rowkoy搜索
	 */
	@Override
	public Map<String, Integer> getNamePairByRowKey(String rowKey) throws IOException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		HTable table = new HTable(conf, "namePairCount");
		Scan scan = new Scan();
		String date = rowKey.split("_")[0];
		String name = rowKey.split("_")[1];
		RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(date));
		rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(name));

		scan.setFilter(rowFilter);
		ResultScanner scanner = table.getScanner(scan);
		for (Result res : scanner) {
			String namePair = Bytes.toString(res.getValue(Bytes.toBytes("base_info"), Bytes.toBytes("namePair")));// 人名对
			String count = Bytes.toString(res.getValue(Bytes.toBytes("base_info"), Bytes.toBytes("count")));// 匹配值
			map.put(namePair, Integer.valueOf(count));
		}
		table.close();
		return map;
	}

	/**
	 * name:传入人物名称
	 * startTime:查询开始时间
	 * endTime：查询结束时间
	 */
	@Override
	public Map<String, Integer> getNamePairByRowKey(String name, String startTime, String endTime) throws IOException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		HTable table = new HTable(conf, "namePairCount");
		Scan scan = new Scan();

		scan.setStartRow(Bytes.toBytes(startTime));
		String endDate = MyDateUtil.getSpecifiedDayAfter(endTime);//传入时间后一天，因hbaseScan的特性 包含前面不包含后面
		scan.setStopRow(Bytes.toBytes(endDate));

		String nameF = "#" + name + "#";
		RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(nameF));
		scan.setFilter(rowFilter);
		ResultScanner scanner = table.getScanner(scan);
		for (Result res : scanner) {
			String namePair = Bytes.toString(res.getValue(Bytes.toBytes("base_info"), Bytes.toBytes("namePair")));// 人名对
			String count = Bytes.toString(res.getValue(Bytes.toBytes("base_info"), Bytes.toBytes("count")));// 匹配值
			
			if(map.containsKey(namePair)){
				Integer va =map.get(namePair)+Integer.valueOf(count);
				map.put(namePair, va);
			}else{
			map.put(namePair, Integer.valueOf(count));
			}			
		}
		table.close();
		return map;
	}

}