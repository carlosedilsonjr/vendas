package carlosedilsonjr.vendas.validation;


import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ListValidator implements ConstraintValidator<NotEmpityList, List> {

    @Override
    public boolean isValid(List value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty();
    }

    @Override
    public void initialize(NotEmpityList constraintAnnotation) {
        
    }

    

}




