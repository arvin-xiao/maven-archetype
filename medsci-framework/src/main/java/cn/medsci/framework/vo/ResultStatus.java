package cn.medsci.framework.vo;

/**
 * 通用状态码
 *
 * @author arvin
 */
public enum ResultStatus {

    /**
     * 未知错误
     */
    UNKNOWN_ERROR(-1, "未知错误"),
    /**
     * 失败
     */
    ERROR(100, "失败"),
    /**
     * 成功
     */
    SUCCESS(200, "成功"),

    /**
     * 请求数据为空
     */
    NO_CONTENT(204, "请求数据为空"),

    /**
     * 请求数据无效
     */
    BAD_REQUEST(400, "请求数据无效");


    private Integer code;

    private String msg;

    ResultStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

