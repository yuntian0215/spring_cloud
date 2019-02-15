package com.hww.es.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

public class HbaseConf {
	static Configuration conf = null;
	public static Configuration getHbaseConfiguration() {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "10.0.12.3:2181,10.0.12.4:2181,10.0.12.5:2181");
		return conf;
	}
}
