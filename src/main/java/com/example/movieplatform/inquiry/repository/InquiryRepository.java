package com.example.movieplatform.inquiry.repository;

import com.example.movieplatform.inquiry.entity.Inquiry;
import com.example.movieplatform.user.dto.UserInquiryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findByStatus(Inquiry.InquiryStatus status);
    @Query("""
        SELECT new com.example.movieplatform.user.dto.UserInquiryDto(
                i.id,
            i.title,
            i.content,
            i.createdAt,
            i.inquiryType,
            i.status,
            ia.createdAt,
            ia.content
        )
        FROM Inquiry i
        LEFT JOIN InquiryAnswer ia ON ia.inquiry = i
        WHERE i.user.id = :userId
        """)
    List<UserInquiryDto> findByUserId(@Param("userId") Long userId);
}
