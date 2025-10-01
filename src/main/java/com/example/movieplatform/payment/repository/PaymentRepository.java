package com.example.movieplatform.payment.repository;

import com.example.movieplatform.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
