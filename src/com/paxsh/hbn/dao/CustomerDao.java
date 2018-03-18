package com.paxsh.hbn.dao;

import com.paxsh.hbn.bean.Customer;

import java.util.List;

public interface CustomerDao {

    /**
     * 保存客户
     *
     * @param c
     */
    void save(Customer c);

    /**
     * 查询所有客户
     *
     * @return
     */
    List<Customer> getAll();

    /**
     * 根据id获得客户
     *
     * @param cust_id
     * @return
     */
    Customer getById(Long cust_id);
}
