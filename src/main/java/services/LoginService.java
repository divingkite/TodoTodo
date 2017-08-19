package services;

import database.Store;
import utils.ErrorMessages;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

public class LoginService {

    public static void serviceMethod(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        Store store = Store.getInstance();
        String username = request.getParameter("loginUserName");
        String password = request.getParameter("loginPassword");

        if( store.getUserRegistered().containsKey(username) ){

            if(store.getUserRegistered().get(username).getPassword().equals(password)){

                String token = new BigInteger(100, new SecureRandom()).toString(32);
                store.addLogin(token,store.getUserRegistered().get(username));
                response.addCookie(new Cookie("sessionId",token));
                response.sendRedirect( "/TodoTodo/home.html");
            }
            else{
                new ErrorMessages().wrongPassword(response);
            }
        }
        else{
            new ErrorMessages().notRegistered(response);
        }

    }
}