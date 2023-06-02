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
import java.util.List;

@WebServlet("/car/show")
public class ShowAllCars extends HttpServlet {
    public static final String JSP_PATH = "/jsp/";
    private CarService carService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        carService = new CarService(new CarRepo(new ConnectionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Car> carList = carService.selectAllByUserId(user.getId());
        RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_PATH + "cars.jsp");;
        req.setAttribute("cars", carList);
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
