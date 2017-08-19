package services;

import utils.LoginCheck;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutService {
    public static void serviceMethod(HttpServletRequest request,
                                     HttpServletResponse response) throws IOException, ServletException {

        if(LoginCheck.check(request,response))
        {
            Cookie cur = new Cookie("sessionId",null);
            cur.setMaxAge(0);
            response.addCookie(cur);
            response.sendRedirect( "/TodoTodo");
            return;
        }
        response.sendRedirect( "/TodoTodo");
    }
}
