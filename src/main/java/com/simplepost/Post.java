package com.simplepost;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTimestamp;

    public Post() {
        creationTimestamp = new Date();
    }

    public Post(String title, String text) {
        this();
        this.title = title;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getCreationDateFormatted() {
        return new SimpleDateFormat("dd.MM.yyyy").format(creationTimestamp);
    }

    public String getCreationTimeFormatted() {
        return new SimpleDateFormat("HH:mm").format(creationTimestamp);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", creationDate=" + getCreationDateFormatted() +
                ", creationTime=" + getCreationTimeFormatted() +
                '}';
    }
}
