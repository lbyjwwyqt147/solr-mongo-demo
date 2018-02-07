package com.solr.solrdemo.controller;

import com.alibaba.fastjson.JSON;
import com.solr.solrdemo.domian.BaseResponse;
import com.solr.solrdemo.domian.MemberInfo;
import com.solr.solrdemo.domian.Pagination;
import com.solr.solrdemo.service.MemberInfoMongoService;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class MemberInfoMongoController extends AbstractController{

    @Autowired
    private MemberInfoMongoService memberInfoMongoService;


    @RequestMapping(value = "/member",method = RequestMethod.POST)
    public  BaseResponse insertMember(){
        BaseResponse baseResponse = new BaseResponse();
        try {
            List<MemberInfo> list = new ArrayList<>();
            for (int i = 10;i<1000;i++){
                MemberInfo memberInfo = new MemberInfo();
                memberInfo.setId(i);
                memberInfo.setLastData(new Date());
                memberInfo.setMemberCode(String.valueOf(i));
                memberInfo.setMemberName("第"+i+"位用户");
                memberInfo.setMemberSex(1);
                list.add(memberInfo);
            }
            memberInfoMongoService.batchInsert(list);


           /* MemberInfo memberInfo = new MemberInfo();
            memberInfo.setId(1);
            memberInfo.setLastData(new Date());
            memberInfo.setMemberCode("1002");
            memberInfo.setMemberName("王五");
            memberInfo.setMemberSex(1);
            memberInfoMongoService.insert(memberInfo);*/

        }catch (Exception e){
            e.printStackTrace();
            baseResponse.setErrorCode("500");
            baseResponse.setErrorMsg("插入会员信息失败.");
        }
        System.out.println(JSON.toJSON(baseResponse));
        return baseResponse;
    }

    @RequestMapping(value = "/member",method = RequestMethod.PUT)
    public BaseResponse updateMember(){
        BaseResponse baseResponse = new BaseResponse();
        try {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setId(1);
            memberInfo.setLastData(new Date());
            memberInfo.setMemberCode("1001");
            memberInfo.setMemberName("张三01");
            memberInfo.setMemberSex(1);
            memberInfoMongoService.update(memberInfo);

        }catch (Exception e){
            e.printStackTrace();
            baseResponse.setErrorCode("500");
            baseResponse.setErrorMsg("修改会员信息失败.");
        }
        System.out.println(JSON.toJSON(baseResponse));
        return baseResponse;
    }

    @RequestMapping(value = "/member/code",method = RequestMethod.GET)
    public BaseResponse findByCode( String code){
        BaseResponse baseResponse = new BaseResponse();
        try {
           MemberInfo memberInfo = memberInfoMongoService.findByMemberCode("1001");
           baseResponse.setData(memberInfo);
        }catch (Exception e){
            e.printStackTrace();
            baseResponse.setErrorCode("500");
            baseResponse.setErrorMsg("插入会员信息失败.");
        }
        System.out.println(JSON.toJSON(baseResponse));
        return baseResponse;
    }

    @RequestMapping(value = "/member/sex",method = RequestMethod.GET)
    public BaseResponse findBySex(@ModelAttribute String sex){
        BaseResponse baseResponse = new BaseResponse();
        try {
            List<MemberInfo> memberInfo = memberInfoMongoService.findByMemberSex(1);
            baseResponse.setData(memberInfo);
        }catch (Exception e){
            e.printStackTrace();
            baseResponse.setErrorCode("500");
            baseResponse.setErrorMsg("插入会员信息失败.");
        }
        System.out.println(JSON.toJSON(baseResponse));
        return baseResponse;
    }


    @RequestMapping(value = "/member",method = RequestMethod.DELETE)
    public BaseResponse del(@ModelAttribute long id){
        BaseResponse baseResponse = new BaseResponse();
        try {
            long[] ids = new long[1];
            ids[0] = id;
            memberInfoMongoService.deletes(ids);
        }catch (Exception e){
            e.printStackTrace();
            baseResponse.setErrorCode("500");
            baseResponse.setErrorMsg("插入会员信息失败.");
        }
        System.out.println(JSON.toJSON(baseResponse));
        return baseResponse;
    }

    @RequestMapping(value = "/member/list",method = RequestMethod.GET)
    public BaseResponse findList(){
        BaseResponse baseResponse = new BaseResponse();
        try {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setMemberCode("8");
           Pagination<MemberInfo> list =  memberInfoMongoService.findList(memberInfo,null);
           baseResponse.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            baseResponse.setErrorCode("500");
            baseResponse.setErrorMsg("查询会员信息失败.");
        }
        System.out.println(JSON.toJSON(baseResponse));
        return baseResponse;
    }
}
