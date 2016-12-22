package com.blog.template.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blog.template.dao.BlogDao;
import com.blog.template.entity.Blog;
import com.blog.template.enums.ConfirmationStatusEnum;

@Repository
public class BlogDaoImpl implements BlogDao {

    private EntityManager entityManager;
    
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    public Blog getBlogPostById(Long id) {
        return entityManager.find(Blog.class, id);
    }
    
    @Transactional
    public List<Blog> listAllBlogPosts(int offset, int limit) {
        TypedQuery<Blog> query = entityManager.createQuery("SELECT e FROM Blog e INNER JOIN FETCH e.company ORDER BY e.createdDate DESC", Blog.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        
        List<Blog> blogList = query.getResultList();
        
        return blogList;
    }
    
    @Transactional
    public List<Blog> listPendingBlogPosts(int offset, int limit) {
        TypedQuery<Blog> query = entityManager.createQuery("SELECT e FROM Blog e INNER JOIN FETCH e.company WHERE e.status = 1 ORDER BY e.createdDate DESC", Blog.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        
        List<Blog> blogList = query.getResultList();
        
        return blogList;
    }
    
    @Transactional
    public List<Blog> listApprovedBlogPosts(int offset, int limit) {
        TypedQuery<Blog> query = entityManager.createQuery("SELECT e FROM Blog e INNER JOIN FETCH e.company WHERE e.status = 2 ORDER BY e.createdDate DESC", Blog.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        
        List<Blog> blogList = query.getResultList();
        
        return blogList;
    }
    
    @Transactional
    public List<Blog> listRejectedBlogPosts(int offset, int limit) {
        TypedQuery<Blog> query = entityManager.createQuery("SELECT e FROM Blog e INNER JOIN FETCH e.company WHERE e.status = 3 ORDER BY e.createdDate DESC", Blog.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        
        List<Blog> blogList = query.getResultList();
        
        return blogList;
    }

    @Transactional
    public List<Blog> listPublishedBlogPosts(int offset, int limit) {
        TypedQuery<Blog> query = entityManager.createQuery("SELECT e FROM Blog e INNER JOIN FETCH e.company WHERE e.status = 2 and e.active = 1 ORDER BY e.createdDate DESC", Blog.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        
        List<Blog> blogList = query.getResultList();
        
        return blogList;
    }
    
    @Transactional
    public void confirmBlogPost(Long id) {
        Blog blog = entityManager.find(Blog.class, id);
        blog.setStatus(ConfirmationStatusEnum.APPROVED_BY_ADMIN.getCode());
    }
    
    @Transactional
    public void rejectBlogPost(Long id) {
        Blog blog = entityManager.find(Blog.class, id);
        blog.setStatus(ConfirmationStatusEnum.REJECTED_BY_ADMIN.getCode());
    }
    
    @Transactional
    public void activateBlogPost(Long id) {
        Blog blog = entityManager.find(Blog.class, id);
        blog.setActive(true);
        entityManager.merge(blog);
    }
    
    @Transactional
    public void deactivateBlogPost(Long id) {
        Blog blog = entityManager.find(Blog.class, id);
        blog.setActive(false);
        entityManager.merge(blog);
    }
    
    @Transactional
    public void createBlogPost(Blog blog) {
        entityManager.persist(blog);
    }

    @Transactional
    public void updateBlogPost(Long id, String subject, String text) {
        Blog blog = entityManager.find(Blog.class, id);
        blog.setSubject(subject);
        blog.setText(text);     
    }

}
