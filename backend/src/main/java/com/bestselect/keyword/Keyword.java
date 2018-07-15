package com.bestselect.keyword;

import com.bestselect.users.Users;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Keyword {

    @ManyToOne
    private Users user;

    @Id
    private String content;
    private String created_date;
    private String updated_date;
    private String deleted_date;

    public Keyword() {
    }

    public Keyword(String content, String created_date, String updated_date, String deleted_date,Users user) {
        super();
        this.content = content;

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        
        this.created_date = strDate;
        this.updated_date = strDate;
        this.deleted_date = strDate;

        this.setUser(user);

    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getDeleted_date() {
        return deleted_date;
    }

    public void setDeleted_date(String deleted_date) {
        this.deleted_date = deleted_date;
    }
}
