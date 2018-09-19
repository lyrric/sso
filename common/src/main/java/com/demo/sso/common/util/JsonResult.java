package com.demo.sso.common.util;

import lombok.Data;


@Data
public class JsonResult {


    /**
     * 请求结果
     */
    public Boolean success;

    /**
     * 处理完成后的消息
     */
    public String errMsg = "";
    /**
     * 处理结果代码
     */
    private int statusCode;
    /**
     * 返回数据
     */
    private Object data;

    /**
     * 默认情况
     */
    public JsonResult() {
    }

    /**
     * 成功返回
     *
     * @param data
     */
    private JsonResult(Object data) {
        this.success = true;
        this.data = data;
    }

    /**
     * 自定义返回
     *
     * @param statusCode
     * @param errMsg
     */
    public JsonResult(Boolean success, int statusCode, String errMsg) {
        this.success = success;
        this.statusCode = statusCode;
        this.errMsg = errMsg;
    }

    /**
     * 自定义返回
     */
    public JsonResult(Boolean success, int statusCode, String errMsg, Object data) {
        this.success = success;
        this.statusCode = statusCode;
        this.errMsg = errMsg;
        this.data = data;
    }


    public static JsonResult ok() {
        return new JsonResult();
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(data);
    }

    public static JsonResult fail(int status, String errMsg) {
        return new JsonResult(false, status, errMsg);
    }


}