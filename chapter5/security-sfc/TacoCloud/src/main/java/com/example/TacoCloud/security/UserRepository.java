package com.example.TacoCloud.security;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<TacoUser, Long> {

    TacoUser findByUsername(String username);

}
