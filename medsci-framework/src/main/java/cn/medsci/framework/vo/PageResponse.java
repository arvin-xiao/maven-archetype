package cn.medsci.framework.vo;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

/**
 * @author arvin
 */
public class PageResponse<T> extends BaseResponse<T> {

    private Long totalSize;

    public PageResponse(Integer status, String message, T data, Long totalSize) {
        super(status, message, data);
        this.totalSize = totalSize;
    }

    public PageResponse() {
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public PageResponse<T> setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
        return this;
    }

    @Override
    public PageResponse<T> setData(T data) {
        super.setData(data);
        return this;
    }

    @Override
    public PageResponse<T> setStatus(Integer status) {
        super.setStatus(status);
        return this;
    }

    @Override
    public PageResponse<T> setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    public static <T> PageResponse<T> ok(@NonNull T data, Long totalSize) {
        return new PageResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data, totalSize);
    }
}
