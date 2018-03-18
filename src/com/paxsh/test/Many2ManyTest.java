package com.paxsh.test;

import com.paxsh.hbn.bean.Role;
import com.paxsh.hbn.bean.User;
import com.paxsh.hbn.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class Many2ManyTest {

    //保存员工以及角色
    @Test
    public void fun1() {
        Session session = HibernateUtils.openSession();

        Transaction tx = session.beginTransaction();

        //-------
        //1> 创建两个 User
        User u1 = new User();
        u1.setUser_name("杰辰");

        User u2 = new User();
        u2.setUser_name("杰辰2");

        //2> 创建两个 Role
        Role r1 = new Role();
        r1.setRole_name("小明");

        Role r2 = new Role();
        r2.setRole_name("小张");

        //3> 用户表达关系
        u1.getRoles().add(r1);
        u1.getRoles().add(r2);

        u2.getRoles().add(r1);
        u2.getRoles().add(r2);

        //4> 角色表达关系
//        r1.getUsers().add(u1);  由user进行维护关系，级联操作
//        r1.getUsers().add(u2);
//
//        r2.getUsers().add(u1);
//        r2.getUsers().add(u2);

        //5> 调用Save方法一次保存
        session.save(u1);
        session.save(u2);
//        session.save(r1);
//        session.save(r2);
        //---------

        tx.commit();
        session.close();
    }

    // 为郝强勇新增一个角色
    @Test
    public void fun2(){
        Session session = HibernateUtils.openSession();

        Transaction tx = session.beginTransaction();

        //---------
        User user = session.get(User.class, 1l);

        Role r = new Role();
        r.setRole_name("小猪");

        user.getRoles().add(r);
        //--------

        tx.commit();
        session.close();
    }

    // 解除一个角色
    @Test
    public void fun3(){
        Session session = HibernateUtils.openSession();

        Transaction tx = session.beginTransaction();

        //----------
        User user = session.get(User.class, 1l);

        Role r1 = session.get(Role.class, 1l);
        Role r2 = session.get(Role.class, 2l);

        user.getRoles().remove(r1);
        user.getRoles().remove(r2);
        //----------

        tx.commit();
        session.close();
    }
}
