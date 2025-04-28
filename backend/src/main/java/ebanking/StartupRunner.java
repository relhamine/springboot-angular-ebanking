package ebanking;

import ebanking.dtos.AccountDTO;
import ebanking.dtos.CustomerDTO;
import ebanking.entities.ERole;
import ebanking.entities.Role;
import ebanking.entities.User;
import ebanking.exceptions.CustomerNotFoundException;
import ebanking.repositories.RoleRepository;
import ebanking.repositories.UserRepository;
import ebanking.services.AccountService;
import ebanking.services.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Component
public class StartupRunner implements CommandLineRunner {
    private static final Logger logger =  LogManager.getLogger( StartupRunner.class );

    @Override
    public void run(String... args) throws Exception {
        logger.debug("CommandLineRunner logic executing during startup...");
    }

    @Bean
    CommandLineRunner commandLineRunner(AccountService accountService, CustomerService customerService) {
        return args -> {
            Stream.of("Rachid", "Sandrine", "Camille").forEach(name -> {
                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                customerService.saveCustomer(customer);
            });
            customerService.listCustomers().forEach(customer -> {
                try {
                    accountService.saveAccount(Math.random() * 9000, 1000000, 9000, customer.getId());
                    accountService.saveAccount(Math.random() * 1200, 1000000, 5.5, customer.getId());

                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });
            List<AccountDTO> accounts = accountService.getAccounts();
            for (AccountDTO bankAccount : accounts) {
                for (int i = 0; i < 10; i++) {
                    String accountId = accountId = bankAccount.getId();
                    accountService.credit(accountId, 10000 + Math.random() * 120000, "Credit");
                    accountService.debit(accountId, 1000 + Math.random() * 9000, "Debit");
                }
            }
        };
    }


    @Bean
    CommandLineRunner start(RoleRepository roleRepository,
                            UserRepository userRepository) {
        return args -> {
            Role role = new Role();
            role.setName(ERole.ROLE_ADMIN);
            roleRepository.save(role);

            Set<Role> roles = new HashSet<>();
            roles.add(role);

            User user = new User();
            user.setEmail("relhamine@gmail.com");
            user.setUsername("relhamine@gmail.com");
            user.setRoles(roles);

            String password = "relhamine@gmail.com";
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(password);

            user.setPassword(encodedPassword);

            userRepository.save(user);
        };
    }
}
