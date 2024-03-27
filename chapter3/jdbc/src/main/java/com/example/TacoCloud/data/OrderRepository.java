package com.example.TacoCloud.data;

import com.example.TacoCloud.model.TacoOrder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
