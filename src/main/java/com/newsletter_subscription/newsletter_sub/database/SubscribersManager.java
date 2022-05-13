package com.newsletter_subscription.newsletter_sub.database;

import com.newsletter_subscription.newsletter_sub.entities.Subscriber;
import com.newsletter_subscription.newsletter_sub.exceptions.SubscriberAlreadyExistsException;
import com.newsletter_subscription.newsletter_sub.exceptions.SubscriberNotFoundException;
import com.newsletter_subscription.newsletter_sub.exceptions.SubscriberAlreadySubscribedException;
import com.newsletter_subscription.newsletter_sub.helper.GenerateSubscriberId;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class SubscribersManager {

  Map<String, Subscriber> subscribers = new HashMap<>();

  GenerateSubscriberId generateSubscriberId = new GenerateSubscriberId();
  public void createSubscriber(final Subscriber newSubscriber) {

    String subscriberId = generateSubscriberId.generateUniqueKey(newSubscriber.getEmail());

    if (subscribers.containsKey(subscriberId)) {
      throw new SubscriberAlreadyExistsException();
    }

    subscribers.put(subscriberId, newSubscriber);
  }

  public Subscriber getSubscriber(final String subscriberId) {
    if (!subscribers.containsKey(subscriberId)) {
      throw new SubscriberNotFoundException();
    }
    return subscribers.get(subscriberId);
  }

  public void addNewSubscription(String subscriberId, String subscriptionId) {
    if (!subscribers.containsKey(subscriberId)) {
      throw new SubscriberNotFoundException();
    }
    Subscriber subscriber = subscribers.get(subscriberId);
    List<String> subscriptionIdList = subscriber.getSubscriptionIdList();
    if (subscriptionIdList.contains(subscriptionId)) {
      throw new SubscriberAlreadySubscribedException();
    }
    subscriptionIdList.add(subscriptionId);
    subscriber.setSubscriptionIdList(subscriptionIdList);
    subscribers.put(subscriberId, subscriber);
  }
}
