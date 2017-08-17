package database;

import model.Todo;
import model.User;

import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Created by hitesh.ag on 10/08/17.
 */
public class Store {

    public static Store instance = null;

    private Map<String, User> LoginMap; //sessionId,User
    private List<Todo> todos;
    private Map<String,User> userRegistered;//username,User

    private Map<Date,Todo > updatedTodos; //used for addition/updation and maintained for each timestamp

    synchronized public static Store getInstance(){
        if(instance == null){
            instance = new Store();
            instance.LoginMap = new HashMap<String, User>();
            instance.todos = new ArrayList<Todo>();
            instance.userRegistered = new HashMap<String, User>();
            instance.updatedTodos = new HashMap<Date, Todo>();
        }
        return instance;
    }

    public Map<String, User> getLoginMap() {
        return LoginMap;
    }

    public Map<String,User> getUserRegistered(){
        return userRegistered;
    }

    public void addLogin(String sessionId,User user ) {
        this.LoginMap.put(sessionId,user);
    }

    public void addUser(String name,User user){
        this.userRegistered.put(name,user);
    }

    public Todo getTodo(Long id){
        for(Todo todo : todos){
            if(todo.getTodoId().equals(id)){
                return todo;
            }
        }
        return null;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void addTodos( Todo todo ) {
        this.todos.add(todo);
    }

    public void addRegisteredUser(User user) {
        this.userRegistered.put(user.getUserName(),user);
    }

    synchronized public void remove(Todo todo){
        todos.remove(todo);
    }

    synchronized public void setStatus(Todo todo){
        todo.setStatus((parseInt(todo.getStatus()) + 1) + "");
    }

    synchronized public void updateTodo(Todo todo){
        updatedTodos.put(new Date(),todo);
    }

    public boolean checkLegitChange(Date date,long todoId){
        for (Map.Entry<Date, Todo > entry : updatedTodos.entrySet())
        {
            if(entry.getKey().after(date) && entry.getValue().getTodoId().equals(todoId)) {
                return false;
            }
        }
        return true;
    }

    public List<Todo> getForAUser(Date date){
        List<Todo> list = new ArrayList<Todo>();
        for (Map.Entry<Date, Todo > entry : updatedTodos.entrySet())
        {
            System.out.println("Change in date ==  " + entry.getKey().toString() + date);
            if(entry.getKey().after(date)){
                list.add(entry.getValue());
            }
        }
        return list;
    }
}