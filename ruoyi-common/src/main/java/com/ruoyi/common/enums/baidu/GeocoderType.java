package com.ruoyi.common.enums.baidu;

/**
 * 地理编码 状态码
 * @author Chas
 * @date 2023-1
 *
 */
public enum GeocoderType {

    OK(0,"正常"),
    innerError(1,"服务器内部错误"),
    parameterInvalid(2,"请求参数非法"),
    verifyFailure(3,"权限校验失败"),
    quotaFailure(4,"配额校验失败"),
    AKFailure(5,"ak不存在或者非法"),
    serviceBan(101,"服务器禁用"),
    whiteError(102,"不通过白名单或者安全码不对"),
    NoPermission(200,"无权限"),
    giveError(300,"配额错误");


    private final int code;
    private final String info;

    GeocoderType(int code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public int getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }

}
