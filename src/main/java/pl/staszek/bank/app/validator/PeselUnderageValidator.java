package pl.staszek.bank.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class PeselUnderageValidator implements ConstraintValidator<PeselUnderage, String> {

    @Override
    public void initialize(PeselUnderage constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        PeselUtil peselUtil = new PeselUtil(s);

        final LocalDate birthDate = peselUtil.getDateOfBirth();
        final LocalDate now = LocalDate.now();

        return now.minusYears(18).isAfter(birthDate);
    }
}
