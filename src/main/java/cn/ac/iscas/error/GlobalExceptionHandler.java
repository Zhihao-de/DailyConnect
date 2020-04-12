package cn.ac.iscas.error;

import cn.ac.iscas.service.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

//import com.auth0.jwt.exceptions.TokenExpiredException;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authz.UnauthorizedException;
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import javax.validation.ValidationException;

/**
 * 全局异常处理器。集中处理未被捕获的异常。
 *
 * @author Naiming
 * @see RuntimeServiceException
 */
@Component
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @param exception 参数缺失异常
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handle(MissingServletRequestParameterException exception) {
        return ResponseResult.error(ErrCodes.MISSING_PARAMETER_ERROR, exception.getMessage());
    }

    /**
     * 参数（非Bean对象）验证的结果通过 ConstraintViolationException 异常抛出，
     * 此方法处理该异常。
     *
     * @param exception 验证异常
     * @return <c>ResponseResult</c> 对象，向客户端传递 JSON 对象
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handle(ValidationException exception) {
        StringBuilder errorInfo = new StringBuilder();
        if (exception instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                errorInfo.append(item.getMessage());
                errorInfo.append(",");
            }
        }
        errorInfo.delete(errorInfo.length() - 1, errorInfo.length());
        return ResponseResult.error(ErrCodes.VALIDATION_ERROR, errorInfo.toString());
    }

    /**
     *
     * @param exception 认证异常
     * @return
     */
    /*
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handle(AuthenticationException exception) {
        ResponseResult result;
        Throwable throwable = exception.getCause();
        if (throwable instanceof TokenExpiredException) {
            result = ResponseResult.error(ErrCodes.ACCESS_CONTROL_ERROR, "expired_token");
        } else {
            result = ResponseResult.error(ErrCodes.ACCESS_CONTROL_ERROR, "illegal_token");
        }
        return result;
    }
    */


    /**
     * @param exception 权限异常
     * @return
     */
    /* 权限管理这里 以后 要老师和家长学生的分开  继续开发

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handle(UnauthorizedException exception) {
        return ResponseResult.error(ErrCodes.ROLE_OR_PERMISSION_ERROR, exception.getMessage());
    }
    */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handle(ServletException exception) {
        return ResponseResult.error(ErrCodes.ACCESS_CONTROL_ERROR, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handle(Exception exception) {
        return ResponseResult.error(ErrCodes.ACCESS_CONTROL_ERROR, exception.getMessage());
    }

    /**
     * 集中处理 RuntimeServiceException 异常。
     *
     * @param exception 运行时业务逻辑异常
     * @return <c>ResponseResult</c> 对象，向客户端传递 JSON 对象
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handle(RuntimeServiceException exception) {
        Throwable throwable = exception.getCause();
        return ResponseResult.error(exception.getCode(), throwable.getMessage());
    }
}
