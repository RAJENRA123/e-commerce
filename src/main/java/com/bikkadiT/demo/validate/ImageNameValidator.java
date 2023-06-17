package com.bikkadiT.demo.validate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.relational.SqlStringGenerationContext;
import org.hibernate.mapping.Constraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageNameValidator implements ConstraintValidator<ImageNameValid,String> {
private Logger logger=LoggerFactory.getLogger(ImageNameValidator.class);


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
  //logic
        if(value.isBlank()) {
            return false;
        }else {
            return true;

        }
    }
}
