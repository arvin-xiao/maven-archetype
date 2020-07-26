package cn.medsci.framework.vo;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * @author arvin
 */
@Data
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {

    private Integer status = 200;

    private String message = "ok";

    private T data;

    public BaseResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
        data = null;
    }

    @NonNull
    public static <T> BaseResponse<T> ok(@Nullable String message, @Nullable T data) {
        return new BaseResponse<>(HttpStatus.OK.value(), message, data);
    }

    @NonNull
    public static <T> BaseResponse<T> ok(@Nullable String message) {
        return ok(message, null);
    }

    public static <T> BaseResponse<T> ok(@NonNull T data) {
        return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    public static <T> BaseResponse<T> error(HttpStatus httpStatus, T data) {
        return new BaseResponse<>(httpStatus.value(), httpStatus.getReasonPhrase(), data);
    }
}
