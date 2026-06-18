package com.global.dax.interview.validation;

import com.global.dax.interview.model.order.Order;
import com.global.dax.interview.model.order.OrderStatus;

import java.util.Optional;

/**
 * Reusable validation rules for Order entities.
 * Each rule method returns Optional.empty() if valid, or Optional<ValidationError> if invalid.
 */
public final class OrderValidationRules {

    private static final String ORDER_LINE_FIELD = "order";

    public static Optional<ValidationError> validateIsDraft(final Order order) {
        if (order.getStatus() != OrderStatus.DRAFT) {
            return Optional.of(new ValidationError(
                    ORDER_LINE_FIELD,
                    "Order must be in DRAFT status"
            ));
        }
        return Optional.empty();
    }
}
