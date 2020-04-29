package com.kisaco.application.urls;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
@RepositoryRestResource(path = "url")
public interface URLsRepository extends CrudRepository<URLs, Integer> {
    public URLs findByShortURL(String short_url);
    public Iterable<URLs> findAllByUserID(Integer user_id);
}