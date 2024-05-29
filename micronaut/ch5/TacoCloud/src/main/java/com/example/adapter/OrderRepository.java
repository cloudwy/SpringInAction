package com.example.adapter;

import com.example.model.TacoOrder;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {
}
