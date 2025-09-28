// 代码生成时间: 2025-09-29 00:02:46
package com.miningpool;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.ArrayList;
import java.util.List;

/**
 * 挖矿池管理
 * @author <Your Name>
 */
public class MiningPoolManagement {

    private static SessionFactory sessionFactory;

    /**
     * 初始化SessionFactory
     */
    public static void initializeSessionFactory() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    /**
     * 关闭SessionFactory
     */
    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    /**
     * 添加挖矿池
     * @param miningPool 挖矿池对象
     */
    public void addMiningPool(MiningPool miningPool) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(miningPool);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 获取所有挖矿池信息
     * @return 挖矿池对象列表
     */
    public List<MiningPool> getAllMiningPools() {
        List<MiningPool> miningPools = new ArrayList<>();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            miningPools = session.createQuery("from MiningPool", MiningPool.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return miningPools;
    }

    /**
     * 更新挖矿池信息
     * @param miningPool 挖矿池对象
     */
    public void updateMiningPool(MiningPool miningPool) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(miningPool);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 删除挖矿池
     * @param id 挖矿池ID
     */
    public void removeMiningPool(int id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            MiningPool miningPool = session.get(MiningPool.class, id);
            if (miningPool != null) {
                session.delete(miningPool);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

/**
 * 挖矿池实体类
 */
class MiningPool {
    private int id;
    private String name;
    private double capacity;
    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getCapacity() {
        return capacity;
    }
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
}