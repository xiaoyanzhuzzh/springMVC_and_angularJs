package com.tw.core.dao;

import com.tw.core.entity.Admin;
import com.tw.core.helper.EncryptionHelper;
import com.tw.core.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDao {

    public Boolean verifyAdminInfo(String name, String password) {
        Boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "from Admin where name=? and password=?";
        Query query = session.createQuery(hql);

        query.setString(0, name);
        query.setString(1, EncryptionHelper.md5(password));

        List<Admin> admins = query.list();
        if(admins.size() == 1) {
            result = true;
        }
        session.close();

        return result;
    }

    public void updateAdmin(Admin admin) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.update(admin);
        session.getTransaction().commit();
        session.close();
    }

    public static void main(String[] args) {

        Admin admin = new Admin(1, "张志慧",  EncryptionHelper.md5("123456"));
        new AdminDao().updateAdmin(admin);
    }

}
