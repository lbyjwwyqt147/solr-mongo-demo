package com.solr.solrdemo.domian;

import java.io.Serializable;

public class BaseResponse implements Serializable {
    private static final long serialVersionUID = -1864470087731542406L;

    private String errorCode = "0";

    private String errorMsg ="ok";

    private String subCode = "0";

    private String subMsg = "ok";

    private  Object data;

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg
     *            the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * @return the subCode
     */
    public String getSubCode() {
        return subCode;
    }

    /**
     * @param subCode
     *            the subCode to set
     */
    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    /**
     * @return the subMsg
     */
    public String getSubMsg() {
        return subMsg;
    }

    /**
     * @param subMsg
     *            the subMsg to set
     */
    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public boolean isSuccess() {
        return this.errorCode == null && this.subCode == null && this.errorMsg == null && this.subMsg == null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
