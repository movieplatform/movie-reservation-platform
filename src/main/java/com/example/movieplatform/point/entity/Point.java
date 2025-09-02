package com.example.movieplatform.point.entity;

import com.example.movieplatform.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "point_history")
@Getter
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pointAmount;   // 적립 또는 사용한 포인트
    private LocalDateTime transactionDate;   // 적립 사용 날짜
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    private PointReason pointReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Point() {}
    public Point(User user, Long pointAmount, LocalDateTime transactionDate, TransactionType transactionType, PointReason pointReason) {
        this.user = user;
        this.pointAmount = pointAmount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.pointReason = pointReason;
    }

    // 회원가입 포인트 적립
    public static Point earnForRegister(User user) {
        return new Point(user, 5000L, LocalDateTime.now(), TransactionType.EARN, PointReason.REGISTER);
    }

    // 구매 포인트 적립
    public static Point earnForPurchase(User user, Long pointAmount) {
        return new Point(user, pointAmount, LocalDateTime.now(), TransactionType.EARN, PointReason.PURCHASE);
    }

    // 리뷰 작성 포인트 적립
    public static Point earnForReview(User user, Long pointAmount) {
        return new Point(user, pointAmount, LocalDateTime.now(), TransactionType.EARN, PointReason.REVIEW);
    }

    // 결제 포인트 차감
    public static Point useForPayment(User user, Long pointAmount) {
        return new Point(user, pointAmount, LocalDateTime.now(), TransactionType.USE, PointReason.RESERVATION_PAYMENT);
    }

    @Getter
    public enum TransactionType
    {
        EARN("적립"),
        USE("사용");

        private final String description;

        TransactionType(String description) {
            this.description = description;
        }
    }

    @Getter
    public enum PointReason
    {
        REGISTER("회원가입"),
        PURCHASE("구매 적립"),
        REVIEW("리뷰 작성 적립"),
        RESERVATION_PAYMENT("좌석 예매 결제 차감");

        private final String description;

        PointReason(String description) {
            this.description = description;
        }
    }
}
