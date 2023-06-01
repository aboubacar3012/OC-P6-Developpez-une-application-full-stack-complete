package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Subscription> subscriptions(){
        return this.subscriptionRepository.findAll();
    }

    public void subscribe(Long userId, Long subjectId){
        Subscription subscription = new Subscription();
        subscription.setUserId(userId);
        subscription.setSubjectId(subjectId);
        this.subscriptionRepository.save(subscription);
    }

    public void unSubscribe(Long userId, Long subjectId){
        Subscription subscription = this.subscriptionRepository.findByUserIdAndSubjectId(userId, subjectId);
        this.subscriptionRepository.deleteById(subscription.getId());
    }

    public Boolean alreadySubscribed(Long userId, Long subjectId){
        Optional<Subscription> subscription = Optional.ofNullable(this.subscriptionRepository.findByUserIdAndSubjectId(userId, subjectId));
        return subscription.isPresent();
    }



}
