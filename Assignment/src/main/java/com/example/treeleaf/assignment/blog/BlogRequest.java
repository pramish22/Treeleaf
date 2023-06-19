package com.example.treeleaf.assignment.blog;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRequest {

    private Long id;

    private String content;

    private String title;

    private String username;
}
