package repository.custom.impl;

import entity.CustomerEntity;
import entity.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.CustomerDao;
import util.HibernateUtil;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity customerEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(customerEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(CustomerEntity customerEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(customerEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Integer id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            CustomerEntity customer = session.get(CustomerEntity.class, id);
            if (customer != null) {
                session.remove(customer);
                transaction.commit();
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public CustomerEntity search(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            return session.get(CustomerEntity.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public List<CustomerEntity> getAll() {
        Session session = HibernateUtil.getSession();
        List<CustomerEntity> customers = null;
        try {
            session.beginTransaction();
            customers = session.createQuery("from CustomerEntity", CustomerEntity.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return customers;
    }
}
