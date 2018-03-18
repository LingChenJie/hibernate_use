package com.paxsh.hbn.dao.iml;

import com.paxsh.hbn.bean.LinkMan;
import com.paxsh.hbn.dao.LinkManDao;
import com.paxsh.hbn.utils.HibernateUtils;
import org.hibernate.Session;

/**
 * 保存联系人
 */
public class LinkManDaoIml implements LinkManDao {

    @Override
    public void save(LinkMan lm) {
        Session session = HibernateUtils.getCurrentSession();
        session.save(lm);
    }
}
