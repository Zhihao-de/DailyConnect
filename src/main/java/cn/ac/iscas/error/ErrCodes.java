package cn.ac.iscas.error;

/**
 * 错误码
 */
public abstract class ErrCodes {

    /* 系统：-40xx */
    public static final int SERVICE_UNEXPECTED_ERROR = -4001;
    public static final int EMPTY_RESULT_SET = -4002;
    public static final int MODIFICATION_ERROR = -4003;

    /* 系统-访问控制拦截： -401x */
    /* AC：未知错误 */
    public static final int ACCESS_CONTROL_ERROR = -4010;
    /* AC：用户未签入错误 */
    public static final int UNAUTHORIZED_ERROR = -4011;
    /* AC：用户名或密码不存在或不匹配 */
    public static final int ABSENCE_OR_MISMATCH_ERROR = -4012;
    /* AC：用户角色或权限错误 */
    public static final int ROLE_OR_PERMISSION_ERROR = -4013;


    /* 参数验证拦截：-402x */
    /* 参数验证-验证错误 */
    public static final int VALIDATION_ERROR = -4021;
    /* 参数验证-参数缺失错误 */
    public static final int MISSING_PARAMETER_ERROR = -4022;

    /* 授权模块：-41xx */
    /* 授权-签入：-411x */
    /* 签入：未知错误 */
    public static final int SIGN_IN_ERROR = -4110;
    /* 签入：当前客户端实例已签入 */
    public static final int REPETITIVE_SIGN_IN_ERROR = -4112;
    /* 签入：用户被禁止签入 */
    public static final int BANNED_USER_ERROR = -4113;
    /* 授权-签注：-412x */
    /* 签注：未知错误 */
    public static final int SIGN_UP_ERROR = -4120;
    /* 授权-签出：-413x */
    /* 签出：未知错误 */
    public static final int SIGN_OUT_ERROR = -4130;


}
