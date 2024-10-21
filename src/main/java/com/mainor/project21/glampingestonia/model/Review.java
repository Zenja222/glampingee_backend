package com.mainor.project21.glampingestonia.model;

import lombok.Data;

@Data
public class Review {
    private String glampingId; // ID глэмпинга, к которому относится отзыв
    private int rating; // Рейтинг от 1 до 5
}
