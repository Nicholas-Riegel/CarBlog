package com.car_blog.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// WebConfig is the read side of the image feature. ImageController writes files to disk, and WebConfig makes those files viewable in the browser.
// @Configuration marks the class as Spring configuration, so Spring loads it at startup.
// implements WebMvcConfigurer lets you customize Spring MVC. Here you override one hook, addResourceHandlers.
// addResourceHandler("/uploads/**") says: for any request URL starting with /uploads/ (the ** matches any path after it), don't look for a controller. Serve a file instead.
// addResourceLocations("file:uploads/") says where to find those files. The file: prefix means a real folder on disk, relative to the working directory. Without it, Spring would look inside the compiled classpath, which is the problem we discussed with static/.
// So a request to /uploads/abc.jpg makes Spring return the file uploads/abc.jpg from disk, with the right Content-Type (image/jpeg) based on the extension.

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}