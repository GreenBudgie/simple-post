package com.simplepost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addPost(@RequestParam String title, @RequestParam String text) {
        Post post = new Post(title, text);
        postRepository.save(post);
        return "Post created";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Post> getPosts() {
        return postRepository.findAll();
    }

}
