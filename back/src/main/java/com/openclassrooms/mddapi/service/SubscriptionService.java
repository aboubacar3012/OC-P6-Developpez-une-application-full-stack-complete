package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Service class for managing subscriptions.
 */
@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Retrieves all subscriptions.
     *
     * @return the list of all subscriptions
     */
    public List<Subscription> subscriptions() {
        return this.subscriptionRepository.findAll();
    }

    /**
     * Subscribes a user to a subject.
     *
     * @param userId    the ID of the user
     * @param subjectId the ID of the subject
     */
    public void subscribe(Long userId, Long subjectId) {
        Subscription subscription = new Subscription();
        subscription.setUserId(userId);
        subscription.setSubjectId(subjectId);
        this.subscriptionRepository.save(subscription);
    }

    /**
     * Unsubscribes a user from a subject.
     *
     * @param userId    the ID of the user
     * @param subjectId the ID of the subject
     */
    public void unSubscribe(Long userId, Long subjectId) {
        Subscription subscription = this.subscriptionRepository.findByUserIdAndSubjectId(userId, subjectId);
        this.subscriptionRepository.deleteById(subscription.getId());
    }

    /**
     * Checks if a user is already subscribed to a subject.
     *
     * @param userId    the ID of the user
     * @param subjectId the ID of the subject
     * @return true if the user is already subscribed, false otherwise
     */
    public Boolean alreadySubscribed(Long userId, Long subjectId) {
        Optional<Subscription> subscription = Optional.ofNullable(this.subscriptionRepository.findByUserIdAndSubjectId(userId, subjectId));
        return subscription.isPresent();
    }
}
