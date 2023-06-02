package com.vsu.servlet.machine;

import com.vsu.entity.Car;
import com.vsu.repository.CarRepo;
import com.vsu.repository.ConnectionFactory;
import com.vsu.service.CarService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/car/edit")
public class EditCar extends HttpServlet {
    private static final String JSP_PATH = "/jsp/";
    private CarService carService;

    @Override
    public void init(ServletConfig config) {
        carService = new CarService(new CarRepo(new ConnectionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        Car existingCar;
        try {
            existingCar = carService.selectById(id);
            RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_PATH + "car-form.jsp");
            req.setAttribute("car", existingCar);
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}