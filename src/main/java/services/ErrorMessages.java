package services;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorMessages {

    public void alreadyCompleted(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        String message = "";
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setHeader("Content-Type", "text/plain");
        message = "Someone has already completed it";
        writer.print(message);
        writer.flush();
        writer.close();
    }

    public void alreadyAssigned(HttpServletResponse response) throws IOException{
        PrintWriter writer = response.getWriter();
        String message = "";
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setHeader("Content-Type", "text/plain");
        message = "Someone has already assigned it";
        writer.print(message);
        writer.flush();
        writer.close();
    }

    public void notExist(HttpServletResponse response) throws IOException{
        PrintWriter writer = response.getWriter();
        String message = "";
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setHeader("Content-Type", "text/plain");
        message = "Someone has already deleted it";
        writer.print(message);
        writer.flush();
        writer.close();
    }
    public void wrongPassword(HttpServletResponse response) throws IOException{
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        writer.write("<a href=\"/TodoTodo\">Incorrect Password</a>");
        writer.flush();
        writer.close();
    }
    public void notRegistered(HttpServletResponse response) throws IOException{
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        writer.print("<a href=\"/TodoTodo\">Register before login</a>");
        writer.flush();
        writer.close();
    }
    public void alreadyRegistered(HttpServletResponse response) throws IOException{
        PrintWriter writer = response.getWriter();
        response.setHeader("Content-Type", "text/html");
        writer.write("<a href=\"/TodoTodo\">Already Register. Enter new username or login</a>");
        writer.flush();
        writer.close();
    }
    public void notLoggedIn(HttpServletResponse response) throws IOException{
        PrintWriter writer = response.getWriter();
        response.setHeader("Content-Type", "text/html");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        writer.write("<a href=\"/TodoTodo\">Login/Register before.</a>");
        writer.flush();
        writer.close();
    }
}
