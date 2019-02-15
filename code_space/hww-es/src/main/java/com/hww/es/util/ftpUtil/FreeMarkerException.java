package com.hww.es.util.ftpUtil;

/**
 * <p>FreeMarker异常处理</p>
 * 2018年11月28日下午2:07:27
 * @author lvjie
 */
public class FreeMarkerException extends BaseException {
    public FreeMarkerException(){
        super("FreeMarker异常");
    }

    public FreeMarkerException(int errorCode, String errorMsg){
        super(errorMsg);
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
    }
    public FreeMarkerException(String errorMsg){
        super(errorMsg);
        this.errorCode=500;
        this.errorMsg=errorMsg;
    }
    public FreeMarkerException(String errorMsg, Exception e){
        super(errorMsg,e);
        this.errorCode=500;
        this.errorMsg=errorMsg;
    }
}
