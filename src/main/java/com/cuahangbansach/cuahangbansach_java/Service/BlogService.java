package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.Blog;
import com.cuahangbansach.cuahangbansach_java.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    public Blog getBlog(String id) {
        return blogRepository.getReferenceById(id);
    }

    public void SaveBlog(Blog blog) {
        blogRepository.save(blog);
    }

    public void DeleteBlog(Blog blog) {
        blogRepository.delete(blog);
    }
}
