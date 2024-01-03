package ad.clinic.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = TimeValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface TimeConstraint {
    String message() default "The clinic is closed during these hours";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}