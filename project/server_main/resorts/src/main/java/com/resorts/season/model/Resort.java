package com.resorts.season.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "resort")
public class Resort {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer resortId;

    @Column(name = "resort_name")
    @Getter
    @Setter
    private String resortName;
}
