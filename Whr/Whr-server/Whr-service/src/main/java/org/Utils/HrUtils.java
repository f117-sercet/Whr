package org.Utils;

import org.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;
/*
Hr工具类
 */
public class HrUtils {
    public static Hr getCurrentHr() {
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
