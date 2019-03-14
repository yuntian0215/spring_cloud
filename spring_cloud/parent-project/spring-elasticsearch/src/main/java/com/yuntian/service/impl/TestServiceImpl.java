package com.yuntian.service.impl;

import com.yuntian.esutils.EsDomian;
import com.yuntian.esutils.PageBean;
import com.yuntian.service.ITestService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>定义查询条件和逻辑</p>
 * 2019/3/14/0014 15:00
 *
 * @author lvjie
 */
@Service
public class TestServiceImpl implements ITestService {
    @Resource
    private Client client;
    @Override
    public PageBean<EsDomian> search(String kw, Integer pageNum, Integer pageSize) {
        PageBean<EsDomian> wr = new PageBean<EsDomian>();
        wr.setIndex(pageNum);

        MultiMatchQueryBuilder q =new MultiMatchQueryBuilder(kw, new String[]{"name","title"});
        SearchResponse resp=null;
        if(wr.getIndex()==1){
            resp = client.prepareSearch("index1")
                    .setTypes("htmlbean")
                    .setQuery(q)
                    .setFrom(0)
                    .setSize(5)
                    .execute().actionGet();
        }else{
            wr.setTotalCount(pageSize);
            resp = client.prepareSearch("index1")
                    .setTypes("htmlbean")
                    .setQuery(q)
                    .setFrom(wr.getStartRow())
                    .setSize(5)
                    .execute().actionGet();
        }
        SearchHits hits= resp.getHits();
        wr.setTotalCount((int)hits.getTotalHits());

        for(SearchHit hit : hits.getHits()){
            EsDomian bean =new EsDomian();
            if(hit.getHighlightFields().get("title")==null){//title中没有包含关键字
                bean.setTitle(hit.getSource().get("title").toString());//获取原来的title（没有高亮的title）
            }else{
                bean.setTitle(hit.getHighlightFields().get("title").getFragments()[0].toString());
            }
            if(hit.getHighlightFields().get("name")==null){//name中没有包含关键字
                bean.setName(hit.getSource().get("name").toString());//获取原来的name（没有高亮的content）
            }else{
                bean.setName(hit.getHighlightFields().get("name").getFragments()[0].toString());
            }
            bean.setAuthor(hit.getSource().get("author").toString());
            bean.setAge(hit.getSource().get("age").toString());
            wr.setBean(bean);
        }
        return wr;
    }
}
