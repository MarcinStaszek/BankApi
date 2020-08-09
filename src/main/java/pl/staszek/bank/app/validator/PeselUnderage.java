package pl.staszek.bank.app.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PeselUnderageValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME )
public @interface PeselUnderage {
    String message() default "{peselUnderage.error.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}