package com.tranduchai.server.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SlugValidator implements ConstraintValidator<Slug, String> {

   @Override
   public boolean isValid(String value, ConstraintValidatorContext context) {
      // if (value == null || value.isBlank()) {
      // return true;
      // }
      boolean isMatched = value.matches("[a-z0-9]+(-[a-z0-9]+)*");
      return isMatched;
   }

}
