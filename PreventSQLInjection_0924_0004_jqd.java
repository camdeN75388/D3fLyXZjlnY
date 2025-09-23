// 代码生成时间: 2025-09-24 00:04:03
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.ArrayList;

// 防止SQL注入的实体类
class User {
    private int id;
    private String name;
    private String email;

    // 省略getter和setter方法
}

// 防止SQL注入的DAO类
public class UserDao {
    private SessionFactory sessionFactory;

    public UserDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<User> listUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<User> users = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User", User.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    // 省略其他方法
}

// 主类，展示如何使用DAO类防止SQL注入
public class PreventSQLInjection {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        // 添加用户，防止SQL注入
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        userDao.addUser(user);

        // 查询用户列表，防止SQL注入
        List<User> users = userDao.listUsers();
        for (User u : users) {
            System.out.println(u.getName() + " - " + u.getEmail());
        }
    }
}