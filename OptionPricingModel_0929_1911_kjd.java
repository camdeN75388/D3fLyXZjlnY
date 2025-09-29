// 代码生成时间: 2025-09-29 19:11:14
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Properties;

// OptionPricingModel.java - This class is designed to encapsulate the business logic for option pricing.

public class OptionPricingModel {


    // Fields to store option parameters

    private double underlyingPrice;

    private double strikePrice;

    private double riskFreeRate;

    private double volatility;

    private long timeToMaturity;



    // Constructor

    public OptionPricingModel(double underlyingPrice, double strikePrice, double riskFreeRate, double volatility, long timeToMaturity) {

        this.underlyingPrice = underlyingPrice;

        this.strikePrice = strikePrice;

        this.riskFreeRate = riskFreeRate;

        this.volatility = volatility;

        this.timeToMaturity = timeToMaturity;

    }



    // Method to calculate the option price using the Black-Scholes model

    public double calculateOptionPrice() {

        // Error handling for invalid input parameters

        if (underlyingPrice < 0 || strikePrice < 0 || riskFreeRate < 0 || volatility < 0 || volatility >= 1 || timeToMaturity < 0) {

            throw new IllegalArgumentException("Invalid option parameters.");

        }


        // Implementing the Black-Scholes formula

        double sqrtT = Math.sqrt(timeToMaturity);
        double d1 = (Math.log(underlyingPrice / strikePrice) + (riskFreeRate + volatility * volatility / 2) * timeToMaturity) / (volatility * sqrtT);
        double d2 = d1 - volatility * sqrtT;
        double callPrice = underlyingPrice * Math.exp(-riskFreeRate * timeToMaturity) * getCDF(d1) - strikePrice * Math.exp(-riskFreeRate * timeToMaturity) * getCDF(d2);
        return callPrice;
    }



    // Helper method to calculate the cumulative distribution function (CDF) for the standard normal distribution

    private double getCDF(double x) {

        // Implementation of the CDF for standard normal distribution

        double a1 = 0.319381530;
        double a2 = -0.356563782;
        double a3 = 1.781477937;
        double a4 = -1.821255978;
        double a5 = 1.330274429;
        double p = 0.231641964;
        double t = 1.0 / (1.0 + p * Math.abs(x));
        double cdf = 1 - p * (a1 * t - a2 * t * t + a3 * t * t * t - a4 * t * t * t * t + a5 * t * t * t * t * t);
        if (x >= 0) {
            return cdf;
        } else {
            return 1 - cdf;
        }
    }



    // Main method for testing

    public static void main(String[] args) {

        try {

            // Hibernate setup with H2 database

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();


            // Option parameters

            double underlyingPrice = 100.0;
            double strikePrice = 95.0;
            double riskFreeRate = 0.05;
            double volatility = 0.2;
            long timeToMaturity = 1.0;

            // Create an instance of OptionPricingModel

            OptionPricingModel model = new OptionPricingModel(underlyingPrice, strikePrice, riskFreeRate, volatility, timeToMaturity);

            // Calculate the option price

            double optionPrice = model.calculateOptionPrice();

            System.out.println("The calculated option price is: " + optionPrice);

            // Close the session factory

            sessionFactory.close();


        } catch (Exception e) {

            e.printStackTrace();

        }


    }
}
