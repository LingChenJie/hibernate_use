package com.paxsh.hbn.service.iml;

import com.paxsh.hbn.bean.Customer;
import com.paxsh.hbn.bean.LinkMan;
import com.paxsh.hbn.dao.CustomerDao;
import com.paxsh.hbn.dao.LinkManDao;
import com.paxsh.hbn.dao.iml.CustomerDaoIml;
import com.paxsh.hbn.dao.iml.LinkManDaoIml;
import com.paxsh.hbn.service.LinkManService;
import com.paxsh.hbn.utils.HibernateUtils;

public class LinkManServiceIml implements LinkManService {

    private CustomerDao cd = new CustomerDaoIml();
    private LinkManDao lmd = new LinkManDaoIml();

    @Override
    public void save(LinkMan lm) {
        // 打开事务
        HibernateUtils.getCurrentSession().beginTransaction();

        try {
            // 根据客户id获得客户对象
            Long cust_id = lm.getCust_id();
            Customer c = cd.getById(cust_id);
            // 将客户放入LinkMan中表达关系
            lm.setCustomer(c);
            // 保存LinkMan
            lmd.save(lm);
        } catch (Exception e) {
            e.printStackTrace();
            // 事务回滚
            HibernateUtils.getCurrentSession().getTransaction().rollback();
        }
        // 提交事务
        HibernateUtils.getCurrentSession().getTransaction().commit();
    }
}
