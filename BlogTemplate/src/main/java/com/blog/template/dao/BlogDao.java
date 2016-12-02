package com.blog.template.dao;

import java.util.List;

import com.blog.template.entity.Blog;

public interface BlogDao {
	
	/*
	 * Get Blog Post
	 */
	public Blog getBlogPostById(Long id);
	
	/*
	 * List Blog Posts
	 */
	public List<Blog> listAllBlogPosts(int offset, int limit);
	public List<Blog> listPendingBlogPosts(int offset, int limit);
	public List<Blog> listApprovedBlogPosts(int offset, int limit);
	public List<Blog> listRejectedBlogPosts(int offset, int limit);
	public List<Blog> listPublishedBlogPosts(int offset, int limit);
	
	/*
	 * ADMIN Controller 
	 */
	public void rejectBlogPost(Long id);
	public void confirmBlogPost(Long id);
	
	/*
	 * Publish/Unpublish Blog Post
	 */
	public void activateBlogPost(Long id);
	public void deactivateBlogPost(Long id);
	
	/*
	 * Create/Update Blog Posts
	 */
	public void createBlogPost(Blog blog);
	public void updateBlogPost(Long id, String subject, String text);
}
