package com.solr.solrdemo.dao;

import com.solr.solrdemo.domian.MemberInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/***
 *
 * 操作 mongoDb
 */
public interface MemberInfoMongoRepository extends PagingAndSortingRepository<MemberInfo,Long> {

    MemberInfo findByMemberCode(String memberCode);

    List<MemberInfo> findByMemberSex(long memberSex);

}
