// 代码生成时间: 2025-10-14 20:50:36
package com.skillcertification.platform;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.ArrayList;

// 实体类 Skill
public class Skill {
    private int id;
    private String name;

    public Skill() {}

    public Skill(String name) {
        this.name = name;
    }

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
}

// Skill 的 DAO 类
public class SkillDAO {
    private SessionFactory sessionFactory;

    public SkillDAO() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addSkill(Skill skill) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(skill);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Skill> getAllSkills() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Skill").list();
        } finally {
            session.close();
        }
    }
}

// 服务类
public class SkillService {
    private SkillDAO skillDAO;

    public SkillService() {
        this.skillDAO = new SkillDAO();
    }

    public void addSkill(String skillName) {
        Skill skill = new Skill(skillName);
        skillDAO.addSkill(skill);
    }

    public List<Skill> getAllSkills() {
        return skillDAO.getAllSkills();
    }
}

// 主应用程序类
public class MainApplication {
    public static void main(String[] args) {
        SkillService skillService = new SkillService();

        // 添加技能
        skillService.addSkill("Java Programming");
        skillService.addSkill("Database Management");

        // 获取所有技能
        List<Skill> skills = skillService.getAllSkills();
        for (Skill skill : skills) {
            System.out.println(skill.getName());
        }
    }
}