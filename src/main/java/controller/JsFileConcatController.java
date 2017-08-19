package controller;

import services.JsFileConcatService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="JsFileConcatController",urlPatterns={
        "/joinjs" //Get
})
public class JsFileConcatController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        JsFileConcatService.serviceMethod(request, response);
    }
}
