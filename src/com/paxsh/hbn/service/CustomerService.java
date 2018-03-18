package com.paxsh.hbn.service;

import com.paxsh.hbn.bean.Customer;

import java.util.List;

public interface CustomerService {

    /**
     * 保存客户
     *
     * @param c
     */
    void save(Customer c);

    /**
     * 获得所有商户
     *
     * @return
     */
    List<Customer> getAll();
}