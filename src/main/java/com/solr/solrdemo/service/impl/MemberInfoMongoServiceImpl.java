package com.solr.solrdemo.service.impl;

import com.solr.solrdemo.dao.MemberInfoMongoRepository;
import com.solr.solrdemo.domian.MemberInfo;
import com.solr.solrdemo.domian.Pagination;
import com.solr.solrdemo.service.MemberInfoMongoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberInfoMongoServiceImpl implements MemberInfoMongoService {

    @Autowired
    private MemberInfoMongoRepository memberInfoMongoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public MemberInfo findByMemberCode(String memberCode) {
        return memberInfoMongoRepository.findByMemberCode(memberCode);
    }

    @Override
    public List<MemberInfo> findByMemberSex(long memberSex) {
        return memberInfoMongoRepository.findByMemberSex(memberSex);
    }

    @Override
    public void insert(MemberInfo memberInfo) {
     memberInfoMongoRepository.save(memberInfo);
    }

    @Override
    public void batchInsert(List<MemberInfo> list) {
        memberInfoMongoRepository.save(list);
    }

    @Override
    public void update(MemberInfo memberInfo) {
      memberInfoMongoRepository.save(memberInfo);
    }

    @Override
    public void deletes(long[] ids) {
      memberInfoMongoRepository.delete(ids[0]);
    }

    @Override
    public Pagination<MemberInfo> findList(MemberInfo memberInfo, Pageable pageable) {
        Query query = new Query();
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"lastData")));
        int pageNumber = 10;
        int pageSize = 20;
        int skip = (pageNumber-1)*pageSize;
        if(memberInfo.getMemberCode() != null){
            //模糊查询
           Criteria criteria = Criteria.where("memberCode").regex(memberInfo.getMemberCode());
           query.addCriteria(criteria);
        }
        //获取总条数
        long totalCount = this.mongoTemplate.count(query, MemberInfo.class);
        //总页数
        int totalPage = (int) (totalCount/20);
        query.skip(skip);
        query.limit(pageSize);
        Pagination<MemberInfo> page = new Pagination<>(pageNumber, totalPage, (int)totalCount,pageSize);
        List<MemberInfo> datas =  mongoTemplate.find(query,MemberInfo.class);
        page.build(datas);//获取数据
        return page;
    }

    /**
     * 统计数据
     * @param sex
     * @return
     */
    private long countSample(Long sex){

        Query query = new Query();
        if(sex != null){
            query.addCriteria(Criteria.where("memberSex").is(sex));
        }
        return mongoTemplate.count(query,MemberInfo.class);
    }



}
