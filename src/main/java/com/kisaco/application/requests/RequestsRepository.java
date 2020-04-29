package com.kisaco.application.requests;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
@RepositoryRestResource(path = "request")
public interface RequestsRepository extends CrudRepository<Requests, Integer> {
    public Iterable<Requests> findAllByUrlID(Integer UrlID);
}