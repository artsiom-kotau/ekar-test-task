package com.itechart.ekar.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("request_event")
@Getter
@Setter
public class RequestEvent extends Event {

    @Column("payload")
    private String payload;
}
