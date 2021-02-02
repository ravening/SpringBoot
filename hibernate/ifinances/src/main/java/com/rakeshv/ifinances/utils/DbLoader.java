package com.rakeshv.ifinances.utils;

import com.rakeshv.ifinances.models.Account;
import com.rakeshv.ifinances.models.AccountTypeEnum;
import com.rakeshv.ifinances.models.Address;
import com.rakeshv.ifinances.models.Bank;
import com.rakeshv.ifinances.models.Bond;
import com.rakeshv.ifinances.models.Budget;
import com.rakeshv.ifinances.models.Credential;
import com.rakeshv.ifinances.models.Currency;
import com.rakeshv.ifinances.models.Market;
import com.rakeshv.ifinances.models.Portfolio;
import com.rakeshv.ifinances.models.Stock;
import com.rakeshv.ifinances.models.TimeTest;
import com.rakeshv.ifinances.models.Transaction;
import com.rakeshv.ifinances.models.User;
import com.rakeshv.ifinances.repositories.AccountRepository;
import com.rakeshv.ifinances.repositories.BankRepository;
import com.rakeshv.ifinances.repositories.BondRepository;
import com.rakeshv.ifinances.repositories.BudgetRepository;
import com.rakeshv.ifinances.repositories.CredentialRepository;
import com.rakeshv.ifinances.repositories.CurrencyRepository;
import com.rakeshv.ifinances.repositories.MarketRepository;
import com.rakeshv.ifinances.repositories.PortfolioRepository;
import com.rakeshv.ifinances.repositories.StockRepository;
import com.rakeshv.ifinances.repositories.TimeTestRepository;
import com.rakeshv.ifinances.repositories.TransactionRepository;
import com.rakeshv.ifinances.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class DbLoader implements CommandLineRunner {

    @Autowired
    TimeTestRepository timeTestRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    CredentialRepository credentialRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    MarketRepository marketRepository;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    BondRepository bondRepository;

    @Override
    public void run(String... args) throws Exception {
        TimeTest timeTest = TimeTest.builder()
                .dateColumn(new Date())
                .timeColumn(new Date())
                .timestampColumn(new Date())
                .datetimeColumn(new Date())
                .sqlDateColumn(new java.sql.Date(new Date().getTime()))
                .sqlTimeColumn(new Time(new Date().getTime()))
                .sqlDateTimeColumn(new Timestamp(new Date().getTime()))
                .sqlTimestampColumn(new Timestamp(new Date().getTime())).build();
//        timeTestRepository.save(timeTest);

        Address address = Address.builder()
                .addressLine1("33 Wall street")
                .addressLine2("Suite 302")
                .city("New York")
                .state("NY")
                .zipCode("27914")
                .build();


        Address address1 = Address.builder()
                .addressLine1("11 manhattan street")
                .addressLine2("Floor 1")
                .city("Manhattan")
                .state("NY")
                .zipCode("97214").build();

        User user = User.builder()
                .createdBy("Kevin")
                .birthDate(getBirthday())
                .createdDate(new Date())
                .emailAddress("kmb385@yahoo.com")
                .firstName("Kevin")
                .lastName("Bowersox")
                .lastUpdatedBy("kevin")
                .lastUpdatedDate(new Date())
                .address(address).build();

        User user1 = User.builder()
                .createdBy("Plural")
                .birthDate(getBirthday())
                .createdDate(new Date())
                .emailAddress("tst@plural.com")
                .firstName("plural")
                .lastName("sight")
                .lastUpdatedBy("plurals")
                .lastUpdatedDate(new Date())
                .address(address).build();

        Credential credential = Credential.builder()
                .password("test")
                .username("user")
                .user(user).build();

//        user.setCredential(credential);
//        credentialRepository.save(credential);
//        userRepository.save(user);

//        List<User> users = userRepository.findAll();
//
//        users.forEach(u -> System.out.println(u.toString()));



        Bank bank = Bank.builder()
                .name("Federal Trust")
                .createdBy("Kevein Bowersox")
                .createdDate(new Date())
                .lastUpdatedBy("Kevin")
                .lastUpdatedDate(new Date())
                .international(false)
                .address(address).build();

        bank.getContacts().put("MANAGER", "JOE");
        bank.getContacts().put("TELLER", "Mary");
//        bankRepository.save(bank);

        Transaction transaction = Transaction.builder()
                .amount(new BigDecimal("50.00"))
                .title("Belt")
                .closingBalance(new BigDecimal("0.00"))
                .createdBy("Kevin")
                .createdDate(new Date())
                .initialBalance(new BigDecimal("10.00"))
                .lastUpdatedBy("Bowersox")
                .lastUpdatedDate(new Date())
                .notes("New dress belt")
                .transactionType("Debit").build();

        Account account = Account.builder()
                .createdBy("Kevein")
                .createdDate(new Date())
                .currentBalance(new BigDecimal("5.00"))
                .initialBalance(new BigDecimal("0.00"))
                .closeDate(new Date())
                .lastUpdatedBy("Bowersoz")
                .lastUpdatedDate(new Date())
                .openDate(new Date())
                .name("Savings").build();

        Account account1 = Account.builder()
                .createdBy("Plural")
                .createdDate(new Date())
                .currentBalance(new BigDecimal("5.00"))
                .initialBalance(new BigDecimal("0.00"))
                .closeDate(new Date())
                .lastUpdatedBy("Sight")
                .lastUpdatedDate(new Date())
                .openDate(new Date())
                .name("FD").build();

        Account checking = Account.builder()
                .createdBy("Checking")
                .createdDate(new Date())
                .currentBalance(new BigDecimal("5.00"))
                .initialBalance(new BigDecimal("0.00"))
                .closeDate(new Date())
                .lastUpdatedBy("Account")
                .lastUpdatedDate(new Date())
                .openDate(new Date())
                .accountTypeEnum(AccountTypeEnum.CHECKING)
                .name("FD").build();

//        accountRepository.save(checking);

        account.getUsers().add(user1);
        account.getUsers().add(user);
        transaction.setAccount(account);

        account1.getUsers().add(user1);
        account1.getUsers().add(user);

//        user.getAccounts().add(account);
//        user.getAccounts().add(account1);

//        user1.getAccounts().add(account);
//        user1.getAccounts().add(account1);
//        accountRepository.save(account1);
//        account.getTransactions().add(transaction);
//        accountRepository.save(account);
//        accountRepository.save(account1);

//        List<Account> accounts = accountRepository.findAll();
//        accounts.forEach(x -> System.out.println(x));

//        List<Transaction> transactions = transactionRepository.findAll();
//
//        transactions.forEach(System.out::println);

        Budget budget = Budget.builder()
                .goalAmount(new BigDecimal("10000.00"))
                .name("Emergency fund")
                .period("Yearly").build();

        budget.getTransactions().add(transaction);
//        budgetRepository.save(budget);

        Currency currency = Currency.builder()
                .countryName("Switzerland")
                .name("Frank")
                .symbol("swissfrank").build();


        Currency euro = Currency.builder()
                .countryName("Amsterdam")
                .name("Euro")
                .symbol("€").build();

//        currencyRepository.save(euro);
        List<Currency> currencies = currencyRepository.findAll();
        currencies.forEach(System.out::println);


        Market market = Market.builder()
                .marketName("Amsterdam Exchange")
                .currency(euro).build();
//        marketRepository.save(market);

//        List<Market> markets = marketRepository.findAll();
//        markets.forEach(System.out::println);

        Portfolio portfolio = Portfolio.builder()
                .name("My investment").build();

        Stock stock = Stock.builder()
                .quantity(new BigDecimal("10"))
                .sharePrice(new BigDecimal("100.00"))
                .build();
        stock.setPortfolio(portfolio);

        Bond bond = Bond.builder()
                .interestRate(new BigDecimal("4.5"))
                .maturityDate(new Date())
                .value(new BigDecimal("5000"))
                .build();
        bond.setPortfolio(portfolio);

        portfolio.getInvestments().add(stock);
        portfolio.getInvestments().add(bond);

        stockRepository.save(stock);
        bondRepository.save(bond);
    }

    private static Date getBirthday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1987);
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.DATE, 19);

        return calendar.getTime();
    }
}
