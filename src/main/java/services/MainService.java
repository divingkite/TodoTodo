package services;

import database.Store;
import model.Todo;
import model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.ErrorMessages;
import utils.LoginCheck;
import utils.UserFromCookie;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainService {

    private static Store store = Store.getInstance();

    private static JSONObject jsonifyTodo(Todo todo) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name", todo.getName());
        json.put("description", todo.getDescription());
        json.put("date", todo.getDate().toString().substring(0,16));
        json.put("creator", todo.getCreator());
        json.put("assigned", todo.getAssigned());
        json.put("todoId", todo.getTodoId());
        json.put("status", todo.getStatus());
        return json;
    }

    public static void serviceMethodHome(HttpServletRequest request,
                                     HttpServletResponse response) throws IOException, ServletException {
        if (LoginCheck.check(request, response)) {

            String req_date = request.getParameter("date");
            List<Todo> todos=new ArrayList<Todo>();
            if(req_date.equals("undefined")){
                todos = store.getTodos();
            }
            else {
                DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);  //change the format

                try {
                    Date date = format.parse(req_date);
                    todos = store.getForAUser(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            PrintWriter writer = response.getWriter();

            response.setContentType("application/json");

            JSONArray todoArray = new JSONArray();

            for(Todo todo : todos){
                try {
                    todoArray.put(jsonifyTodo(todo));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            JSONObject totalList = new JSONObject();

            try {
                totalList.put("todos", todoArray);
                String date = new Date().toString();
                totalList.put("date",date);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            writer.print(totalList);
            writer.flush();
        } else {
            new ErrorMessages().notLoggedIn(response);
        }
    }
    public static void serviceMethodAddTask(HttpServletRequest request,
                                        HttpServletResponse response) throws IOException, ServletException {
        if(!LoginCheck.check(request,response)){
            response.sendRedirect("/TodoTodo");
        }
        String name= request.getParameter("title");
        String description= request.getParameter("description");
        User user = UserFromCookie.getUserFromSessionId(request,response);

        Todo todo = new Todo(name,description,user.getUserName());

        store.addTodos(todo);
        store.updateTodo(todo);
    }
}
