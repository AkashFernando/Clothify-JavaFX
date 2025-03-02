package repository.custom.impl;

import entity.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.EmployeeDao;
import util.HibernateUtil;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity employee){
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(employee);
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
    public boolean update(EmployeeEntity employeeEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(employeeEntity);
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

            EmployeeEntity employee = session.get(EmployeeEntity.class, id);
            if (employee != null) {
                session.remove(employee);
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
    public EmployeeEntity search(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            return session.get(EmployeeEntity.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public List<EmployeeEntity> getAll() {
        Session session = HibernateUtil.getSession();
        List<EmployeeEntity> employees = null;
        try {
            session.beginTransaction();
            employees = session.createQuery("from EmployeeEntity", EmployeeEntity.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

    @Override
    public List<EmployeeEntity> getAllAdmins() {
        Session session = HibernateUtil.getSession();
        List<EmployeeEntity> employees = null;
        try {
            session.beginTransaction();
            employees = session.createQuery("from EmployeeEntity e where e.role = :role", EmployeeEntity.class)
                    .setParameter("role", "admin")
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

}
