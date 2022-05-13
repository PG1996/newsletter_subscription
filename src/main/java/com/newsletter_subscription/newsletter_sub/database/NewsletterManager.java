package com.newsletter_subscription.newsletter_sub.database;

import com.newsletter_subscription.newsletter_sub.entities.Newsletter;
import com.newsletter_subscription.newsletter_sub.exceptions.NewsletterAlreadyExistsException;
import com.newsletter_subscription.newsletter_sub.exceptions.NewsletterNotFoundException;
import com.newsletter_subscription.newsletter_sub.helper.GenerateNewsletterId;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsletterManager {
    
    Map<String, Newsletter> newsletters = new HashMap<>();
    GenerateNewsletterId generateNewsletterId = new GenerateNewsletterId();
    CategoryManager categoryManager = new CategoryManager();

    public String createNewsletter(final Newsletter newsletter) {
        
        String newletterId = generateNewsletterId.generateUniqueKey(newsletter.getTitle());
        if (newsletters.containsKey(newletterId)) {
            throw new NewsletterAlreadyExistsException();
        }
        List<String> categoriesList = newsletter.getCategory();
        List<String> categoryIdList = new ArrayList<>();

        for (String category:categoriesList) {
            String categoryId = "";           
            try {
                categoryId = categoryManager.getCategory(category); 
            } catch (Exception e) {
                categoryId = categoryManager.createCategory(category);                 
            }
            categoryManager.registerNewsletterAgainstCategory(categoryId, newletterId);
            categoryIdList.add(categoryId);            
        }
        
        newsletter.setCategory(categoryIdList);
        newsletters.put(newletterId, newsletter);
        return newletterId;
    }

    public Map<String, List<Newsletter> > getNewsletter(List<String> categoryNameList) {

        Map<String, List<Newsletter> > newsletters_Map = new HashMap<>();
        for(String categoryName:categoryNameList) {
            
            List<String> newsLetterIdList = categoryManager.getNewsletterAgainstCategory(categoryName);
            List<Newsletter> newsletterList = new ArrayList<>();
            
            for(String newsLetterId: newsLetterIdList) {
                newsletterList.add(newsletters.get(newsLetterId));
            }

            newsletters_Map.put(categoryName, newsletterList);
        }
        return newsletters_Map;
    }

    public void subscribeToNewsletter(String newsletterId) {
        
        CreatorManager creatorManager = new CreatorManager();
        
        if (!newsletters.containsKey(newsletterId)) {
            throw new NewsletterNotFoundException();
        }
        Newsletter newsletter = newsletters.get(newsletterId);
        creatorManager.updateCreatorEarnings(newsletter.getCreatorId(), newsletter.getPrice());
    }

}
