package com.global.dax.interview.validation;

import static com.global.dax.interview.validation.OrderValidationRules.validateIsDraft;

import java.util.ArrayList;
import java.util.List;

import com.global.dax.interview.exception.ValidationException;
import com.global.dax.interview.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderValidator {

    public void validateCreate(final Order orderLine) {
        final List<ValidationError> errors = new ArrayList<>();
        validateIsDraft(orderLine).ifPresent(errors::add);


        if (!errors.isEmpty()) {
            throw new ValidationException("Order line creation validation failed", errors);
        }
    }
}
