package model;

/**
 * Created by hitesh.ag on 10/08/17.
 */
import java.util.Date;

public class Todo {

    private long id;
    private String name;
    private String description;
    private String creator;
    private Date date;
    private String status;
    private String assigned;
    private static long todoId = 0;


    public Todo(String name,String description,String creator){
        this.id = ++todoId;
        this.name = name;
        this.description = description;
        this.creator = creator;
        this.date = new Date();
        this.status = "0";
    }


    public String getName() {
        return name;
    }

    synchronized public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    synchronized public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    synchronized public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getDate() {
        return date;
    }

    synchronized public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    synchronized public void setStatus(String status) {
        this.status = status;
    }

    public Long getTodoId() {
        return id;
    }

    public String getAssigned() {
        return assigned;
    }

    synchronized public void setAssigned(String assigned) {
        this.assigned = assigned;
    }
}
