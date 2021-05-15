package com.samsung.bitcoin.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TickPrice {
    private String marketId;
    private Date timestamp;
    private double tickPrice;
}
