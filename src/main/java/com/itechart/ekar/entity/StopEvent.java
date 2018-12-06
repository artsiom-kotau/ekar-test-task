package com.itechart.ekar.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("stop_event")
@Getter
@Setter
public class StopEvent extends Event {

}
