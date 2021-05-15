package com.samsung.bitcoin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TargetTickPrice {
    private String token;
    private String marketId;
    private Double tickPrice;
}
