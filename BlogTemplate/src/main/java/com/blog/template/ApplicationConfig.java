package com.blog.template;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.blog.template.resource.BlogResource;

@Component
@ApplicationPath("/v1")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
    	register(MultiPartFeature.class);
    	/** End Points **/ 
        register(BlogResource.class);       
    }
}