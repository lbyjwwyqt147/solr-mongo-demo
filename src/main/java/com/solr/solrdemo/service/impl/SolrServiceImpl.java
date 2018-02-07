package com.solr.solrdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.solr.solrdemo.service.SolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;


@Service
//@ConfigurationProperties(prefix = "solr")
public class SolrServiceImpl implements SolrService {

    private Logger logger  = LoggerFactory.getLogger(SolrServiceImpl.class);

    private String httpSolrClient = "http://192.168.255.128:8983/solr/";
    public String getHttpSolrClient() {
        return httpSolrClient;
    }

    public void setHttpSolrClient(String httpSolrClient) {
        this.httpSolrClient = httpSolrClient;
    }

    private HttpSolrClient connetHttpSolrClientServer(String coreName){
        HttpSolrClient server = new HttpSolrClient(httpSolrClient + coreName);
        server.setSoTimeout(5000);
        server.setConnectionTimeout(1000);
        server.setDefaultMaxConnectionsPerHost(1000);
        server.setMaxTotalConnections(5000);
        return server;
    }

    @Autowired
    private  SolrClient solrClient;

    @Override
    public boolean pushDataIntoSolr(String coreName, SolrInputDocument input) {
        boolean flag = false;
        try {
            solrClient = connetHttpSolrClientServer(coreName);
            solrClient.add(input);
            solrClient.commit();
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }finally {
            try {
                solrClient.close();
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }

        return flag;
    }

    //查询
    @Override
    public SolrDocumentList querySolrIndex(String coreName, String query) {
        SolrDocumentList list = null;
        try {
            solrClient = connetHttpSolrClientServer(coreName);
            QueryResponse rsp = null;
            SolrQuery  solrQuery = new SolrQuery("*:*");
            solrQuery.addFilterQuery(query);
            rsp = solrClient.query(solrQuery);
            list = rsp.getResults();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }finally {
            try {
                solrClient.close();
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
        System.out.println("查询结果大小:"+list.size());
        System.out.println("查询结果:"+ JSON.toJSONString(list));
        return list;
    }
}
