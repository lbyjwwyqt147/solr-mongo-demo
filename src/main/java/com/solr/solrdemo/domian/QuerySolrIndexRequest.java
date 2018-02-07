package com.solr.solrdemo.domian;

import java.io.Serializable;

public class QuerySolrIndexRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 核心名称
     */
    private String coreName ;

    /**
     * 查询条件
     */
    private String query;

    public String getCoreName() {
        return coreName;
    }

    public void setCoreName(String coreName) {
        this.coreName = coreName;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

}
