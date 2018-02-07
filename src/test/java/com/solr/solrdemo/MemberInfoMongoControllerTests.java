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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SolrDemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MemberInfoMongoControllerTests {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Before
    public void setup(){
        //初始化MockMvc 对象
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 测试向mongo添加数据
     */
    @Test
    public void insert(){
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        MvcResult result;
        try {
            String requestJson = ow.writeValueAsString(map);
            result = mockMvc.perform(post("/demo/v1/member").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
                    .andExpect(status().isOk())
                    //  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andReturn();
            System.out.println(result.getResponse().getContentAsString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 测试向mongo修改数据
     */
    @Test
    public void update(){
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        MvcResult result;
        try {
            String requestJson = ow.writeValueAsString(map);
            result = mockMvc.perform(put("/demo/v1/member").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
                    .andExpect(status().isOk())
                    //  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andReturn();
            System.out.println(result.getResponse().getContentAsString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 测试向mongo查询数据
     */
    @Test
    public void findCode(){
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        MvcResult result;
        try {
            map.put("code","1001");
            String requestJson = ow.writeValueAsString(map);
            result = mockMvc.perform(get("/demo/v1/member/code").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
                    .andExpect(status().isOk())
                    //  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andReturn();
            System.out.println(result.getResponse().getContentAsString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 测试向mongo查询数据
     */
    @Test
    public void findSex(){
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        MvcResult result;
        try {
            map.put("sex",1);
            String requestJson = ow.writeValueAsString(map);
            result = mockMvc.perform(get("/demo/v1/member/sex").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
                    .andExpect(status().isOk())
                    //  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andReturn();
            System.out.println(result.getResponse().getContentAsString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 测试向mongo删除数据
     */
    @Test
    public void del(){
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        MvcResult result;
        try {
            map.put("id",1);
            String requestJson = ow.writeValueAsString(map);
            result = mockMvc.perform(delete("/demo/v1/member/sex").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
                    .andExpect(status().isOk())
                    //  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andReturn();
            System.out.println(result.getResponse().getContentAsString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 测试查询分页数据
     */
    @Test
    public void findList(){
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        MvcResult result;
        try {
            map.put("id",1);
            String requestJson = ow.writeValueAsString(map);
            result = mockMvc.perform(get("/demo/v1/member/list").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
                    .andExpect(status().isOk())
                    //  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andReturn();
            System.out.println(result.getResponse().getContentAsString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
