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

@WebServlet("/car/update")
public class UpdateCar  extends HttpServlet {
    private static final String JSP_PATH = "/jsp/";
    private CarService carService;

    @Override
    public void init(ServletConfig config) {
        carService = new CarService(new CarRepo(new ConnectionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String plate = req.getParameter("plate");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Car car = new Car(id, brand, model, plate, user.getId());

        try {
            carService.updateByID(car);
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_PATH + "car-form.jsp");
            req.setAttribute("error", e.toString());
            dispatcher.forward(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/car/show");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

