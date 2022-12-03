package com.example.webserver.mapper;

import com.example.webserver.dto.ChatDTO;
import com.example.webserver.dto.MessageDTO;
import com.example.webserver.dto.ProjectDTO;
import com.example.webserver.dto.ProjectStaffDTO;
import com.example.webserver.dto.TopicDTO;
import com.example.webserver.dto.TopicMessageDTO;
import com.example.webserver.dto.UserDTO;
import com.example.webserver.model.Chat;
import com.example.webserver.model.Message;
import com.example.webserver.model.Project;
import com.example.webserver.model.ProjectStaff;
import com.example.webserver.model.Topic;
import com.example.webserver.model.TopicMessage;
import com.example.webserver.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-03T12:02:32+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.12 (Azul Systems, Inc.)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public void updateUserFromDto(UserDTO dto, User entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getUsername() != null ) {
            entity.setUsername( dto.getUsername() );
        }
        if ( dto.getUserLN() != null ) {
            entity.setUserLN( dto.getUserLN() );
        }
        if ( dto.getPassword() != null ) {
            entity.setPassword( dto.getPassword() );
        }
        if ( dto.getCode() != null ) {
            entity.setCode( dto.getCode() );
        }
        if ( dto.getUserFN() != null ) {
            entity.setUserFN( dto.getUserFN() );
        }
        if ( dto.getMatchingPassword() != null ) {
            entity.setMatchingPassword( dto.getMatchingPassword() );
        }
    }

    @Override
    public void updateChatFromDto(ChatDTO dto, Chat entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getUserId1() != null ) {
            entity.setUserId1( dto.getUserId1() );
        }
        if ( dto.getUserId2() != null ) {
            entity.setUserId2( dto.getUserId2() );
        }
    }

    @Override
    public void updateMessageFromDto(MessageDTO dto, Message entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getUserId() != null ) {
            entity.setUserId( dto.getUserId() );
        }
        if ( dto.getText() != null ) {
            entity.setText( dto.getText() );
        }
        if ( dto.getDoc() != null ) {
            entity.setDoc( dto.getDoc() );
        }
        if ( dto.getTime() != null ) {
            entity.setTime( dto.getTime() );
        }
    }

    @Override
    public void updateProjectFromDto(ProjectDTO dto, Project entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getProjectName() != null ) {
            entity.setProjectName( dto.getProjectName() );
        }
        if ( dto.getDescription() != null ) {
            entity.setDescription( dto.getDescription() );
        }
    }

    @Override
    public void updateProjectStaffFromDto(ProjectStaffDTO dto, ProjectStaff entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getUserId() != null ) {
            entity.setUserId( dto.getUserId() );
        }
        if ( dto.getProjectId() != null ) {
            entity.setProjectId( dto.getProjectId() );
        }
    }

    @Override
    public void updateTopicFromDto(TopicDTO dto, Topic entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getProjectId() != null ) {
            entity.setProjectId( dto.getProjectId() );
        }
        if ( dto.getNameTopic() != null ) {
            entity.setNameTopic( dto.getNameTopic() );
        }
        if ( dto.getCategory() != null ) {
            entity.setCategory( dto.getCategory() );
        }
    }

    @Override
    public void updateTopicMessageFromDto(TopicMessageDTO dto, TopicMessage entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getUserId() != null ) {
            entity.setUserId( dto.getUserId() );
        }
        if ( dto.getText() != null ) {
            entity.setText( dto.getText() );
        }
        if ( dto.getDoc() != null ) {
            entity.setDoc( dto.getDoc() );
        }
        if ( dto.getTime() != null ) {
            entity.setTime( dto.getTime() );
        }
    }
}
