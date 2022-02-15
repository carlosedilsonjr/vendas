package carlosedilsonjr.vendas.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ListValidator.class)
public @interface NotEmpityList {
    
    String message() default "A lista nao pode ser vazia";
    
    Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
