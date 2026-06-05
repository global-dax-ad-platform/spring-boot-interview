package com.global.dax.interview.web;

import com.global.dax.interview.model.campaign.Campaign;
import com.global.dax.interview.service.CampaignService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campaign")
@AllArgsConstructor
@Slf4j
public class CampaignController {

    private final CampaignService campaignService;

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Integer id) {
        log.info("GET /campaign/{} called", id);
        final var campaign = campaignService.getCampaignById(id);
        return ResponseEntity.ok(campaign);
    }


}
