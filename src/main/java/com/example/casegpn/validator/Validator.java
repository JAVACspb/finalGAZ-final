package com.example.casegpn.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator implements ConstraintValidator<RequestValid,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern =
                Pattern.compile("(.*[.]\\D{1}.{0,2}|^_.*_|^[0-9]{3,})$");
        Matcher matcher = pattern.matcher(value);
        return !matcher.matches();
    }
}
