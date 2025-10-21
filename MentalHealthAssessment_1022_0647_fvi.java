// 代码生成时间: 2025-10-22 06:47:32
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

// 定义心理健康评估实体类
class MentalHealthAssessment {
    private int id;
    private String patientName;
    private double score;

    // 构造方法、getter和setter省略...

    // 评估方法
    public double evaluate() {
        // 假设评估逻辑
        if (score > 90) {
            return 1.0; // 正常
        } else if (score > 70) {
            return 0.5; // 轻度
        } else {
            return 0.0; // 中度或严重
        }
    }
}

public class MentalHealthAssessmentService {
    public static void main(String[] args) {
        // 加载配置文件
        Configuration configuration = new Configuration().configure();
        // 创建SessionFactory
        org.hibernate.SessionFactory sessionFactory = configuration.buildSessionFactory();
        // 创建Session
        try (Session session = sessionFactory.openSession()) {
            // 开启事务
            Transaction transaction = session.beginTransaction();
            try {
                // 模拟输入数据
                Scanner scanner = new Scanner(System.in);
                System.out.println("请输入患者姓名：");
                String patientName = scanner.nextLine();
                System.out.println("请输入评估分数：");
                double score = scanner.nextDouble();

                // 创建心理健康评估对象
                MentalHealthAssessment assessment = new MentalHealthAssessment();
                assessment.setPatientName(patientName);
                assessment.setScore(score);

                // 保存评估结果
                session.save(assessment);
                transaction.commit();
                System.out.println("评估成功！");

                // 查询并显示评估结果
                List<MentalHealthAssessment> assessments = session.createQuery("from MentalHealthAssessment", MentalHealthAssessment.class).list();
                for (MentalHealthAssessment a : assessments) {
                    System.out.println("患者姓名：" + a.getPatientName() + ", 评估分数：" + a.getScore() + ", 评估结果：" + a.evaluate());
                }
            } catch (Exception e) {
                // 错误处理
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
        } finally {
            // 关闭SessionFactory
            sessionFactory.close();
        }
    }
}