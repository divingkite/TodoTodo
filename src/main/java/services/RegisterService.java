package services;

import database.Store;
import model.User;
import utils.ErrorMessages;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RegisterService {

    public static void serviceMethod(HttpServletRequest request,
                                     HttpServletResponse response) throws IOException, ServletException {
        Store store = Store.getInstance();
        String username = request.getParameter("registerName");
        String password = request.getParameter("registerPassword");
        String phone = request.getParameter("registerPhone");
        String emailId = request.getParameter("registerEmail");

        if( !store.getUserRegistered().containsKey(username) ){

            User user = new User(username,password,emailId,phone);
            String token = new BigInteger(100, new SecureRandom()).toString(32);
            store.addLogin(token,user);
            store.addRegisteredUser(user);
            response.addCookie(new Cookie("sessionId",token));
            response.sendRedirect( "/TodoTodo/home.html");
        }
        else{
            new ErrorMessages().alreadyRegistered(response);
        }
    }
}
