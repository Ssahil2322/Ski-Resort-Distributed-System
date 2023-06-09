package com.resorts.season.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "resort_skier")
public class ResortSkier {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer resortSkierId;
    @Column(name = "resort_id")
    @Getter
    @Setter
    private Integer resortId;
    @Column(name = "time")
    @Getter
    @Setter
    private int time;

    @Column(name = "num_of_skiers")
    @Setter
    @Getter
    private int numOfSkiers;
}
