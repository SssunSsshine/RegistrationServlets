package com.vsu.servlet.user;

import com.vsu.entity.User;
import com.vsu.repository.ConnectionFactory;
import com.vsu.repository.UserRepo;
import com.vsu.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/update")
public class UpdateUser extends HttpServlet {
    private static final String JSP_PATH = "/jsp/";
    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = new UserService(new UserRepo(new ConnectionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String birthday = req.getParameter("birthday");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        User user = new User(id, surname, name, birthday, email, phone, password);
        try {
            userService.updateByID(user);
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_PATH + "user-form.jsp");
            req.setAttribute("error", e.toString());
            dispatcher.forward(req, resp);
            return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_PATH + "user-page.jsp");
        req.setAttribute("user", user);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
