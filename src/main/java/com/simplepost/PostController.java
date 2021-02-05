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

    @DeleteMapping("posts/delete")
    @ResponseBody
    public String deletePost(@RequestParam Integer id) {
        if(!postRepository.existsById(id)) return "There is no post with id " + id;
        postRepository.deleteById(id);
        return "Post with id " + id + " was deleted";
    }

    @DeleteMapping({"posts/clear", "posts/deleteAll"})
    @ResponseBody
    public String deleteAll() {
        if(postRepository.count() == 0) return "Nothing to delete";
        postRepository.deleteAll();
        return "All posts were deleted";
    }

    @GetMapping({"posts/all", "posts/getAll"})
    @ResponseBody
    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }

}
