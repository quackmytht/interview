package com.tht.utils;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;  

/**
 * @author tht
 */
@Documented
@Constraint(validatedBy = { ListVarValidatorForArrayOfInt.class })  
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })  
@Retention(RUNTIME)  
public @interface ListVar {  
	  
	String message() default "{com.tht.common.validator.customization.ListVar.message}";  
	  
	Class<?>[] groups() default { };  
	  
	Class<? extends Payload>[] payload() default { };  
	  
	String[] values() default { };
  
}