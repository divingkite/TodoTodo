package utils;

import database.Store;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCheck {
    private static Store store = Store.getInstance();

    public static boolean check(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cookie[]  cookie = request.getCookies();
        if(cookie == null ){
            return false;
        }
        for (Cookie cur : cookie) {
            if (cur.getName().equals("sessionId")) {
                if (store.getLoginMap().containsKey(cur.getValue()))
                    return true;
            }
        }
        return false;
    }
}
