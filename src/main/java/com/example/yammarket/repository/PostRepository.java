package com.example.yammarket.repository;



import com.example.yammarket.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> {

    List<Posts> findAllByOrderByModifiedAtDesc();
    // findAll By OrderBy ModifiedAt Desc
    // 모두 찾아라 순서에따라 ModifiedAt을 기준으로 내림차순으로
    // 규칙에 맞게만 써주면 jpa가 알아서 sql문을 짜준다
    // modifiedAt 은 Timestamped 에 있다.

}
