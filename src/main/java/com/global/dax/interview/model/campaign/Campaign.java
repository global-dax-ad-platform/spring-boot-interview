package com.global.dax.interview.model.campaign;

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
public class Campaign {

    private Integer id;
    @NotNull private Integer orderLineId;
    @NotNull private String name;
    @NotNull private CampaignStatus status;
    @NotNull private DeliveryType deliveryType;
    @NotNull private Integer businessId;
}
