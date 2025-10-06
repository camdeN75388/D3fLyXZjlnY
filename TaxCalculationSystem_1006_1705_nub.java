// 代码生成时间: 2025-10-06 17:05:34
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Properties;

// 税务实体类
class TaxEntity {
    private int id;
    private double income;
    private double tax;

    // 构造函数、getter和setter省略...
}

// 税务计算服务接口
interface TaxCalculationService {
    double calculateTax(double income);
}

// 税务计算服务实现类
class StandardTaxCalculationService implements TaxCalculationService {
    @Override
    public double calculateTax(double income) {
        // 这里添加税务计算逻辑
        // 示例：简单起见，此处仅按固定税率10%计算
        return income * 0.10;
    }
}

// Hibernate配置和会话工厂
class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            // 创建一个配置对象
            Configuration configuration = new Configuration().configure();
            // 创建服务注册对象
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            // 创建会话工厂
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            // 错误处理
            System.err.println(