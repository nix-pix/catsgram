package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.IncorrectParameterException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;

import static ru.yandex.practicum.catsgram.Constants.DESCENDING_ORDER;
import static ru.yandex.practicum.catsgram.Constants.SORTS;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> findAllPosts(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size,
            @RequestParam(defaultValue = DESCENDING_ORDER, required = false) String sort
    ) {
        if (!SORTS.contains(sort)) {
            throw new IncorrectParameterException("sort");
        }
        if (page < 0) {
            throw new IncorrectParameterException("page");
        }
        if (size <= 0) {
            throw new IncorrectParameterException("size");
        }
        Integer from = page * size;
        return postService.findAllPosts(size, from, sort);
    }

    @PostMapping(value = "/post")
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @GetMapping("/post/{postId}")
    public Post findPostById(@PathVariable("postId") Integer postId) {
        return postService.findPostById(postId);
    }
}
