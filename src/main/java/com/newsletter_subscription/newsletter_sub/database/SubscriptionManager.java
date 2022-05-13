package com.newsletter_subscription.newsletter_sub.database;

import com.newsletter_subscription.newsletter_sub.entities.Subscriptions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SubscriptionManager {
    
    Map<String, Subscriptions > subscriptions = new HashMap<>();

    public String subscribe(String newsletterId, String subscriberId) {

        String subscriptionId = UUID.randomUUID().toString();
        Subscriptions newSubscription = new Subscriptions(subscriptionId, newsletterId, subscriberId);

        new NewsletterManager().subscribeToNewsletter(newsletterId);
        new SubscribersManager().addNewSubscription(subscriberId, subscriptionId);

        subscriptions.put(subscriptionId ,newSubscription);
        return subscriptionId;
    }
}
