package com.global.dax.interview.service;

import com.global.dax.interview.model.campaign.Campaign;
import com.global.dax.interview.repository.CampaignRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Slf4j
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public Campaign getCampaignById(Integer id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Campaign with id " + id + " not found"));
    }
}
