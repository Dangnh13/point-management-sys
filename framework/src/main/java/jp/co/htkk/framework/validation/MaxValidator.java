package jp.co.htkk.framework.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.co.htkk.framework.validation.annotation.Max;
import org.apache.commons.lang3.StringUtils;

public class MaxValidator implements ConstraintValidator<Max, String> {
    private long max;

    public void initialize(Max parameters) {
        max = parameters.value();
        validateParameters();
    }

    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(value)) {
            return true;
        }

        String regex = "^(\\d+)$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value.trim());

        if (m.find()) {
            try {
                return Long.parseLong(value.trim()) <= max;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    private void validateParameters() {
    }

}
