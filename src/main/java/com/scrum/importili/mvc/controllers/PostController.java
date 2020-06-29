package com.scrum.importili.mvc.controllers;

import com.scrum.importili.payload.request.PostSearchQuery;
import com.scrum.importili.payload.response.ListResponse;
import com.scrum.importili.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.scrum.importili.payload.request.NewPostData;


@CrossOrigin(origins="http://localhost:4200")
@Controller
@RequestMapping(path="/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping(path="/add")
    public ResponseEntity<?> createPost(@RequestBody NewPostData data)
    {
        return postService.createPost(data);
    }
    
        @GetMapping(path="/list/{page}")
    public ResponseEntity<ListResponse> getPostList(@PathVariable int page)
    {
        return postService.getPostList(page);
    }
    
    @GetMapping(path="/{id}")
    public ResponseEntity<?> getPost(@PathVariable Integer id)
    {
        return postService.getPost(id);
    }
    
    @PostMapping(path="/search")
    public ResponseEntity<ListResponse> searchPosts(@RequestBody PostSearchQuery query)
    {
        return postService.searchPosts(query);
    }

}