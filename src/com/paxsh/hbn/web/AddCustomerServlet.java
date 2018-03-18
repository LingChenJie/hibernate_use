package com.paxsh.hbn.web;

import com.paxsh.hbn.bean.Customer;
import com.paxsh.hbn.service.CustomerService;
import com.paxsh.hbn.service.iml.CustomerServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 添加客户
 */
public class AddCustomerServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获得参数并封装到Customer对象
        Customer c = new Customer();
        try {
            BeanUtils.populate(c, req.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        customerService.save(c);
        //2 调用Service保存客户
        resp.sendRedirect(req.getContextPath() + "/listCustomer");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
