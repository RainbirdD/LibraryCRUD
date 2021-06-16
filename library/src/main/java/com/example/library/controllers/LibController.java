package com.example.library.controllers;

import com.example.library.models.Post;
import com.example.library.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class LibController {

    @Autowired
    private PostRepository postRepository;

    public LibController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/lib")
    public String libMain(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);

        return "lib-main";

    }

    @GetMapping("/lib/add")
    public String libAdd(Model model){
        return "lib-add";

    }


    @GetMapping("/lib/search")
    public String search(Map<String, Object> model) {
       Iterable<Post> posts =  postRepository.findAll();
        model.put("posts", posts);
        return "search";

    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        List<Post> posts = postRepository.findBybookTitle(filter);
        model.put("posts",posts);
        return "search";
    }

    @PostMapping("filter2")
    public String filter2(@RequestParam int filter2, Map<String, Object> model){
        List<Post> posts = postRepository.findByisbn(filter2);
        model.put("posts",posts);
        return "search";
    }
    @PostMapping("filter3")
    public String filter3(@RequestParam String filter3, Map<String, Object> model){
        List<Post> posts = postRepository.findByauthor(filter3);
        model.put("posts",posts);
        return "search";
    }



    @PostMapping("deleteById")
    public String remove(@RequestParam long deleteById) {
        postRepository.deleteById(deleteById);
        return "lib-main";
    }



    @PostMapping("/lib/add")
    public String libPostAdd(
            @RequestParam String bookTitle,
            @RequestParam String author,
            @RequestParam String genre,
            @RequestParam String publisher,
            @RequestParam String storage,
            @RequestParam int isbn,
            @RequestParam int yearOfBook, Map<String, Object> model){

        Post post = new Post(bookTitle, author, genre, publisher, storage, isbn, yearOfBook);
        postRepository.save(post);
        Iterable<Post> posts =  postRepository.findAll();
        model.put("posts", posts);
        return "lib-main";
    }


}
