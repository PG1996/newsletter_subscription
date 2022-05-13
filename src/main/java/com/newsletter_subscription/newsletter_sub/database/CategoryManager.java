package com.newsletter_subscription.newsletter_sub.database;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.newsletter_subscription.newsletter_sub.entities.Category;
import com.newsletter_subscription.newsletter_sub.exceptions.CategoryNotFoundException;
import com.newsletter_subscription.newsletter_sub.helper.GenerateCategoryId;

public class CategoryManager {
    
    Map<String, Category> categories = new HashMap<>();
    GenerateCategoryId generateCategoryId = new GenerateCategoryId();

    public String createCategory(final String categoryName) {

        String categoryId = generateCategoryId.generateUniqueKey(categoryName);
        
        Category newCategory = new Category(categoryId, categoryName, new ArrayList<>());
        newCategory.setId(categoryId);
        if (!categories.containsKey(categoryId)) {
            categories.put(categoryId, newCategory);
        }
        return categoryId;
    }

    public String getCategory(final String categoryName) {
        String categoryId = generateCategoryId.generateUniqueKey(categoryName);
        if (!categories.containsKey(categoryId)) {
            throw new CategoryNotFoundException();
        }
        return categoryId;
    }

    public void registerNewsletterAgainstCategory(String categoryId, String newsletterId) {
        
        if (!categories.containsKey(categoryId)) {
            throw new CategoryNotFoundException();
        }
        Category category = categories.get(categoryId);
        List<String> newsletterIdList = category.getNewsletterIdList();
        newsletterIdList.add(newsletterId);
    }

    public List<String> getNewsletterAgainstCategory(String categoryName) {
        String categoryId = generateCategoryId.generateUniqueKey(categoryName);
        if (!categories.containsKey(categoryId)) {
            throw new CategoryNotFoundException();
        }
        Category category = categories.get(categoryId);
        return category.getNewsletterIdList();
    }
}
