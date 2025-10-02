package com.example.movieplatform.inquiry.service;

import com.example.movieplatform.common.exception.EntityNotFoundException;
import com.example.movieplatform.inquiry.dto.InquiryRequest;
import com.example.movieplatform.inquiry.entity.Inquiry;
import com.example.movieplatform.inquiry.entity.InquiryAnswer;
import com.example.movieplatform.inquiry.repository.InquiryAnswerRepository;
import com.example.movieplatform.inquiry.repository.InquiryRepository;
import com.example.movieplatform.user.Repository.UserRepository;
import com.example.movieplatform.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {
    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;
    private final InquiryAnswerRepository inquiryAnswerRepository;

    public void saveInquiry(InquiryRequest request, User user) {
        Inquiry inquiry = Inquiry.ofInquiry(request, user);
        inquiryRepository.save(inquiry);
    }

    public List<Inquiry> findByStatus(Inquiry.InquiryStatus status) {
        return inquiryRepository.findByStatus(status);
    }

    public void adminAnswerInquiry(Long userId, Long inquiryId, String content) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(userId.toString()));
        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(() -> new EntityNotFoundException(inquiryId.toString()));

        InquiryAnswer answer = new InquiryAnswer(content, inquiry, user);
        inquiryAnswerRepository.save(answer);
    }
}
