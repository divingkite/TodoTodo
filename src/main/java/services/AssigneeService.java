package services;

import database.Store;
import model.Constants;
import model.Todo;
import utils.DateParser;
import utils.ErrorMessages;
import utils.LoginCheck;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static java.lang.Long.parseLong;

public class AssigneeService {
    private static Store  store = Store.getInstance();

    public static void serviceMethod(HttpServletRequest request,
                                     HttpServletResponse response) throws IOException, ServletException {
        if(!LoginCheck.check(request,response)){
            response.sendRedirect("/TodoTodo");
        }

        Todo todo = store.getTodo(parseLong(request.getParameter("todoId")));
        Date date = new DateParser().parseDate(request);

        boolean exists = store.getTodo(parseLong(request.getParameter("todoId"))) != null;
        ErrorMessages messageGenerator = new ErrorMessages();


        if(!exists){
            messageGenerator.notExist(response);
            return;
        }
        Constants status = todo.getStatus();
        if( status.equals(Constants.ASSIGNED)){
            messageGenerator.alreadyAssigned(response);
            return;
        }
        if( status.equals(Constants.COMPLETED)){
            messageGenerator.alreadyCompleted(response);
            return;
        }

        todo.setAssigned(request.getParameter("assignedTo"));
        todo.setStatus(Constants.ASSIGNED);
        store.updateTodo(todo);
    }
}
