package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.Collection;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

//    @GetMapping("/posts")
//    public List<Post> findAllPosts(
//            @RequestParam(defaultValue = "0", required = false) Integer page,
//            @RequestParam(defaultValue = "10", required = false) Integer size,
//            @RequestParam(defaultValue = DESCENDING_ORDER, required = false) String sort
//    ) {
//        if (!SORTS.contains(sort)) {
//            throw new IncorrectParameterException("sort");
//        }
//        if (page < 0) {
//            throw new IncorrectParameterException("page");
//        }
//        if (size <= 0) {
//            throw new IncorrectParameterException("size");
//        }
//        Integer from = page * size;
//        return postService.findAllPosts(size, from, sort);
//    }

    @GetMapping("/posts")
    public Collection<Post> findAll(@RequestParam String userId) {
        return postService.findPostsByUser(userId);
    }

//    @PostMapping(value = "/post")
//    public Post createPost(@RequestBody Post post) {
//        return postService.createPost(post);
//    }

//    @GetMapping("/post/{postId}")
//    public Post findPostById(@PathVariable("postId") Integer postId) {
//        return postService.findPostById(postId);
//    }
}
