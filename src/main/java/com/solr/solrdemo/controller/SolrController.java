package com.solr.solrdemo.controller;

import com.solr.solrdemo.domian.BaseResponse;
import com.solr.solrdemo.domian.MemberInfo;
import com.solr.solrdemo.domian.PushDataIntoSolrRequest;
import com.solr.solrdemo.domian.QuerySolrIndexRequest;
import com.solr.solrdemo.service.SolrService;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping(value = "/solr")
public class SolrController {

    @Autowired
    private SolrService solrService;

    /**
     * 向solr中加入数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/pushDataIntoSolr",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse pushDataIntoSolr(@RequestBody PushDataIntoSolrRequest request){
        BaseResponse baseResponse = new BaseResponse();
        SolrInputDocument inputDocument = new SolrInputDocument();
        for (Map.Entry<String,Object> entry : request.getInput().entrySet()){
            inputDocument.addField(entry.getKey(),entry.getValue());
        }
        if(!solrService.pushDataIntoSolr(request.getCoreName(),inputDocument)){
            baseResponse.setErrorCode("500");
            baseResponse.setErrorMsg("插入失败,请稍后重试.");
        }else {
            baseResponse.setSubMsg("请求成功,数据插入成功.");
        }
        return  baseResponse;
    }

    /**
     *  按条件查询搜索引擎
     * @param request
     * @return
     */
    @RequestMapping(value = "/querySolrIndex",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    public SolrDocumentList querySolrIndex(@RequestBody QuerySolrIndexRequest request){

        return solrService.querySolrIndex(request.getCoreName(),request.getQuery());
    }



}
