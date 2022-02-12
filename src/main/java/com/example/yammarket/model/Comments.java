package com.example.yammarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comments {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(nullable = false, name = "post_id")
    private Posts posts;

    @Column(nullable = false)
    private String comment;
}
