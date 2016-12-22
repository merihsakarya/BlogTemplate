package com.blog.template.resource;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blog.template.entity.Blog;
import com.blog.template.service.BlogService;

@Component
@Path("blog")
public class BlogResource {
        
    @Autowired
    BlogService blogService;
    
    @GET
    @Path("echo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response echo() {            
        return Response.status(Status.OK).entity("echo").build();           
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBlogPostById(
            @PathParam("id") long id) {

        Blog blog = blogService.getBlogPostById(id);
        
        return Response.status(Status.OK).entity(blog).build();
    }
    
    @GET
    @Path("published")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPublishedBlogPosts(
            @QueryParam("offset")   @DefaultValue("0")  int offset,
            @QueryParam("limit")    @DefaultValue("20") int limit) {
                
        List<Blog> blogList = blogService.listPublishedBlogPosts(offset, limit);
        
        return Response.status(Status.OK).entity(blogList).build(); 
    }

}