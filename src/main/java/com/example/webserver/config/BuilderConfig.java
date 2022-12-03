package com.example.webserver.config;

import com.example.webserver.builder.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuilderConfig {

    @Bean
    public ChatBuilder ChatBuilderConfig(){
        return new ChatBuilder();
    }

    @Bean
    public MessageBuilder MessageBuilderConfig(){
        return new MessageBuilder();
    }

    @Bean
    public ProjectBuilder ProjectBuilderConfig(){
        return new ProjectBuilder();
    }

    @Bean
    public ProjectStaffBuilder  ProjectStaffBuilderConfig(){
        return new ProjectStaffBuilder();
    }
    @Bean
    public TopicBuilder TopicBuilderConfig(){
        return new TopicBuilder();
    }
    @Bean
    public TopicMessageBuilder TopicMessageBuilderConfig(){
        return new TopicMessageBuilder();
    }
    @Bean
    public UserBuilder UserBuilderConfig(){
        return new UserBuilder();
    }



}
