package com.spring.sqa_fullstack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", nullable = false)
    private Long id;
    @Column(name = "schedule_date")
    private LocalDate date;
    @Column(name = "schedule_start_time")
    private LocalTime startTime;
    @Column(name = "schedule_end_time")
    private LocalTime endTime;
    @Column(name = "schedule_price")
    private int price;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(nullable = false,name = "auditorium_id", referencedColumnName = "auditorium_id")
    private Auditorium auditorium;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(nullable = false, name = "movie_id", referencedColumnName = "movie_id")
    private Movie movie;
}
