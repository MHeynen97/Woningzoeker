package com.woningzoeker.woningzoeker.Validaties;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = BodHogerDanPrijsValidate.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface BodHogerDanPrijs {
    String message() default "Bod moet hoger zijn dan prijs.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
