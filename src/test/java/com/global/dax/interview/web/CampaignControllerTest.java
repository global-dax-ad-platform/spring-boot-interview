package com.global.dax.interview.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.global.dax.interview.model.DeliveryType;
import com.global.dax.interview.model.campaign.Campaign;
import com.global.dax.interview.model.campaign.CampaignStatus;
import com.global.dax.interview.repository.CampaignRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CampaignControllerTest {

    private MockMvc mockMvc;
    @Autowired private CampaignRepository campaignRepository;
    @Autowired private WebApplicationContext webApplicationContext;


    @BeforeEach
    void beforeEach() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        campaignRepository.truncate();
        campaignRepository.save(Campaign.builder()
                                        .id(1)
                                        .name("My Campaign")
                                        .orderLineId(1)
                                        .status(CampaignStatus.DRAFT)
                                        .deliveryType(DeliveryType.DIRECT)
                                        .businessId(1)
                                        .build());
    }

    @Test
    void getCampaignById_returnsCampaign() throws Exception {
        mockMvc.perform(get("/campaign/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("My Campaign"))
                .andExpect(jsonPath("$.status").value("DRAFT"));
    }

    @Test
    void getCampaignById_returnsNotFound_whenCampaignDoesNotExist() throws Exception {
        mockMvc.perform(get("/campaign/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Campaign with id 99 not found"));
    }
}
