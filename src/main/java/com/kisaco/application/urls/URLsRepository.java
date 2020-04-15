package com.kisaco.application.urls;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface URLsRepository extends CrudRepository<URLs, Integer> {
    public URLs findByShortURL(String short_url);
}