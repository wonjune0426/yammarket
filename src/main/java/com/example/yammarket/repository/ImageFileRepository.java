package com.example.yammarket.repository;



import com.example.yammarket.model.ImageFiles;
import com.example.yammarket.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageFileRepository extends JpaRepository<ImageFiles, Long> {


}
