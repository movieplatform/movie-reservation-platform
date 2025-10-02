package com.example.movieplatform.point.repository;

import com.example.movieplatform.point.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findByUserIdOrderByTransactionDateDesc(Long userId);

    @Query("""
        SELECT COALESCE(SUM(
            CASE
                WHEN p.transactionType = com.example.movieplatform.point.entity.Point.TransactionType.EARN THEN p.pointAmount
                ELSE -p.pointAmount
            END
        ), 0)
        FROM Point p
        WHERE p.user.id = :userId
    """)
    Long getUserPointBalance(@Param("userId") Long userId);
}
