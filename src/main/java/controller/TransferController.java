package controller;

import database.Store;
import services.StatusTransferService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="TransferController",urlPatterns={
        "/transfer" //Post
})
public class TransferController extends HttpServlet {

    Store store = Store.getInstance();
    @Override
    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        StatusTransferService.serviceMethod(request,response);
    }
}