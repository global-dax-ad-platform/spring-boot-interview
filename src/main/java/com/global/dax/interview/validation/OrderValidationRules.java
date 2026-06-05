package com.global.dax.interview.validation;

import com.global.dax.interview.model.Order;
import com.global.dax.interview.model.OrderStatus;

import java.util.Optional;

/**
 * Reusable validation rules for Order entities.
 * Each rule method returns Optional.empty() if valid, or Optional<ValidationError> if invalid.
 */
public final class OrderValidationRules {

    private static final String ORDER_LINE_FIELD = "orderLine";

    public static Optional<ValidationError> validateIsDraft(final Order orderLine) {
        if (orderLine.getStatus() != OrderStatus.DRAFT) {
            return Optional.of(new ValidationError(
                    ORDER_LINE_FIELD,
                    "Order line must be in DRAFT status"
            ));
        }
        return Optional.empty();
    }
}
