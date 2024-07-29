package com.uniview.project0719.annotation;

import java.lang.annotation.*;

/**
 * @author ：zx
 * @date ：Created in 2024/7/29 19:12
 * @description：
 * @modified By：
 * @version: $
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PreAuthorize {
    String[] value();
}
