package com.paxsh.hbn.service.iml;

import com.paxsh.hbn.bean.Customer;
import com.paxsh.hbn.dao.CustomerDao;
import com.paxsh.hbn.dao.iml.CustomerDaoIml;
import com.paxsh.hbn.service.CustomerService;
import com.paxsh.hbn.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao = new CustomerDaoIml();

    @Override
    public void save(Customer c) {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        // 调用Dao保存客户
        customerDao.save(c);
        transaction.commit();
    }

    @Override
    public List<Customer> getAll() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Customer> list = customerDao.getAll();
        transaction.commit();
        return list;
    }
}
