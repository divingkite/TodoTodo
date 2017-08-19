package utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorMessages {

    // code repitions

    private void helper(HttpServletResponse response,String message) throws IOException{
        PrintWriter writer = response.getWriter();
        writer.print(message);
        writer.flush();
        writer.close();
    }

    public void alreadyCompleted(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setHeader("Content-Type", "text/plain");
        helper(response,"Someone has already completed it");
    }

    public void alreadyAssigned(HttpServletResponse response) throws IOException{

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setHeader("Content-Type", "text/plain");
        helper(response,"Someone has already assigned it");
    }

    public void notExist(HttpServletResponse response) throws IOException{

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setHeader("Content-Type", "text/plain");
        helper(response,"Someone has already deleted it");
    }
    public void wrongPassword(HttpServletResponse response) throws IOException{

        response.setContentType("text/html");
        helper(response,"<a href=\"/TodoTodo\">Incorrect Password</a>");
    }
    public void notRegistered(HttpServletResponse response) throws IOException{

        response.setContentType("text/html");
        helper(response,"<a href=\"/TodoTodo\">Register before login</a>");
    }
    public void alreadyRegistered(HttpServletResponse response) throws IOException{

        response.setHeader("Content-Type", "text/html");
        helper(response,"<a href=\"/TodoTodo\">Already Register. Enter new username or login</a>");
    }
    public void notLoggedIn(HttpServletResponse response) throws IOException{

        response.setHeader("Content-Type", "text/html");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        helper(response,"<a href=\"/TodoTodo\">Login/Register before.</a>");
    }



}
