// 代码生成时间: 2025-10-05 01:57:23
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.spi.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.selector.spi.StrategySelector;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Properties;
import java.util.Map;

public class ServiceDiscoveryAndRegistration {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    static {
        try {
            // 创建配置并加载hibernate.cfg.xml文件
            Configuration configuration = new Configuration().configure();

            // 创建服务注册器构建器
            ServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

            // 服务发现和注册
            serviceRegistry = serviceRegistryBuilder.build();

            // 通过服务注册器和配置创建session工厂
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            // 记录日志，处理异常
            System.err.println("Initial SessionFactory creation failed." + "
" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session openSession() throws HibernateException {
        // 打开一个新的Session
        return sessionFactory.openSession();
    }

    public static void close() {
        // 关闭session工厂，清理资源
        if (sessionFactory != null) {
            sessionFactory.close();
        }
        if (serviceRegistry != null) {
            serviceRegistry.destroy();
        }
    }

    public static void main(String[] args) {
        try {
            // 打开session
            Session session = openSession();
            try {
                // 开启事务
                Transaction tx = session.beginTransaction();
                
                // 这里可以添加业务逻辑，例如：注册服务
                // ...
                
                // 提交事务
                tx.commit();
            } catch (HibernateException e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
                e.printStackTrace();
            } finally {
                // 关闭session
                session.close();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
