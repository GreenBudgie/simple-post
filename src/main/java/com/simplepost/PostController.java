package com.simplepost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("posts", getPosts());
        return "index";
    }

    @PostMapping("posts/add")
    public String addPost(@ModelAttribute Post post, Model model) {
        postRepository.save(post);
        model.addAttribute("posts", getPosts());
        return "index";
    }

    @PostMapping("posts/delete")
    public String deletePost(@RequestParam Integer id, Model model) {
        if(!postRepository.existsById(id)) throw new IllegalArgumentException("Unknown id:" + id);
        postRepository.deleteById(id);
        model.addAttribute("post", new Post());
        model.addAttribute("posts", getPosts());
        return "index";
    }

    @PostMapping({"posts/clear", "posts/deleteAll"})
    public String deleteAll(Model model) {
        postRepository.deleteAll();
        model.addAttribute("posts", getPosts());
        return "index";
    }

    @GetMapping({"posts/all", "posts/getAll"})
    @ResponseBody
    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }

}
