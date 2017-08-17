package services;

import database.Store;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutService {
    public static void serviceMethod(HttpServletRequest request,
                                     HttpServletResponse response) throws IOException, ServletException {

        Store store = Store.getInstance();
        Cookie[]  cookies = request.getCookies();

        for (Cookie cur : cookies) {
            if (cur.getName().equals("sessionId")) {
                store.getLoginMap().remove(cur.getValue());
                cur = new Cookie("sessionId",null);
                cur.setMaxAge(0);
                response.addCookie(cur);
                response.sendRedirect( "/TodoTodo");
                return;
            }
        }
        response.sendRedirect( "/TodoTodo");
    }
}
