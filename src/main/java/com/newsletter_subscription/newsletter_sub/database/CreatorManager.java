package com.newsletter_subscription.newsletter_sub.database;

import java.util.Map;
import java.util.HashMap;

import com.newsletter_subscription.newsletter_sub.entities.Creator;
import com.newsletter_subscription.newsletter_sub.entities.Subscriber;
import com.newsletter_subscription.newsletter_sub.exceptions.CreatorNotFoundException;

public class CreatorManager extends SubscribersManager {
    
    Map<String, Creator> creators = new HashMap<>();

    public void updateCreatorEarnings(String creatorId, double newEarning) {

        if (creators.containsKey(creatorId)) {
            Creator currentCreator = creators.get(creatorId);
            currentCreator.setEarnings(currentCreator.getEarnings() + newEarning);
        } 
        else {
            Subscriber subscriber = super.getSubscriber(creatorId);
            Creator creator = new Creator(subscriber.getId(), subscriber.getName(), 
                                        subscriber.getEmail(), subscriber.getSubscriptionIdList());
            creator.setEarnings(newEarning);

            creators.put(creatorId, creator);
        }
    }

    public double getCreatorEarnings(String creatorId) {

        if (!creators.containsKey(creatorId)) {
            throw new CreatorNotFoundException();
        }
        return creators.get(creatorId).getEarnings();
    }

}
