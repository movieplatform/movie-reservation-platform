package com.example.movieplatform.inquiry.repository;

import com.example.movieplatform.inquiry.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
