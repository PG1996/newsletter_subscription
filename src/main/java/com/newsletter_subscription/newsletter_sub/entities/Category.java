package com.newsletter_subscription.newsletter_sub.entities;

import java.util.List;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Setter
@Getter
@AllArgsConstructor
public class Category {
    
    String id;
    String name;
    List<String> newsletterIdList;
}
