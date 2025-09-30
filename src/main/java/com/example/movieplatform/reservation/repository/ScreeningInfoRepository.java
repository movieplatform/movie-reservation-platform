package com.example.movieplatform.reservation.repository;

import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.reservation.dto.ScreeningInfoDto;
import com.example.movieplatform.reservation.entity.ScreeningInfo;
import com.example.movieplatform.theater.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScreeningInfoRepository extends JpaRepository<ScreeningInfo, Long> {
    @Query("SELECT si FROM ScreeningInfo si " +
            "WHERE si.screeningDate >= :today OR (si.screeningDate = :today AND si.endTime >= :nowTime)")
    List<ScreeningInfo> findActiveAndTodayScreenings(@Param("today") LocalDate today, @Param("nowTime") LocalTime nowTime);

    @Query("SELECT si FROM ScreeningInfo si " +
            "WHERE si.screeningStatus = :status AND si.screeningDate < :today")
    List<ScreeningInfo> findByScreeningStatusAndScreeningDateBefore(
            @Param("status") ScreeningInfo.ScreeningStatus status,
            @Param("today") LocalDate today);

    @Query("SELECT MAX(si.screeningDate) FROM ScreeningInfo si WHERE si.screen = :screen")
    LocalDate findMaxScreeningDateByScreen(@Param("screen") Screen screen);

    boolean existsByScreenAndScreeningDateAndStartTime(Screen screen, LocalDate date, LocalTime startTime);

    @Query("""
        SELECT new com.example.movieplatform.reservation.dto.ScreeningInfoDto(
            si.id,
            si.screeningDate,
            s.screenName,
            m.title,
            si.startTime,
            si.endTime,
            si.screeningStatus
        )
        FROM ScreeningInfo si
        JOIN si.screen s
        JOIN si.movie m
        WHERE s.theater.id = :theaterId
        AND si.screeningStatus = com.example.movieplatform.reservation.entity.ScreeningInfo.ScreeningStatus.SCHEDULED
        ORDER BY si.screeningDate, s.screenName, si.startTime
    """)
    List<ScreeningInfoDto> findScreeningDtoByTheaterId(@Param("theaterId")Long theaterId);

    @Query("SELECT si FROM ScreeningInfo si " +
            "JOIN si.screen s " +
            "JOIN s.theater t " +
            "JOIN si.movie m " +
            "WHERE t.id = :theaterId " +
            "AND m.docId = :docId " +
            "AND si.screeningDate = :screeningDate " +
            "AND si.screeningStatus = com.example.movieplatform.reservation.entity.ScreeningInfo.ScreeningStatus.SCHEDULED")
    List<ScreeningInfo> findByTheaterMovieAndDate(@Param("theaterId") Long theaterId,
                                                  @Param("docId") String docId,
                                                  @Param("screeningDate") LocalDate screeningDate);

    @Query("""
         SELECT DISTINCT m
         FROM ScreeningInfo si
         JOIN si.movie m
         WHERE si.screeningStatus = com.example.movieplatform.reservation.entity.ScreeningInfo.ScreeningStatus.SCHEDULED
    """)
    List<Movie> findAllScheduledMovies();

}
