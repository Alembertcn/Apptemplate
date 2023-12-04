package com.king.template.net;

/**
 * 全局的接口响应码
 */
public class HttpCode {

    /**
     * 位置错误
     */
    public static int UNKNOWN_ERROR = -1;
    /**
     * 全局成功响应码
     */
    public final static int SUCCESS = 200;
    public final static int SUCCESS_AEX = 20000;

    //三方账号未绑定官网账号错误码
    public final static int THIRD_NOT_BIND_ACCOUNT = 7010;
    //帐号已被注册
    public final static int ACCOUNT_ALREADY_EXISTS = 7018;
    //帐号未注册
    public final static int ACCOUNT_NOT_EXISTS = 7002;
    //第三方 Token 已过期
    public final static int THIRD_TOKEN_OUT_OF_DATE = 7009;
    //滑块的 ticket 失效
    public static int SLIDE_TICKET_ERROR = 7032;

    //官网 TOKEN 失效
    public final static int TOKEN_ERROR = 10;
    //登陆校验
    public final static int LOGIN_NEED_CHECK = 30;

    public final static int NO_FIND_CHANNEL_ERROR = 3001;
    //用户登录失败
    public final static int LOGIN_ERROR = 11;
    //交易 TOKEN 失效
    public final static int TRADE_TOKEN_ERROR = 12;
    //交易 TOKEN 失效
    public final static int TRADE_TOKEN_ERROR_INFAST = 18;

    //交易密码输入错误
    public final static int TRADE_PASSWORD_ERROR = 13;
    //交易密码输入错误
    public final static int TRADE_PASSWORD_ERROR_COUNT = 16;
    //交易密码锁定
    public final static int TRADE_PASSWORD_LOCK = 17;

    // 黑名单
    public final static int BLACK_USER = 8004;
    // 非TRS白名单
    public final static int NOT_TRS_SUPPORT_STOCK = -2;
    public final static int ACCOUNT_UNEXISTENCE = 9991;//账号不存在
    //改测单的时候需要刷新列表
    public final static int CHANGE_ORDER_TYPE = 58854;


}
