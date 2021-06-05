package com.example.library.controllers;

import com.example.library.models.Post;
import com.example.library.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;
    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);

        return "blog-main";

    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog-add";

    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);

        ArrayList<Post> res=new ArrayList<>();
        post.ifPresent((res::add));
        model.addAttribute("post",res);
        return "blod-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);

        ArrayList<Post> res=new ArrayList<>();
        post.ifPresent((res::add));
        model.addAttribute("post",res);
        return "blod-edit";
    }




    @GetMapping
    public String home(Map<String, Object> model) {
/*        Iterable<Post> posts =  postRepository.findAll();
        model.put("posts", posts);*/
        return "home";

    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        List<Post> posts = postRepository.findBybookTitle(filter);
        model.put("posts",posts);
        return "home";
    }

    @PostMapping("filter2")
    public String filter2(@RequestParam int filter2, Map<String, Object> model){
        List<Post> posts = postRepository.findByisbn(filter2);
        model.put("posts",posts);
        return "home";
    }
    @PostMapping("filter3")
    public String filter3(@RequestParam String filter3, Map<String, Object> model){
        List<Post> posts = postRepository.findByauthor(filter3);
        model.put("posts",posts);
        return "home";
    }



    @PostMapping("deleteById")
    public String remove(@RequestParam long deleteById) {
        postRepository.deleteById(deleteById);
        return "blog";
    }



    @PostMapping("/blog/add")
    public String blogPostAdd(
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
        return "home";
    }

/*
    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(
            @PathVariable(value = "id") long id,
            @RequestParam String bookTitle,
            @RequestParam String author,
            @RequestParam String genre,
            @RequestParam String publisher,
            @RequestParam String storage,
            @RequestParam int isbn,
            @RequestParam int yearOfBook, Map<String, Object> model){
        Post post = postRepository.findById(id).orElseThrow();
        post.setBookTitle(bookTitle);
        post.setAuthor(author);
        post.setGenre(genre);
        post.setPublisher(publisher);
        post.setStorage(storage);
        post.getIsbn(isbn);
        post.setYearOfBook(yearOfBook);
        postRepository.save(post);
        return "blog-edit";
    }*/


}
