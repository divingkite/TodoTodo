package services;

import database.Store;
import model.Constants;
import model.Todo;
import utils.ErrorMessages;
import utils.LoginCheck;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Long.parseLong;

public class StatusTransferService {

    private static Store store = Store.getInstance();

    public static void serviceMethod(HttpServletRequest request,
                                     HttpServletResponse response) throws IOException, ServletException {
        if(!LoginCheck.check(request,response)){
            response.sendRedirect("/TodoTodo");
        }


        Todo todo = store.getTodo(parseLong(request.getParameter("todoId")));
        String reqStatus = request.getParameter("status");
        ErrorMessages messageGenerator = new ErrorMessages();

        boolean exists = (todo != null);
        if(!exists){
            messageGenerator.notExist(response);
            return;
        }

        Constants status = todo.getStatus();
        if(status.equals(reqStatus)){
            if (status.equals(Constants.COMPLETED)) {
                store.setStatus(todo);
                store.updateTodo(todo);
                store.remove(todo);
            }
            else if (status.equals(Constants.ASSIGNED)) {
                store.setStatus(todo);
                store.updateTodo(todo);
            }
        }
        else {
            if (todo.getStatus().equals(Constants.ASSIGNED)) {
                messageGenerator.alreadyAssigned(response);
                return;
            }
            if (todo.getStatus().equals(Constants.COMPLETED)) {
                messageGenerator.alreadyCompleted(response);
                return;
            }
        }
    }
}
