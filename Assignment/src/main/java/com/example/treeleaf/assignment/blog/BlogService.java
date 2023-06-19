package com.example.treeleaf.assignment.blog;

import com.example.treeleaf.assignment.entity.Blog;
import com.example.treeleaf.assignment.exception.PostNotFoundException;
import com.example.treeleaf.assignment.user.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public void createPost(BlogRequest blogRequest) {
        Blog post = mapFromDtoToPost(blogRequest);

        blogRepository.save(post);
    }

    public List<Blog> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();

        return blogs.stream().map(this::mapFromPostToDto).collect(toList());
    }

    private Blog mapFromDtoToPost(BlogRequest blogRequest) {
        Blog post = new Blog();
        post.setTitle(blogRequest.getTitle());
        post.setContent(blogRequest.getContent());
        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        post.setUsername(loggedInUser.getUsername());

        return post;
    }

    private Blog mapFromPostToDto(Blog blog) {
        Blog post = new Blog();
        post.setId(post.getId());
        post.setTitle(post.getTitle());
        post.setContent(post.getContent());
        post.setUsername(post.getUsername());

        return post;
    }

    public Blog getBlogById(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));

        return mapFromPostToDto(blog);
    }

    public Optional<Blog> updateBlog(Long id, BlogRequest blogRequest) {
        Optional<Blog> blog = blogRepository.findById(id);

        if (blog.isPresent()) {
            Blog existingBlog = blog.get();
            existingBlog.setTitle(blogRequest.getTitle());

            return Optional.of(blogRepository.save(existingBlog));
        }

        return Optional.empty();
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
