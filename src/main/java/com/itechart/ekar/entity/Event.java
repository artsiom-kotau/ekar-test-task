package com.itechart.ekar.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class Event {

    @Id
    private Long id;

    @Column("event_time")
    private LocalDateTime eventTime;


}
