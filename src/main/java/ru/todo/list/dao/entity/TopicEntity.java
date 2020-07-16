package ru.todo.list.dao.entity;

import com.j256.ormlite.field.ForeignCollectionField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TopicEntity extends BaseEntity {

    @Column
    private String name;

    @ForeignCollectionField
    private Collection<TaskEntity> tasks;

    public TopicEntity(String name){
        this.name = name;
    }

}
