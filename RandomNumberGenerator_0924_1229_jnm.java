// 代码生成时间: 2025-09-24 12:29:21
import java.util.Random;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RandomNumberGenerator {
    // Method to generate random number
    public static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt();
    }

    // Main method to run the program
    public static void main(String[] args) {
        try {
            // Create a new configuration and build the session factory
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            // Open a new session
            Session session = sessionFactory.openSession();
            // Begin a new transaction
            Transaction transaction = session.beginTransaction();
            
            // Generate random number
            int randomNumber = generateRandomNumber();
            System.out.println("Generated Random Number: " + randomNumber);

            // Commit the transaction
            transaction.commit();

            // Close the session and the session factory
            session.close();
            sessionFactory.close();
        } catch (Exception e) {
            // Handle any exceptions that occur
            e.printStackTrace();
        }
    }
}