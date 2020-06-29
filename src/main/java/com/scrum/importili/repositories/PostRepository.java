package com.scrum.importili.repositories;
import com.scrum.importili.mvc.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query("select p from Post p")
    Page<Post> findAll(Pageable pageable);
}