package com.vsu.servlet.machine;

import com.vsu.entity.Car;
import com.vsu.entity.User;
import com.vsu.repository.CarRepo;
import com.vsu.repository.ConnectionFactory;
import com.vsu.service.CarService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/car/insert")
public class InsertCar extends HttpServlet {
    private static final String JSP_PATH = "/jsp/";
    private CarService carService;

    @Override
    public void init(ServletConfig config) {
        carService = new CarService(new CarRepo(new ConnectionFactory()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String plate = req.getParameter("plate");
        User user = (User) session.getAttribute("user");
        try {
            carService.insertCar(new Car(brand, model, plate, user.getId()));
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/user/page");
            req.setAttribute("error", e.toString());
            dispatcher.forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/user/page");
    }
}
