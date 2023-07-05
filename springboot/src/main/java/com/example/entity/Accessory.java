package com.example.entity;

import lombok.Data;

@Data
public class Accessory {
    private Integer id;
    private String type;
    private String name;
    private String skin;
    private String quality;
    private Byte img;
    private String paint_seed;
    private String paint_index;
    private String appearance;
    private String abrasion;
    private String attribute_description;
    private String price;
    private String status;
}
