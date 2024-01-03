package ad.clinic.validator;

import java.time.LocalTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimeValidator implements ConstraintValidator<TimeConstraint, LocalTime> {

    @Override
    public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
        int hour = value.getHour();
        if (9 < hour && hour < 17) {
            return true;
        }

        return false;
    }

}
