package com.solr.solrdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.solr.solrdemo.domian.PushDataIntoSolrRequest;
import com.solr.solrdemo.domian.QuerySolrIndexRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SolrDemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SolrTests {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Before
    public void setup(){
        //初始化MockMvc 对象
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 测试向solr添加数据
     */
    @Test
    public void pushDataIntoSolr(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",4);
        map.put("title",4);
        map.put("content","4444");
        PushDataIntoSolrRequest request = new PushDataIntoSolrRequest();
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        request.setInput(map);
        request.setCoreName("new_core");
        MvcResult result;
        try {
            String requestJson = ow.writeValueAsString(request);
            result = mockMvc.perform(post("/solr/pushDataIntoSolr").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
                    .andExpect(status().isOk())
                  //  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andReturn();
            System.out.println(result.getResponse().getContentAsString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //查询solr方法
    @Test
    public void querySolrIndex(){
        MvcResult result;
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        QuerySolrIndexRequest request = new QuerySolrIndexRequest();
        try {
            request.setCoreName("new_core");
           // request.setQuery("content:4444");
            java.lang.String requestJson = ow.writeValueAsString(request);
            result = mockMvc.perform(get("/solr/querySolrIndex").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
                    .andExpect(status().isOk())
                 //   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                    .andReturn();
            System.out.println( result.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
