package com.paxsh.test;

import com.paxsh.hbn.bean.Customer;
import com.paxsh.hbn.bean.LinkMan;
import com.paxsh.hbn.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class One2ManyTest {


    // 保存客户 以及客户 下的联系人
    @Test
    public void saveCustomer2LinkMan() {
        Configuration config = new Configuration().configure();

        SessionFactory sessionFactory = config.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        // ---------
        Customer customer = new Customer();
        customer.setCust_name("杰辰");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkm_name("小明");
        LinkMan linkMan2 = new LinkMan();
        linkMan2.setLkm_name("小张");

        // 表达一对多，一个客户有多个联系人
//        customer.getLinkMans().add(linkMan); 由LinkMan维护关系，级联保存
//        customer.getLinkMans().add(linkMan2);

        // 表达多对一，联系人属于哪个客户
        linkMan.setCustomer(customer);
        linkMan2.setCustomer(customer);

//        session.save(customer); 由LinkMan维护关系，级联保存
        session.save(linkMan);
        session.save(linkMan2);
        // ---------

        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    // 为客户增加联系人
    @Test
    public void addCustomerLinkMan() {
        Session session = HibernateUtils.openSession();

        Transaction tx = session.beginTransaction();

        //--------
        Customer c = session.get(Customer.class, 2l);

        LinkMan lm1 = new LinkMan();
        lm1.setLkm_name("杰辰2");

        c.getLinkMans().add(lm1);
        lm1.setCustomer(c);

        session.save(lm1);
        //--------

        tx.commit();
        session.close();
    }

    // 为客户删除联系人
    @Test
    public void deleteCustomerLinkMan() {
        Session session = HibernateUtils.openSession();

        Transaction tx = session.beginTransaction();

        //--------
        Customer c = session.get(Customer.class, 3l);

        LinkMan lm = session.get(LinkMan.class, 5l);

        c.getLinkMans().remove(lm);
        lm.setCustomer(null);
        //--------

        tx.commit();
        session.close();
    }

    // 删除客户
    @Test
    public void deleteCustomer() {
        Session session = HibernateUtils.openSession();

        Transaction tx = session.beginTransaction();

        //-------
        Customer customer = session.get(Customer.class, 4l);

        session.delete(customer);
        //--------

        tx.commit();
        session.close();
    }
}
