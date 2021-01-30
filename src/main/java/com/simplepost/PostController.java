package com.simplepost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/add")
    @ResponseBody
    public String addPost(@RequestParam String title, @RequestParam String text) {
        Post post = new Post(title, text);
        postRepository.save(post);
        return "Post created:\n" + post.toString();
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public String deletePost(@RequestParam Integer id) {
        if(!postRepository.existsById(id)) return "There is no post with id " + id;
        postRepository.deleteById(id);
        return "Post with id " + id + " was deleted";
    }

    @DeleteMapping({"/clear", "/deleteAll"})
    @ResponseBody
    public String deleteAll() {
        if(postRepository.count() == 0) return "Nothing to delete";
        postRepository.deleteAll();
        return "All posts were deleted";
    }

    @GetMapping({"/all", "/getAll"})
    @ResponseBody
    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }

}
