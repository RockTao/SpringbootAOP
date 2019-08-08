package com.example.annoation;

import java.lang.annotation.*;

/**
 * Created by Tianhao on 2019-08-08.
 */

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnnotation {
	  String value();   // 允许注解有参数

}
