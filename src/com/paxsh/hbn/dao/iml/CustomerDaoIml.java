package com.paxsh.hbn.dao.iml;

import com.paxsh.hbn.bean.Customer;
import com.paxsh.hbn.dao.CustomerDao;
import com.paxsh.hbn.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

/**
 * 操作客户数据库
 */
public class CustomerDaoIml implements CustomerDao {

    @Override
    public void save(Customer c) {
        // 获得session
        Session session = HibernateUtils.getCurrentSession();
        // 执行保存
        session.save(c);
    }

    @Override
    public List<Customer> getAll() {
        Session session = HibernateUtils.getCurrentSession();
        //2 创建Criteria对象
        Criteria criteria = session.createCriteria(Customer.class);
        return criteria.list();
    }

    @Override
    public Customer getById(Long cust_id) {
        Session session = HibernateUtils.getCurrentSession();
        return session.get(Customer.class, cust_id);
    }
}
