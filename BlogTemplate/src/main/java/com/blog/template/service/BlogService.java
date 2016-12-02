package com.blog.template.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.template.dao.BlogDao;
import com.blog.template.entity.Blog;
import com.blog.template.enums.ConfirmationStatusEnum;

@Service
public class BlogService {
	
	@Autowired
	BlogDao blogDao;
	
	public Blog getBlogPostById(Long id){
		return blogDao.getBlogPostById(id);
	}
	
	public List<Blog> listPendingBlogPosts(int offset, int limit){
		return blogDao.listPendingBlogPosts(offset, limit);
	}
	
	public List<Blog> listApprovedBlogPosts(int offset, int limit){
		return blogDao.listApprovedBlogPosts(offset, limit);
	}
	
	public List<Blog> listRejectedBlogPosts(int offset, int limit){
		return blogDao.listRejectedBlogPosts(offset, limit);
	}
	
	public List<Blog> listPublishedBlogPosts(int offset, int limit){
		return blogDao.listPublishedBlogPosts(offset, limit);
	}
	
	public void rejectBlogPost(Long id){
		blogDao.rejectBlogPost(id);
	}
	
	public void confirmBlogPost(Long id){
		blogDao.confirmBlogPost(id);
	}

	public void publishBlogPost(Long id) {
		blogDao.activateBlogPost(id);
	}

	public void unpublishBlogPost(Long id) {
		blogDao.deactivateBlogPost(id);
	}
	
	public void createBlogPost(String subject, String text){
		Blog blog = new Blog();
		blog.setSubject(subject);
		blog.setText(text);
		blog.setCreatedDate(new Date());
		blog.setUpdatedDate(new Date());
		blog.setStatus(ConfirmationStatusEnum.PENDING.getCode());
		blogDao.createBlogPost(blog);
	}
	
	public void updateBlogPost(Long id, String subject, String text){
		blogDao.updateBlogPost(id, subject, text);
	}
	
}
