package com.newsletter_subscription.newsletter_sub.entities;

import java.util.List;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Setter
@Getter
@AllArgsConstructor
public class Newsletter {
    String id;
    List<String> category;
    String title;
    String creatorId;
    double price;
}
