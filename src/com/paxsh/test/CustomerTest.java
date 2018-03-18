package com.paxsh.test;

import com.paxsh.hbn.bean.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

public class CustomerTest {

    @Test
    public void save() {
        // configure() 会读取src的 hibernate.cfg.xml 文件
        Configuration config = new Configuration().configure();

        // SessionFactory 用于创建 Session，它负责保存和使用所有配置信息，消耗内存比较大，尽量一个项目中只创建一个 SessionFactory
        SessionFactory sessionFactory = config.buildSessionFactory();

        // Session 表示 hibernate 框架与数据库之间的会话，类似于 jdbc 的 connection 对象
        Session session = sessionFactory.openSession();

        // 开启并获取事务
        Transaction transaction = session.beginTransaction();

        Customer c = new Customer();
        c.setCust_name("杰辰");

        // 执行保存
        session.save(c);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void find() {
        Configuration config = new Configuration().configure();

        SessionFactory sessionFactory = config.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, 1L);
        System.out.println(customer.toString());

        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void update() {
        Configuration config = new Configuration().configure();

        SessionFactory sessionFactory = config.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, 1L);
        customer.setCust_name("jiechen");
        session.update(customer);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void delete() {
        Configuration config = new Configuration().configure();

        SessionFactory sessionFactory = config.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, 1L);
        session.delete(customer);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }

}
