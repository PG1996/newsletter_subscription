package com.newsletter_subscription.newsletter_sub.entities;

import java.util.List;
import java.util.ArrayList;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class Creator extends Subscriber {
    
    private double earnings;
    
    public Creator(String id, String name, String email, List<String> subscriptionIdList) {
        super(id, name, email, new ArrayList<>());
    }
}
