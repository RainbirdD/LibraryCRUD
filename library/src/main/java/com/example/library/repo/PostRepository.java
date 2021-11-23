package com.example.library.repo;

import com.example.library.models.Post;
import com.mysql.cj.protocol.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
List<Post> findBybookTitle(String bookTitle);
List<Post> findByisbn(int isbn);
List<Post> findByauthor(String author);

}
