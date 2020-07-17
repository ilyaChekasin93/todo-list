package ru.todo.list.dao.entity;

import com.j256.ormlite.field.DatabaseField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String content;

    @Column
    private boolean active;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private TopicEntity topic;

    public TaskEntity(String name, String content, TopicEntity topic){
        this.name = name;
        this.content = content;
        this.topic = topic;
        this.active = true;
    }

}
