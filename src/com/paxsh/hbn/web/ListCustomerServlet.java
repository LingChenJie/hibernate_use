package com.paxsh.hbn.web;

import com.paxsh.hbn.bean.Customer;
import com.paxsh.hbn.service.CustomerService;
import com.paxsh.hbn.service.iml.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListCustomerServlet extends HttpServlet {

    private CustomerService cs = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> all = cs.getAll();
        req.setAttribute("list", all);
        req.getRequestDispatcher("/jsp/customer/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
