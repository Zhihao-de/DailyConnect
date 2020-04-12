package cn.ac.iscas.validation;

public final class RegexPatterns {
    /**
     * 验证手机号的正则表达式，允许11位数字组合，对一些特殊开头进行校验
     */
    public final static String PhoneNumberPattern = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0-9]))\\d{8}$";

    /**
     * 验证登录密码的正则表达式，允许6-16位数字和字母的组合
     */
    public final static String PasswordPattern = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";

    /**
     * 验证微信id的正则表达式
     */
    public final static String WxidPattern = "^[a-zA-Z\\d_]{5,}$";

    /**
     * 验证UUID的正则表达式
     */
    public final static String UUIDPattern = "[0-9a-f]{8}([0-9a-f]{4}){3}[0-9a-f]{12}";

    /**
     * 验证整形数字的正则表达式
     */
    public final static String IntPattern = "^[1-9]\\d*$";

    public final static String UserRolePattern = "elementary|junior|senior";

}
