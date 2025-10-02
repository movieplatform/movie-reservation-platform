package com.example.movieplatform.point.service;

import com.example.movieplatform.point.dto.UserPointResponse;
import com.example.movieplatform.point.dto.UserPointSummary;
import com.example.movieplatform.point.entity.Point;
import com.example.movieplatform.point.repository.PointRepository;
import com.example.movieplatform.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;

    public void addRegisterPoint(User user){
        Point point = Point.earnForRegister(user);
        pointRepository.save(point);
    }

    public void addPurchasePoint(User user, Long pointAmount){
        Point point = Point.earnForPurchase(user, pointAmount);
        pointRepository.save(point);
    }

    public void addReviewPoint(User user, Long pointAmount){
        Point point = Point.earnForReview(user, pointAmount);
        pointRepository.save(point);
    }

    public void addPaymentUsePoint(User user, Long pointAmount){
        Point point = Point.useForPayment(user, pointAmount);
        pointRepository.save(point);
    }

    public UserPointSummary getUserPointSummary(Long userId) {
        List<Point> pointHistory = pointRepository.findByUserIdOrderByTransactionDateDesc(userId);

        List<UserPointResponse> points = pointHistory.
                stream()
                .map(p -> new UserPointResponse(
                        p.getTransactionDate(),    // 적립 또는 사용한 날짜
                        p.getTransactionType().getDescription(),     //  적립인지 사용인지
                        p.getPointReason().getDescription(),   // 사유
                        p.getTransactionType() == Point.TransactionType.EARN ? p.getPointAmount() : -p.getPointAmount()  // 예) 적립이면 +500 사용이면 -500
                )).toList();

        long total = points.stream().mapToLong(UserPointResponse::getAmount).sum();

        return new UserPointSummary(points, total);
    }
}
