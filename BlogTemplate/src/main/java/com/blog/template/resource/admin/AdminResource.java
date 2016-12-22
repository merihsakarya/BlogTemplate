package com.blog.template.resource.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.blog.template.config.exception.ApplicationRuntimeException;
import com.blog.template.entity.Blog;
import com.blog.template.service.BlogService;

@Component
@Path("admin")
public class AdminResource {
    
    @Autowired
    BlogService blogService;
    
    @GET
    @Path("echo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response echo() {            
        return Response.status(Status.OK).entity("echo").build();           
    }
    
    @Secured("ROLE_ADMIN")
    @GET
    @Path("blogs/pending")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPendingBlogPosts(
            @QueryParam("offset")   @DefaultValue("0")  int offset,
            @QueryParam("limit")    @DefaultValue("20") int limit) throws ApplicationRuntimeException {
        
        List<Blog> blogList = new ArrayList<Blog>();
        blogList = blogService.listPendingBlogPosts(offset, limit);
        
        return Response.status(Status.OK).entity(blogList).build();
    }
    
    @Secured("ROLE_ADMIN")
    @GET
    @Path("blogs/approved")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApprovedBlogPosts(
            @QueryParam("offset")   @DefaultValue("0")  int offset,
            @QueryParam("limit")    @DefaultValue("20") int limit) throws ApplicationRuntimeException {
        
        List<Blog> blogList = new ArrayList<Blog>();
        blogList = blogService.listApprovedBlogPosts(offset, limit);
        
        return Response.status(Status.OK).entity(blogList).build();
    }
    
    @Secured("ROLE_ADMIN")
    @GET
    @Path("blogs/rejected")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRejectedBlogPosts(
            @QueryParam("offset")   @DefaultValue("0")  int offset,
            @QueryParam("limit")    @DefaultValue("20") int limit) throws ApplicationRuntimeException {
        
        List<Blog> blogList = new ArrayList<Blog>();
        blogList = blogService.listRejectedBlogPosts(offset, limit);
        
        return Response.status(Status.OK).entity(blogList).build();
    }
    
    @Secured("ROLE_ADMIN")
    @POST
    @Path("blog/confirm/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response confirmBlog (
            @PathParam("id") long id) throws ApplicationRuntimeException {

        blogService.confirmBlogPost(id);
        
        return Response.status(Status.OK).build();
    }
    
    @Secured("ROLE_ADMIN")
    @POST
    @Path("blog/confirm")
    @Produces(MediaType.APPLICATION_JSON)
    public Response confirmBlog (List<Long> idList) throws ApplicationRuntimeException {
        for (Long id : idList) {
            blogService.confirmBlogPost(id);
        }

        return Response.status(Status.OK).build();
    }
    
    @Secured("ROLE_ADMIN")
    @POST
    @Path("blog/reject/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response rejectBlog (
            @PathParam("id") long id) throws ApplicationRuntimeException {
        
        blogService.rejectBlogPost(id);
        
        return Response.status(Status.OK).build();
    }
    
    @Secured("ROLE_ADMIN")
    @POST
    @Path("blog/reject")
    @Produces(MediaType.APPLICATION_JSON)
    public Response rejectBlog (List<Long> idList) throws ApplicationRuntimeException {
        for (Long id : idList) {
            blogService.rejectBlogPost(id);
        }
    
        return Response.status(Status.OK).build();
    }
    
    @Secured("ROLE_ADMIN")
    @PUT
    @Path("blog/publish/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeBlogPostActive (
            @PathParam("id") long id) throws ApplicationRuntimeException {
        
        blogService.publishBlogPost(id);

        return Response.status(Status.OK).build();
    }
    
    @Secured("ROLE_ADMIN")
    @PUT
    @Path("blog/unpublish/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeBlogPostPassive (
            @PathParam("id") long id) throws ApplicationRuntimeException {
        
        blogService.unpublishBlogPost(id);
        
        return Response.status(Status.OK).build();
    }
    
    @Secured("ROLE_ADMIN")
    @GET
    @Path("blog/detail/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBlogPostDetailsById(
            @PathParam("id") long id) throws ApplicationRuntimeException, JsonProcessingException {
        
        Blog blog = blogService.getBlogPostById(id);
        
        return Response.status(Status.OK).entity(blog).build();
        
    }

}
