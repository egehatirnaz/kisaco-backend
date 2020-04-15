package com.kisaco.application.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
    public Users findByEmail(String email);
}