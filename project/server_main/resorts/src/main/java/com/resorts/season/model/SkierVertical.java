package com.resorts.season.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "skier_vertical")
public class SkierVertical {
    @Id
    @Getter
    @Setter
    private Integer id;
    @Column(name = "resort_id")
    @Getter
    @Setter
    private Integer resortId;
    @Column(name = "seasonId")
    @Getter
    @Setter
    private Integer seasonId;
    @Column(name = "total_vert")
    @Getter
    @Setter
    private int totalVert;
}
