package com.paxsh.hbn.web;

import com.paxsh.hbn.bean.LinkMan;
import com.paxsh.hbn.service.LinkManService;
import com.paxsh.hbn.service.iml.LinkManServiceIml;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 添加联系人
 */
public class AddLinkManServlet extends HttpServlet {

    private LinkManService lms = new LinkManServiceIml();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinkMan lm = new LinkMan();
        try {
            BeanUtils.populate(lm, req.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        lms.save(lm);
        resp.sendRedirect(req.getContextPath() + "/listLinkMan");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
