package com.solr.solrdemo.domian;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
@Document(collection = "member_info")   //@Document（实体类将会映射到数据库中名为member_info的collection中
public class MemberInfo implements Serializable {

    @Id
    private  long id;
    private  String memberName;
    private  String memberCode;
    private  long memberSex;
    private Date lastData;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public long getMemberSex() {
        return memberSex;
    }

    public void setMemberSex(long memberSex) {
        this.memberSex = memberSex;
    }

    public Date getLastData() {
        return lastData;
    }

    public void setLastData(Date lastData) {
        this.lastData = lastData;
    }

    @Override
    public String toString() {
        return "MemberInfo{" +
                "id=" + id +
                ", memberName='" + memberName + '\'' +
                ", memberCode='" + memberCode + '\'' +
                ", memberSex=" + memberSex +
                ", lastData=" + lastData +
                '}';
    }
}
