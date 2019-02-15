package com.hww.es.util.ftpUtil;

/**
 * <p>定义FTP异常</p>
 * 2018年11月26日上午9:50:35
 * @author lvjie
 */
public class PDFException extends BaseException {

    public PDFException(){
        super("PDF异常");
    }

    public PDFException(int errorCode, String errorMsg){
        super(errorMsg);
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
    }
    public PDFException(String errorMsg){
        super(errorMsg);
        this.errorCode=500;
        this.errorMsg=errorMsg;
    }
    public PDFException(String errorMsg, Exception e){
        super(errorMsg,e);
        this.errorCode=500;
        this.errorMsg=errorMsg;
    }


}
