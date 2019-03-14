package com.yuntian.service;

import com.yuntian.esutils.EsClient;
import com.yuntian.esutils.EsDomian;
import com.yuntian.esutils.PageBean;

/**
 * Package: com.yuntian.service
 * <p>
 * Description： TODO
 * <p>
 * Author: lvjie
 * <p>
 * Date: Created in 2019/3/14/0014 14:59
 * <p>
 * Version: 0.0.1
 */
public interface ITestService {
    PageBean<EsDomian> search(String kw, Integer pageNum, Integer pageSize);
}
