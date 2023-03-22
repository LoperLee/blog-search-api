package com.blog.core.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidator implements ConstraintValidator<Enum, String> {
    private Set<String> acceptedValues;

    @Override
    public void initialize(Enum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(java.lang.Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return acceptedValues.contains(value.toUpperCase());
    }
}
