package com.itechart.ekar.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("stop_event")
@Getter
@Setter
public class StopEvent extends Event {

    @Column("reached")
    private String reached;
}
