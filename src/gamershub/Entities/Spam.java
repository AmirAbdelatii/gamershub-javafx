/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.Entities;

/**
 *
 * @author amira
 */
public class Spam {
    int id;
    Blog blog;
    User user;

    public Spam() {
    }

    public Spam(int id, Blog blog, User user) {
        this.id = id;
        this.blog = blog;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public Blog getBlog() {
        return blog;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Spam{" + "id=" + id + '}';
    }
    
    
}
