package com.example.movieplatform.point.service;

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

    public List<Point> getAllPointsByUserId(Long userId){
        return pointRepository.findByUserId(userId);
    }
}
