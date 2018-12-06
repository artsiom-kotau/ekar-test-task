package com.itechart.ekar.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientConfiguration {

    private Integer producer;

    private Integer consumer;

    public boolean isEmpty() {
        return producer == null && consumer == null;
    }
}
