package com.samsung.bitcoin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Market {
    String marketId;
    String koreaName;
    String englishName;
    String warning;
}
