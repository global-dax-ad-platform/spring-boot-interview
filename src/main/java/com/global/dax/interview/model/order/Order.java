package com.global.dax.interview.model.order;

import com.global.dax.interview.model.DeliveryType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Integer id;
    @NotNull private String name;
    @NotNull private OrderStatus status;
    @NotNull private DeliveryType deliveryType;
    @NotNull private Integer businessId;
}
