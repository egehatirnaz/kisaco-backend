package com.kisaco.application.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
@RepositoryRestResource(path = "user")
public interface UsersRepository extends CrudRepository<Users, Integer> {
    public Users findByEmail(String email);
}