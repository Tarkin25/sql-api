package ch.medusa.sqlapi.config.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DriverValidator.class)
public @interface Driver {

    String message() default "Driver not found";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
