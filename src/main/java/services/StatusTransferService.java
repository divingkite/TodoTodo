package services;

import database.Store;
import model.Todo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static java.lang.Long.parseLong;
import static services.MainService.checkLogin;

public class StatusTransferService {

    private static Store store = Store.getInstance();

    public static void serviceMethod(HttpServletRequest request,
                                     HttpServletResponse response) throws IOException, ServletException {
        if(!checkLogin(request,response)){
            response.sendRedirect("/TodoTodo");
        }


        Todo todo = store.getTodo(parseLong(request.getParameter("todoId")));
        String reqStatus = request.getParameter("status");
        String reqDate = request.getParameter("date");
        Date date = new DateParser().parseDate(request);
        ErrorMessages messageGenerator = new ErrorMessages();

        boolean exists = (store.getTodo(parseLong(request.getParameter("todoId"))) != null);
        if(!exists){
            messageGenerator.notExist(response);
            return;
        }
        String status = todo.getStatus();
        if(status.equals(reqStatus)){
            if (status.equals("2")) {
                store.setStatus(todo);
                store.updateTodo(todo);
                store.remove(todo);
            }
            else if (status.equals("1")) {
                store.setStatus(todo);
                store.updateTodo(todo);
            }
        }
        else {
            if (todo.getStatus().equals("1")) {
                messageGenerator.alreadyAssigned(response);
                return;
            }
            if (todo.getStatus().equals("2")) {
                messageGenerator.alreadyCompleted(response);
                return;
            }
        }
    }
}
