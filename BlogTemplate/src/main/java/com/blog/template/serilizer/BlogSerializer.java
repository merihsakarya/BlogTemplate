package com.blog.template.serilizer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.blog.template.entity.Blog;

public class BlogSerializer extends JsonSerializer<Blog> {

	@Override
	public void serialize(Blog blog, JsonGenerator jsonGenerator,
		SerializerProvider provider) throws IOException,
		JsonProcessingException {

		jsonGenerator.writeStartObject(); 
		jsonGenerator.writeNumberField("id", blog.getId());
		jsonGenerator.writeStringField("subject", blog.getSubject());
		jsonGenerator.writeStringField("text", blog.getText());
		
		if(blog.getProfileImage() == null || blog.getProfileImage() == ""){
			jsonGenerator.writeStringField("image", "img/cus-avatar.png");
		} else {
			jsonGenerator.writeStringField("image", blog.getProfileImage());
		}			
		
		Date createdDate = blog.getCreatedDate();
		Instant instant = Instant.ofEpochMilli(createdDate.getTime());
		LocalDateTime localCreatedDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());		
		String absoluteDate = localCreatedDateTime.format(DateTimeFormatter.ofPattern("dd MMM uuuu"));
		jsonGenerator.writeStringField("timestamp", absoluteDate);
		
		jsonGenerator.writeBooleanField("active", blog.isActive());
		
        jsonGenerator.writeEndObject();
	}
}
