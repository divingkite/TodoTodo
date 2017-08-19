package utils;

import database.Store;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFromCookie {
    private static Store store = Store.getInstance();

    public static User getUserFromSessionId(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Cookie[]  cookie = request.getCookies();

        for (Cookie cur : cookie) {
            if (cur.getName().equals("sessionId")) {
                if (store.getLoginMap().containsKey(cur.getValue()))
                    return store.getLoginMap().get(cur.getValue());
            }
        }
        return null;
    }
}
