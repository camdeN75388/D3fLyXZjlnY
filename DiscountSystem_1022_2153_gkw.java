// 代码生成时间: 2025-10-22 21:53:58
package com.example.discountsystem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.UUID;

/**
 * 折扣优惠系统
 *
 * @author ExampleAuthor
 */
public class DiscountSystem {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // 创建一个SessionFactory对象
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // 日志记录异常，这里使用System.err.println作为简单示例
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void shutDown() {
        // 关闭SessionFactory
        sessionFactory.close();
    }

    /**
     * 添加折扣优惠
     *
     * @param discount 折扣优惠对象
     * @return 成功返回true，失败返回false
     */
    public boolean addDiscount(Discount discount) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(discount);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            }
        }
    }

    /**
     * 获取所有折扣优惠
     *
     * @return 折扣优惠列表
     */
    public List<Discount> getAllDiscounts() {
        List<Discount> discounts = null;
        try (Session session = sessionFactory.openSession()) {
            discounts = session.createQuery("from Discount", Discount.class).list();
        }
        return discounts;
    }

    /**
     * 删除折扣优惠
     *
     * @param id 折扣优惠ID
     * @return 成功返回true，失败返回false
     */
    public boolean removeDiscount(String id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Discount discount = session.get(Discount.class, id);
                if (discount != null) {
                    session.delete(discount);
                    transaction.commit();
                    return true;
                }
                transaction.rollback();
                return false;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            }
        }
    }
}

/**
 * 折扣优惠实体类
 *
 * @author ExampleAuthor
 */
class Discount {
    private String id;
    private String productName;
    private double discountValue;

    public Discount() {
        this.id = UUID.randomUUID().toString();
    }

    public Discount(String productName, double discountValue) {
        this();
        this.productName = productName;
        this.discountValue = discountValue;
    }

    // Getters and Setters
    // ...
}
