package com.solr.solrdemo.service;

import com.solr.solrdemo.domian.MemberInfo;
import com.solr.solrdemo.domian.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberInfoMongoService {

    MemberInfo findByMemberCode(String memberCode);

    List<MemberInfo> findByMemberSex(long memberSex);

    void  insert(MemberInfo memberInfo);

    void  batchInsert(List<MemberInfo> list);

    void  update(MemberInfo memberInfo);

    void  deletes(long[] ids);

    /***
     * 分页
     * @param memberInfo
     * @return
     */
    Pagination<MemberInfo> findList(MemberInfo memberInfo, Pageable pageable);


}
