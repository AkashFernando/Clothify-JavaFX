package repository.custom.impl;

import entity.EmployeeEntity;
import entity.ItemEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.ItemDao;
import util.HibernateUtil;

import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(ItemEntity itemEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(itemEntity);
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
    public boolean update(ItemEntity itemEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(itemEntity);
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

            ItemEntity itemEntity = session.get(ItemEntity.class, id);
            if (itemEntity != null) {
                session.remove(itemEntity);
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
    public ItemEntity search(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            return session.get(ItemEntity.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public List<ItemEntity> getAll() {
        Session session = HibernateUtil.getSession();
        List<ItemEntity> items = null;
        try {
            session.beginTransaction();
            items = session.createQuery("from ItemEntity", ItemEntity.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return items;
    }
}
