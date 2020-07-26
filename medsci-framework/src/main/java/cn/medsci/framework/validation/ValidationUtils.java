package cn.medsci.framework.validation;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.HibernateValidator;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.*;

/**
 * 校验的工具类，传入任意类型实体类，快速返回校验的第一个错误
 *
 * @author arvin
 */
public class ValidationUtils {

    //private static Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();
    /**
     * 修改配置
     */
    private static Validator validator = Validation.byProvider(HibernateValidator.class)
            .configure().failFast(true).buildValidatorFactory().getValidator();

    /**
     * 校验实体类
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> ValidationResult validateEntity(T obj) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        return result;
    }

    /**
     * 仅校验具体某个字段
     *
     * @param obj
     * @param propertyName
     * @param <T>
     * @return
     */
    public static <T> ValidationResult validateProperty(T obj, String propertyName) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<String, String>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(propertyName, cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        return result;
    }

    /**
     * 校验实体类，返回错误消息
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String validateEntityWithMsg(T obj) {
        String result = null;
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            final String strSplit = ";";
            StringBuffer sBuffer = new StringBuffer();
            for (ConstraintViolation<T> cv : set) {
                sBuffer.append(cv.getMessage());
                sBuffer.append(strSplit);
            }
            result = sBuffer.toString();
        }
        return result;
    }

    /**
     * 将字段验证错误转换为标准的map型，key:value = field:message
     *
     * @param constraintViolations constraint violations(contain error information)
     * @return error detail map
     */
    @NonNull
    public static Map<String, String> mapWithValidError(Set<ConstraintViolation<?>> constraintViolations) {
        if (CollectionUtils.isEmpty(constraintViolations)) {
            return Collections.emptyMap();
        }

        Map<String, String> errMap = new HashMap<>(4);
        // Format the error message
        constraintViolations.forEach(
                constraintViolation ->
                        errMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()));
        return errMap;
    }

    /**
     * 将字段验证错误转换为标准的map型，key:value = field:message
     *
     * @param fieldErrors 字段错误组
     * @return 如果返回null，则表示未出现错误
     */
    public static Map<String, String> mapWithFieldError(@Nullable List<FieldError> fieldErrors) {
        if (CollectionUtils.isEmpty(fieldErrors)) {
            return Collections.emptyMap();
        }
        Map<String, String> errMap = new HashMap<>(4);
        fieldErrors.forEach(filedError -> errMap.put(filedError.getField(), filedError.getDefaultMessage()));
        return errMap;
    }
}

