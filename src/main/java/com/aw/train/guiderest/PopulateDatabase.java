package com.aw.train.guiderest;

import com.aw.train.guiderest.entity.Employee;
import com.aw.train.guiderest.entity.Order;
import com.aw.train.guiderest.entity.Role;
import com.aw.train.guiderest.entity.Status;
import com.aw.train.guiderest.repository.EmployeeRepository;
import com.aw.train.guiderest.repository.OrderRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PopulateDatabase {

    private static final Log logger = LogFactory.getLog(PopulateDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {
        return args -> {
            logger.info("Preloading " + employeeRepository.save(new Employee("Bilbo", "Baggins", Role.BURGLAR)));
            logger.info("Preloading " + employeeRepository.save(new Employee("Frodo", "Baggins", Role.THIEF)));

            logger.info("Preloading " + orderRepository.save(new Order("Nvidia GeForce RTX 3080", Status.COMPLETED)));
            logger.info("Preloading " + orderRepository.save(new Order("Sony Playstation 5", Status.IN_PROGRESS)));
        };
    }
}
