package com.hww.es.util.ftpUtil;

/**
 * <p>异常基础类</p>
 * 2018年11月26日上午9:50:12
 * @author lvjie
 */
public class BaseException extends RuntimeException {
    public int errorCode;
    public String errorMsg;

    public BaseException(){
        super("运行时异常");
    }
    public BaseException(int errorCode, String errorMsg){
        super(errorMsg);
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
    }
    public BaseException(String errorMsg){
        super(errorMsg);
        this.errorCode=500;
        this.errorMsg=errorMsg;
    }
    public BaseException(String errorMsg, Exception e){
        super(errorMsg,e);
        this.errorCode=500;
        this.errorMsg=errorMsg;
    }


}
