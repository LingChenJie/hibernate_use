package com.paxsh.hbn.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate操作
 */
public class HibernateUtils {

    private static SessionFactory sf;

    static {
        // 1 创建,调用空参构造
        Configuration conf = new Configuration().configure();
        //2 根据配置信息,创建 SessionFactory对象
        sf = conf.buildSessionFactory();
    }

    /**
     * 获得session => 获得全新session
     *
     * @return
     */
    public static Session openSession() {
        Session session = sf.openSession();
        return session;
    }

    /**
     * 获取session => 获得与线程绑定的session
     *
     * @return
     */
    public static Session getCurrentSession() {
        Session session = sf.getCurrentSession();
        return session;
    }
}
