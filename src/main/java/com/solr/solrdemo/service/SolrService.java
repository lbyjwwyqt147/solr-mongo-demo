package com.solr.solrdemo.service;

import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public interface  SolrService {


    /**
     *@ClassDescribe:向solr插入数据
     *@param coreName 核心名称
     *@param input 数据封装
     *@return
     */
    public boolean pushDataIntoSolr(String coreName ,SolrInputDocument input);

    /**
     *@ClassDescribe:按条件查询搜索引擎
     *@param query solr查询条件
     *@return 返回查询集合
     */
    public SolrDocumentList querySolrIndex(String coreName, String query);
}
