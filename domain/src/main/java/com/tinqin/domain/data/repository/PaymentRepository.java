package com.tinqin.domain.data.repository;

import com.tinqin.domain.data.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

    Optional<Payment> getPaymentById(String uuid);

}
