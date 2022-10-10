package Root.Model;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private String content;

    private Date date;

    @ElementCollection
    private List<Teg> tegs;
@ManyToOne
    private User user;

    public void setTegs(List<Teg> tegs) {
        this.tegs = tegs;
    }

    public List<Teg> getTeg() {
        return tegs;
    }

    public News(String title, String content, List<Teg> tegs) {
        this.title = title;
        this.tegs = tegs;
        this.content = content;


    }
public void setUser(User  user){
  this.user=user;
}
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
public User getUser(){
   return user;
}
    public void setTitle(String title) {
        this.title = title;
    }
public void setDate (Date date){
 this.date=date;
}
public Date getDate(){
        return date;
}
public String getTitle() {
        return title;
    }

    public News(int id,String title,String content,Date date,List<Teg>tegs,User user) {
this.id=id;
this.title=title;
this.content=content;
this.date=date;
this.tegs=tegs;
this.user=user;
    }
public News (){

}
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + ""
                + tegs +
                "" + title + "" +
                "" + content
                +""+date;

    }
public String getUsername(){
if (getUser().getUsername()==null){
return "";
}
    return getUser().getUsername();

}

}
