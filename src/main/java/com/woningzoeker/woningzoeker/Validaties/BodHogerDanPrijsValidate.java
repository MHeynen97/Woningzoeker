package com.woningzoeker.woningzoeker.Validaties;

import com.woningzoeker.woningzoeker.models.Bieding;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BodHogerDanPrijsValidate implements ConstraintValidator <BodHogerDanPrijs, Bieding> {
    @Override
    public boolean isValid(Bieding bieding, ConstraintValidatorContext context) {
        if (bieding == null) {
            return true;
        }

        if (bieding.getBod() <= 0 || bieding.getPrijs() <= 0) {
            return true; // wordt door andere validaties afgehandeld
        }

        return bieding.getBod() > bieding.getPrijs();
    }
}
