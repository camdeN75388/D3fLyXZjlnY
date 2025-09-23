// 代码生成时间: 2025-09-23 17:19:36
// 用户权限管理系统

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.ArrayList;

// 用户实体类
class User {
    private int id;
    private String username;
    private String password;
    private List<Role> roles = new ArrayList<>();

    // 构造器、getter和setter省略

    public void addRole(Role role) {
        roles.add(role);
    }
}

// 角色实体类
class Role {
    private int id;
    private String rolename;

    // 构造器、getter和setter省略
}

// 权限实体类
class Permission {
    private int id;
    private String permissionName;

    // 构造器、getter和setter省略
}

// 权限管理服务类
class PermissionManagementService {

    private SessionFactory sessionFactory;

    public PermissionManagementService() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addUserWithRole(int userId, String username, String password, String rolename) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = new User();
            user.setId(userId);
            user.setUsername(username);
            user.setPassword(password);

            Role role = session.get(Role.class, rolename);
            if (role == null) {
                throw new Exception("Role not found");
            }
            user.addRole(role);

            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void grantPermissionToUser(int userId, String permissionName) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = session.get(User.class, userId);
            if (user == null) {
                throw new Exception("User not found");
            }

            Permission permission = new Permission();
            permission.setId(1); // Assuming permission ID is 1 for simplicity
            permission.setPermissionName(permissionName);

            // Logic to grant permission to user is omitted

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 更多方法和错误处理省略
}

// 主类
public class UserPermissionManagement {
    public static void main(String[] args) {
        PermissionManagementService service = new PermissionManagementService();
        try {
            // 添加用户并分配角色
            service.addUserWithRole(1, "admin", "password123", "ADMIN");

            // 给用户授权
            service.grantPermissionToUser(1, "READ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
