package com.newsletter_subscription.newsletter_sub.entities;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Setter
@Getter
@AllArgsConstructor
public class Subscriptions {
    
    String subscriptionId;
    String newsLetterId;
    String subscriberId;
}
