package com.solr.solrdemo.domian;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

public class PushDataIntoSolrRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 核心名称
     */
    @NotNull
    private String coreName ;

    /**
     * 存储实体
     */
    @NotNull
    private Map<String, Object> input;

    public String getCoreName() {
        return coreName;
    }

    public void setCoreName(String coreName) {
        this.coreName = coreName;
    }

    public Map<String, Object> getInput() {
        return input;
    }

    public void setInput(Map<String, Object> input) {
        this.input = input;
    }
}
