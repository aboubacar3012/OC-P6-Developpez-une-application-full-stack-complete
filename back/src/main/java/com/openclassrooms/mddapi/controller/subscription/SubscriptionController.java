package com.openclassrooms.mddapi.controller.subscription;

import com.openclassrooms.mddapi.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/alreadySubscribed")
    public Boolean alreadySubscribed(@RequestParam Long userId, @RequestParam Long subjectId){
        return this.subscriptionService.alreadySubscribed(userId, subjectId);
    }

    @GetMapping()
    public ResponseEntity<?> subscriptions(){
        return ResponseEntity.ok().body(this.subscriptionService.subscriptions());
    }

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestParam Long userId, @RequestParam Long subjectId){
        HashMap<String, String> response = new HashMap<>();
        response.put("success", "subscribed successfully");
        this.subscriptionService.subscribe(userId,subjectId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<?> unsubscribe(@RequestParam Long userId, @RequestParam Long subjectId){
        HashMap<String, String> response = new HashMap<>();
        response.put("success", "unsubscribed successfully");
        this.subscriptionService.unSubscribe(userId,subjectId);
        return ResponseEntity.ok().body(response);
    }
}
