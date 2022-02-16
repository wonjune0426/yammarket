package com.example.yammarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Setter
public class Comments extends TimeStamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String userId;
//    @ManyToOne
//    @JoinColumn(name = "user_id",nullable = false)
//    private Users users;
//
//    @ManyToOne
//    @JoinColumn(name = "post_id",nullable = false)
//    private Posts posts;
    public Comments(String comment,Long postId,String userId){
        this.comment=comment;
        this.postId=postId;
        this.userId=userId;
    }

}
