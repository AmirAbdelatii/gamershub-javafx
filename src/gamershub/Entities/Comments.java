/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Entities;

import java.sql.Date;

/**
 *
 * @author amira
 */
public class Comments {
    private int id;
    private String comment;
    private Date commentedAt;
    private Blog blog;

    public Comments() {
    }

    public Comments(String comment, Date commentedAt, Blog blog) {
        this.comment = comment;
        this.commentedAt = commentedAt;
        this.blog = blog;
    }

    public Comments(String comment, Blog blog) {
        this.comment = comment;
        this.blog = blog;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Date getCommentedAt() {
        return commentedAt;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentedAt(Date commentedAt) {
        this.commentedAt = commentedAt;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "Comments{" + "id=" + id + ", comment=" + comment + ", commentedAt=" + commentedAt + ", blog=" + blog + '}';
    }
    
}
