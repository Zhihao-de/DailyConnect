package cn.ac.iscas.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import net.sf.json.JSONObject;
import org.jetbrains.annotations.Contract;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult implements Serializable {
    private int code;
    private String msg;
    private Map<String, Object> data;

    /**
     *
     */
    @Contract(pure = true)
    public ResponseResult() {
    }

    @Contract(pure = true)
    protected ResponseResult(int code, String msg, Map<String, Object> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @param errCode
     * @param errMsg
     * @return
     */
    public static ResponseResult error(int errCode, String errMsg) {
        return new ResponseResult(errCode, errMsg, null);
    }

    /**
     * @param ex
     * @return
     * runtimeException
     */

    /*
    public static ResponseResult error(RuntimeServiceException ex) {
        return new ResponseResult(ex.getCode(), ex.getMessage(), null);
    }
   */

    /**
     * 创建一个包含成功结果的<code>ResponseResult</code>对象
     *
     * @return 包含成功结果的<code>ResponseResult</code>对象
     */
    public static ResponseResult ok() {
        return new ResponseResult(200, "ok", new HashMap<>());
    }

    /**
     * 为返回数据增加属性
     *
     * @param key   属性名
     * @param value 属性值
     * @return <code>ResponseResult</code>对象
     */
    public ResponseResult put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    /**
     * 将当前对象的JSON表示写入Http响应
     *
     * @param writer Http响应的<code>PrintWriter</code>对象
     * @return 当前<code>ResponseResult</code>对象
     */
    public ResponseResult write(PrintWriter writer) {
        if (null != writer) {
            writer.append(JSONObject.fromObject(this).toString());
        }
        return this;
    }

    /**
     * 返回code
     *
     * @return <code>int</code>
     */
    public int getCode() {
        return code;
    }

    /**
     * 返回msg
     *
     * @return <code>String</code>
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 返回Data
     *
     * @return <code>map</code>
     */
    public Map<String, Object> getData() {
        return data;
    }
}
