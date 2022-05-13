package com.newsletter_subscription.newsletter_sub.helper;

import java.util.UUID;

public class GenerateSubscriberId implements GenerateKey {

    @Override
    public String generateUniqueKey(String value) {
        return UUID.nameUUIDFromBytes( String.valueOf(value).getBytes() ).toString();
    }
}