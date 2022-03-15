package kr.co.m2m.example.demo.common.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.ToString;

@ToString
public class CommonResponseModel<T> implements Serializable {
    private boolean isSuccess;
    private String message;

    private T data;

    private Map<String, Object> extraData;
    private String extraString;
    private String returnCode;

    public CommonResponseModel() {
        this.isSuccess = true;
        this.message = null;
        this.data = null;
        this.extraData = new HashMap<>();
    }

    public CommonResponseModel(T data) {
        this.data = data;
        this.isSuccess = true;
        this.message = null;
        this.extraData = new HashMap<>();
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T result) {
        this.data = result;
    }

    public Map<String, Object> getExtraData() {
        return extraData;
    }

    public void put(String key, Object value) {
        extraData.put(key, value);
    }

    public Object get(String key) {
        return extraData.get(key);
    }

    public String getExtraString() {
        return extraString;
    }

    public void setExtraString(String extraString) {
        this.extraString = extraString;
    }
}