package com.global.dax.interview.repository;

import com.global.dax.interview.model.campaign.Campaign;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CampaignRepository {

    private final Map<Integer, Campaign> storage = new ConcurrentHashMap<>();
    private Integer nextId = 1;

    public Campaign save(Campaign campaign) {
        if (campaign.getId() == null) {
            campaign.setId(nextId++);
        }
        storage.put(campaign.getId(), campaign);
        return campaign;
    }

    public Optional<Campaign> findById(Integer id) {
        return Optional.ofNullable(storage.get(id));
    }

    public void truncate() {
        storage.clear();
        nextId = 1;
    }
}
