package com.example.TacoCloud.data;

import com.example.TacoCloud.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
