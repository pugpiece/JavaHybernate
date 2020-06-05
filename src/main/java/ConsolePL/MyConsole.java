package ConsolePL;

import DAO.Implementations.*;
import DAO.Interfaces.*;
import DAO.SessionFactory.SessionFactorySingletone;
import Entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MyConsole {
    private SessionFactory sessionFactory;
    private IChefDAO chefDAO;
    private ICountryDAO countryDAO;
    private ICustomerDAO customerDAO;
    private IDishDAO dishDAO;
    private IInstrumentDAO instrumentDAO;
    private IProductDAO productDAO;
    private IReviewDAO reviewDAO;
    private IShopDAO shopDAO;
    private Scanner scan;

    public MyConsole() {
        sessionFactory = SessionFactorySingletone.getSessionFactory();
        chefDAO = new ChefDAO();
        countryDAO = new CountryDAO();
        customerDAO = new CustomerDAO();
        dishDAO = new DishDAO();
        instrumentDAO = new InstrumentDAO();
        productDAO = new ProductDAO();
        reviewDAO = new ReviewDAO();
        shopDAO = new ShopDAO();
        scan = new Scanner(java.lang.System.in);

    }

    public void print(List<? extends Object> list ) throws JsonProcessingException {
        for (Object item: list) {
            String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
            System.out.println(json);
        }
    }

    public void StartPage() throws JsonProcessingException{
        System.out.println("Перед тем как начать работу, вам необходимо войти в систему");
        System.out.println("1 - войти в существующий аккаунт");
        System.out.println("2 - создать новый аккаунт");
        System.out.println("3 - выход");
        try {
            switch (scan.nextInt()) {
                case 1: {
                    LogInPage();
                }
                break;
                case 2: {
                    AddCustomerPage();
                }
                break;
                case 3: {
                    System.exit(0);
                }
                break;
                default: {
                    StartPage();
                }
                }
        }
        catch (InputMismatchException e) {
            System.out.println("Вы ввели недействительный ответ, повторите попытку");
            StartPage();
        }
    }

    public void AddCustomerPage() throws JsonProcessingException{
        scan.nextLine();
        System.out.println("Введите логин:");
        String login = scan.nextLine();
        System.out.println("Введите пароль:");
        String passwordHashCode = scan.nextLine();
        System.out.println("Являетесь ли вы поваром:");
        System.out.println("1 - да");
        System.out.println("2 - нет");
        int isChef = scan.nextInt();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (isChef == 1) {
                session.save(new Customer(login, passwordHashCode));
            }
            else if (isChef == 2) {
                session.save(new Chef(login, passwordHashCode));
            }
            else {
                System.out.println("Вы ввели недействительный ответ, повторите попытку");
                AddCustomerPage();
            }
        }
        catch  (InputMismatchException e ) {
            System.out.println("Вы ввели недействительный ответ, повторите попытку");
            AddCustomerPage();
        }
        transaction.commit();
        session.close();
        MainPage();
    }

    public void LogInPage() throws JsonProcessingException{
        scan.nextLine();
        System.out.println("Введите логин:");
        String login = scan.nextLine();
        System.out.println("Введите пароль:");
        String password = scan.nextLine();
        if ((customerDAO.loginCheck(login, password).size() > 0) || (chefDAO.loginCheck(login, password).size() > 0)) {
            MainPage();
        }
        else {
            scan.nextLine();
            System.out.println("Такого пользователя не существует");
            System.out.println("1 - попробовать еще раз");
            System.out.println("2 - создать новый аккаунт");
            try {
                switch (scan.nextInt()) {
                    case 1: {
                        LogInPage();
                    }
                    case 2: {
                        AddCustomerPage();
                    }
                    default: {
                        LogInPage();
                    }
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Вы ввели недействительный ответ, повторите попытку");
                LogInPage();
            }
        }
    }

    public void MainPage() throws JsonProcessingException{
        System.out.println("Вы находитесь на главной странице, выберите действие");
        System.out.println("1 - работать с поварами");
        System.out.println("2 - работать с странами");
        System.out.println("3 - работать с пользователями");
        System.out.println("4 - работать с блюдами");
        System.out.println("5 - работать с инструментами");
        System.out.println("6 - работать с продуктами");
        System.out.println("7 - работать с обзорами");
        System.out.println("8 - работать с магазинами");
        System.out.println("9 - выход");
        try {
            switch (scan.nextInt()) {
                case 1: {
                    ChefPage();
                }
                case 2: {
                    CountryPage();
                }
                case 3: {
                    CustomerPage();
                }
                case 4: {
                    DishPage();
                }
                case 5: {
                    InstrumentPage();
                }
                case 6: {
                    ProductPage();
                }
                case 7: {
                    ReviewPage();
                }
                case 8: {
                    ShopPage();
                }
                case 9: {
                    System.exit(0);
                }
                default: {
                    MainPage();
                }
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Вы ввели недействительный ответ, повторите попытку");
            MainPage();
        }
    }

    public void ChefPage() throws JsonProcessingException{
        System.out.println("Вы работаете с списком поваров, выберите действие");
        System.out.println("1 - добавить повара");
        System.out.println("2 - удалить повара");
        System.out.println("3 - просмотреть список");
        System.out.println("4 - выход в меню");
        try {
            switch (scan.nextInt()) {
                case 1: {
                    scan.nextLine();
                    System.out.println("Введите логин повара:");
                    String login = scan.nextLine();
                    System.out.println("Введите пароль повара:");
                    String password= scan.nextLine();
                    System.out.println("Введите рейтинг повара:");
                    float rating = scan.nextFloat();
                    Chef temp = new Chef();
                    temp.setCustomerLogin(login);
                    temp.setCustomerPassword(password);
                    temp.setChefRating(rating);
                    chefDAO.addChef(temp);
                    System.out.println("Тренер успешно добавлен!");
                    ChefPage();

                }
                case 2: {
                    scan.nextLine();
                    System.out.println("Введите Id повара:");
                    int id = scan.nextInt();
                    System.out.println("Вы действительно хотите удалить этого повара?");
                    System.out.println("1 - да");
                    System.out.println("2 - нет");
                    switch (scan.nextInt()) {
                        case 1: {
                            chefDAO.deleteChef(id);
                        }
                        case 2: {

                        }
                    }
                    ChefPage();
                }
                case 3: {
                    scan.nextLine();
                    System.out.println("Выберите действие:");
                    System.out.println("1 - просмотреть весь список");
                    System.out.println("2 - поиск по имени");
                    System.out.println("3 - поиск по индексу");
                    switch (scan.nextInt()) {
                        case 1: {
                            List<Chef> chefs = chefDAO.readChefs();
                            if (chefs.size() > 0) {
                                print(chefs);
                            } else {
                                System.out.println("Список поваров пуст!");
                            }
                            ChefPage();
                        }
                        case 2: {
                            scan.nextLine();
                            System.out.println("Введите имя повара:");
                            String name = scan.nextLine();
                            List<Chef> chefs = chefDAO.findChefsName(name);
                            if (chefs.size() > 0) {
                                print(chefs);
                            }
                            else {
                                System.out.println("Таких поваров не существует");
                            }
                            ChefPage();
                        }
                        case 3: {
                            scan.nextLine();
                            System.out.println("Введите рейтинг повара:");
                            float rating = scan.nextFloat();
                            List<Chef> chefs = chefDAO.findChefsRating(rating);
                            if (chefs.size() > 0) {
                                print(chefs);
                            } else {
                                System.out.println("Таких поваров не существует");
                            }
                            ChefPage();
                        }
                        default:{
                            ChefPage();
                        }

                    }
                }
                case 4: {
                    MainPage();
                }
                default:{
                    ChefPage();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели недействительный ответ, повторите попытку");
            ChefPage();
        }
    }

    public void CountryPage() throws JsonProcessingException{
        scan.nextLine();
        System.out.println("Вы работаете с списком cтран, выберите действие");
        System.out.println("1 - добавить страну");
        System.out.println("2 - удалить страну");
        System.out.println("3 - просмотреть список");
        System.out.println("4 - выход в меню");
        try {
            switch (scan.nextInt()) {
                case 1: {
                    scan.nextLine();
                    System.out.println("Введите название страны:");
                    String name = scan.nextLine();
                    System.out.println("Введите описание страны:");
                    String description = scan.nextLine();
                    Country temp = new Country();
                    temp.setCountryName(name);
                    temp.setCountryDescription(description);
                    countryDAO.addCountry(temp);
                    System.out.println("Страна успешно добавлена!");
                    CountryPage();

                }
                case 2: {
                    scan.nextLine();
                    System.out.println("Введите Id страны:");
                    int id = scan.nextInt();
                    System.out.println("Вы действительно хотите удалить эту страну?");
                    System.out.println("1 - да");
                    System.out.println("2 - нет");
                    switch (scan.nextInt()) {
                        case 1: {
                            countryDAO.deleteCountry(id);
                        }
                        case 2: {

                        }
                    }
                    CountryPage();
                }
                case 3: {
                    scan.nextLine();
                    System.out.println("Выберите действие:");
                    System.out.println("1 - просмотреть весь список");
                    System.out.println("2 - поиск по названию");
                    switch (scan.nextInt()) {
                        case 1: {
                            List<Country> countries = countryDAO.readCountries();
                            if (countries.size() > 0) {
                                print(countries);
                            } else {
                                System.out.println("Список стран пуст!");
                            }
                            CountryPage();
                        }
                        case 2: {
                            scan.nextLine();
                            System.out.println("Введите название страны:");
                            String name = scan.nextLine();
                            List<Country> countries = countryDAO.findCountriesName(name);
                            if (countries.size() > 0) {
                                print(countries);
                            } else {
                                System.out.println("Таких стран не существует");
                            }
                            CountryPage();
                        }
                        default:{
                            CountryPage();
                        }

                    }

                }
                case 4: {
                    MainPage();
                }
                default:{
                    CountryPage();
                }
            }
        } catch (InputMismatchException e) {
            scan.nextLine();
            System.out.println("Вы ввели недействительный ответ, повторите попытку");
            CountryPage();
        }
    }

    public void CustomerPage() throws JsonProcessingException{
        scan.nextLine();
        System.out.println("Вы работаете с списком пользователей, выберите действие");
        System.out.println("1 - добавить пользователя");
        System.out.println("2 - удалить пользователя");
        System.out.println("3 - просмотреть список");
        System.out.println("4 - выход в меню");
        try {
            switch (scan.nextInt()) {
                case 1: {
                    scan.nextLine();
                    System.out.println("Введите логин пользователя:");
                    String login = scan.nextLine();
                    System.out.println("Введите пароль пользователя:");
                    String password = scan.nextLine();
                    Customer temp = new Customer();
                    temp.setCustomerLogin(login);
                    temp.setCustomerPassword(password);
                    customerDAO.addCustomer(temp);
                    System.out.println("Пользователь успешно добавлен!");
                    CustomerPage();

                }
                case 2: {
                    scan.nextLine();
                    System.out.println("Введите Id пользователя:");
                    int id = scan.nextInt();
                    System.out.println("Вы действительно хотите удалить этого пользователя?");
                    System.out.println("1 - да");
                    System.out.println("2 - нет");
                    switch (scan.nextInt()) {
                        case 1: {
                            customerDAO.deleteCustomer(id);
                        }
                        case 2: {

                        }
                    }
                    CustomerPage();
                }
                case 3: {
                    scan.nextLine();
                    System.out.println("Выберите действие:");
                    System.out.println("1 - просмотреть весь список");
                    System.out.println("2 - поиск по логину");
                    switch (scan.nextInt()) {
                        case 1: {
                            List<Customer> customers = customerDAO.readCustomers();
                            if (customers.size() > 0) {
                                print(customers);
                            } else {
                                System.out.println("Список пользователей пуст!");
                            }
                            CustomerPage();
                        }
                        case 2: {
                            scan.nextLine();
                            System.out.println("Введите логин пользователя:");
                            String login = scan.nextLine();
                            List<Customer> customers = customerDAO.findCustomersName(login);
                            if (customers.size() > 0) {
                                print(customers);
                            } else {
                                System.out.println("Таких пользователей не существует");
                            }
                            CustomerPage();
                        }
                        default:{
                            CustomerPage();
                        }

                    }

                }
                case 4: {
                    MainPage();
                }
                default:{
                    CustomerPage();
                }
            }
        } catch (InputMismatchException e) {
            scan.nextLine();
            System.out.println("Вы ввели недействительный ответ, повторите попытку");
            CustomerPage();
        }
    }

    public void DishPage() throws JsonProcessingException{
        scan.nextLine();
        System.out.println("Вы работаете с списком блюд, выберите действие");
        System.out.println("1 - добавить блюдо");
        System.out.println("2 - удалить блюдо");
        System.out.println("3 - просмотреть список");
        System.out.println("4 - выход в меню");
        try {
            switch (scan.nextInt()) {
                case 1: {
                    scan.nextLine();
                    System.out.println("Введите название блюда:");
                    String name = scan.nextLine();
                    System.out.println("Введите рецепт:");
                    String recipe = scan.nextLine();
                    Dish temp = new Dish();
                    temp.setDishName(name);
                    temp.setDishRecipe(recipe);
                    dishDAO.addDish(temp);
                    System.out.println("Блюдо успешно добавлено!");
                    DishPage();

                }
                case 2: {
                    scan.nextLine();
                    System.out.println("Введите Id блюда:");
                    int id = scan.nextInt();
                    System.out.println("Вы действительно хотите удалить это блюдо?");
                    System.out.println("1 - да");
                    System.out.println("2 - нет");
                    switch (scan.nextInt()) {
                        case 1: {
                            dishDAO.deleteDish(id);
                        }
                        case 2: {

                        }
                    }
                    DishPage();
                }
                case 3: {
                    scan.nextLine();
                    System.out.println("Выберите действие:");
                    System.out.println("1 - просмотреть весь список");
                    System.out.println("2 - поиск по названию");
                    switch (scan.nextInt()) {
                        case 1: {
                            List<Dish> dishes = dishDAO.readDishes();
                            if (dishes.size() > 0) {
                                print(dishes);
                            } else {
                                System.out.println("Список блюд пуст!");
                            }
                            DishPage();
                        }
                        case 2: {
                            scan.nextLine();
                            System.out.println("Введите название блюда:");
                            String name = scan.nextLine();
                            List<Dish> dishes = dishDAO.findDishesName(name);
                            if (dishes.size() > 0) {
                                print(dishes);
                            } else {
                                System.out.println("Таких блюд не существует");
                            }
                            DishPage();
                        }
                        default:{
                            DishPage();
                        }

                    }

                }
                case 4: {
                    MainPage();
                }
                default:{
                    DishPage();
                }
            }
        } catch (InputMismatchException e) {
            scan.nextLine();
            System.out.println("Вы ввели недействительный ответ, повторите попытку");
            DishPage();
        }
    }

    public void InstrumentPage() throws JsonProcessingException {
        scan.nextLine();
        System.out.println("Вы работаете с списком инструментов, выберите действие");
        System.out.println("1 - добавить инструмент");
        System.out.println("2 - удалить инструмент");
        System.out.println("3 - просмотреть список");
        System.out.println("4 - выход в меню");
        try {
            switch (scan.nextInt()) {
                case 1: {
                    scan.nextLine();
                    System.out.println("Введите название инструмента:");
                    String name = scan.nextLine();
                    System.out.println("Введите описание:");
                    String description = scan.nextLine();
                    System.out.println("Введите цену:");
                    float cost = scan.nextFloat();
                    Instrument temp = new Instrument();
                    temp.setInstrumentName(name);
                    temp.setInstrumentDescription(description);
                    temp.setInstrumentCost(cost);
                    instrumentDAO.addInstrument(temp);
                    System.out.println("Инструмент успешно добавлен!");
                    InstrumentPage();

                }
                case 2: {
                    scan.nextLine();
                    System.out.println("Введите Id инструмента:");
                    int id = scan.nextInt();
                    System.out.println("Вы действительно хотите удалить этот инструмент?");
                    System.out.println("1 - да");
                    System.out.println("2 - нет");
                    switch (scan.nextInt()) {
                        case 1: {
                            instrumentDAO.deleteInstrument(id);
                        }
                        case 2: {

                        }
                    }
                    InstrumentPage();
                }
                case 3: {
                    scan.nextLine();
                    System.out.println("Выберите действие:");
                    System.out.println("1 - просмотреть весь список");
                    System.out.println("2 - поиск по названию");
                    System.out.println("3 - поиск по цене");
                    switch (scan.nextInt()) {
                        case 1: {
                            List<Instrument> instruments = instrumentDAO.readInstruments();
                            if (instruments.size() > 0) {
                                print(instruments);
                            } else {
                                System.out.println("Список инструментов пуст!");
                            }
                            InstrumentPage();
                        }
                        case 2: {
                            scan.nextLine();
                            System.out.println("Введите название блюда:");
                            String name = scan.nextLine();
                            List<Instrument> instruments = instrumentDAO.findInstrumentsName(name);
                            if (instruments.size() > 0) {
                                print(instruments);
                            } else {
                                System.out.println("Таких инструментов не существует");
                            }
                            InstrumentPage();
                        }
                        case 3: {
                            scan.nextLine();
                            System.out.println("Введите цену инструмента:");
                            float cost = scan.nextFloat();
                            List<Instrument> instruments = instrumentDAO.findInstrumentsCost(cost);
                            if (instruments.size() > 0) {
                                print(instruments);
                            } else {
                                System.out.println("Таких инструментов не существует");
                            }
                            InstrumentPage();
                        }
                        default: {
                            InstrumentPage();
                        }

                    }

                }
                case 4: {
                    MainPage();
                }
                default: {
                    InstrumentPage();
                }
            }
        } catch (InputMismatchException e) {
            scan.nextLine();
            System.out.println("Вы ввели недействительный ответ, повторите попытку");
            InstrumentPage();
        }
    }

    public void ProductPage() throws JsonProcessingException {
        scan.nextLine();
        System.out.println("Вы работаете с списком продуктов, выберите действие");
        System.out.println("1 - добавить продукт");
        System.out.println("2 - удалить продукт");
        System.out.println("3 - просмотреть список");
        System.out.println("4 - выход в меню");
        try {
            switch (scan.nextInt()) {
                case 1: {
                    scan.nextLine();
                    System.out.println("Введите название продукта:");
                    String name = scan.nextLine();
                    System.out.println("Введите жиры:");
                    float fats = scan.nextFloat();
                    System.out.println("Введите белки:");
                    float proteins = scan.nextFloat();
                    System.out.println("Введите углеводы:");
                    float carbohydrates = scan.nextFloat();
                    System.out.println("Введите калории:");
                    float calories = scan.nextFloat();
                    System.out.println("Введите цену:");
                    float cost = scan.nextFloat();
                    Product temp = new Product();
                    temp.setProductName(name);
                    temp.setProductFats(fats);
                    temp.setProductProteins(proteins);
                    temp.setProductCarbohydrates(carbohydrates);
                    temp.setProductCalories(calories);
                    temp.setProductCost(cost);
                    productDAO.addProduct(temp);
                    System.out.println("Продукт успешно добавлен!");
                    ProductPage();

                }
                case 2: {
                    scan.nextLine();
                    System.out.println("Введите Id продукта:");
                    int id = scan.nextInt();
                    System.out.println("Вы действительно хотите удалить этот продукт?");
                    System.out.println("1 - да");
                    System.out.println("2 - нет");
                    switch (scan.nextInt()) {
                        case 1: {
                            productDAO.deleteProduct(id);
                        }
                        case 2: {

                        }
                    }
                    ProductPage();
                }
                case 3: {
                    scan.nextLine();
                    System.out.println("Выберите действие:");
                    System.out.println("1 - просмотреть весь список");
                    System.out.println("2 - поиск по названию");
                    System.out.println("3 - поиск по калориям");
                    switch (scan.nextInt()) {
                        case 1: {
                            List<Product> products = productDAO.readProducts();
                            if (products.size() > 0) {
                                print(products);
                            } else {
                                System.out.println("Список продуктов пуст!");
                            }
                            ProductPage();
                        }
                        case 2: {
                            scan.nextLine();
                            System.out.println("Введите название продукта:");
                            String name = scan.nextLine();
                            List<Product> products = productDAO.findProductsName(name);
                            if (products.size() > 0) {
                                print(products);
                            } else {
                                System.out.println("Таких инструментов не существует");
                            }
                            ProductPage();
                        }
                        case 3: {
                            scan.nextLine();
                            System.out.println("Введите название продукта:");
                            float calories = scan.nextFloat();
                            List<Product> products = productDAO.findProductsCalories(calories);
                            if (products.size() > 0) {
                                print(products);
                            } else {
                                System.out.println("Таких инструментов не существует");
                            }
                            ProductPage();
                        }
                        default: {
                            ProductPage();
                        }

                    }

                }
                case 4: {
                    MainPage();
                }
                default: {
                    ProductPage();
                }
            }
        } catch (InputMismatchException e) {
            scan.nextLine();
            System.out.println("Вы ввели недействительный ответ, повторите попытку");
            ProductPage();
        }
    }

    public void ReviewPage() throws JsonProcessingException {
        scan.nextLine();
        System.out.println("Вы работаете с списком обзоров, выберите действие");
        System.out.println("1 - добавить обзор");
        System.out.println("2 - удалить обзор");
        System.out.println("3 - просмотреть список");
        System.out.println("4 - выход в меню");
        try {
            switch (scan.nextInt()) {
                case 1: {
                    scan.nextLine();
                    System.out.println("Введите оценку:");
                    float rating = scan.nextFloat();
                    System.out.println("Введите плюсы:");
                    String pluses = scan.nextLine();
                    System.out.println("Введите минусы:");
                    String minuses = scan.nextLine();
                    System.out.println("Введите обзор:");
                    String overview = scan.nextLine();
                    Review temp = new Review();
                    temp.setReviewRating(rating);
                    temp.setReviewPluses(pluses);
                    temp.setReviewMinuses(minuses);
                    temp.setReviewOverview(overview);
                    reviewDAO.addReview(temp);
                    System.out.println("Обзор успешно добавлен!");
                    ReviewPage();

                }
                case 2: {
                    scan.nextLine();
                    System.out.println("Введите Id обзора:");
                    int id = scan.nextInt();
                    System.out.println("Вы действительно хотите удалить этот обзор?");
                    System.out.println("1 - да");
                    System.out.println("2 - нет");
                    switch (scan.nextInt()) {
                        case 1: {
                            reviewDAO.deleteReview(id);
                        }
                        case 2: {

                        }
                    }
                    ReviewPage();
                }
                case 3: {
                    scan.nextLine();
                    System.out.println("Выберите действие:");
                    System.out.println("1 - просмотреть весь список");
                    System.out.println("2 - поиск по рейтингу");
                    switch (scan.nextInt()) {
                        case 1: {
                            List<Review> reviews = reviewDAO.readReviews();
                            if (reviews.size() > 0) {
                                print(reviews);
                            } else {
                                System.out.println("Список обзоров пуст!");
                            }
                            ReviewPage();
                        }
                        case 2: {
                            scan.nextLine();
                            System.out.println("Введите оценку обзора:");
                            float rating = scan.nextFloat();
                            List<Review> reviews = reviewDAO.findReviewsRating(rating);
                            if (reviews.size() > 0) {
                                print(reviews);
                            } else {
                                System.out.println("Таких обзоров не существует");
                            }
                            ReviewPage();
                        }
                        default: {
                            ReviewPage();
                        }

                    }

                }
                case 4: {
                    MainPage();
                }
                default: {
                    ReviewPage();
                }
            }
        } catch (InputMismatchException e) {
            scan.nextLine();
            System.out.println("Вы ввели недействительный ответ, повторите попытку");
            ReviewPage();
        }
    }

    public void ShopPage() throws JsonProcessingException {
        scan.nextLine();
        System.out.println("Вы работаете с списком магазинов, выберите действие");
        System.out.println("1 - добавить магазин");
        System.out.println("2 - удалить магазин");
        System.out.println("3 - просмотреть список");
        System.out.println("4 - выход в меню");
        try {
            switch (scan.nextInt()) {
                case 1: {
                    scan.nextLine();
                    System.out.println("Введите название:");
                    String name = scan.nextLine();
                    System.out.println("Введите описание:");
                    String description = scan.nextLine();
                    System.out.println("Введите рейтинг:");
                    float rating = scan.nextFloat();
                    Shop temp = new Shop();
                    temp.setShopName(name);
                    temp.setShopDescription(description);
                    temp.setShopRating(rating);
                    shopDAO.addShop(temp);
                    System.out.println("Магазин успешно добавлен!");
                    ShopPage();

                }
                case 2: {
                    scan.nextLine();
                    System.out.println("Введите Id магазина:");
                    int id = scan.nextInt();
                    System.out.println("Вы действительно хотите удалить этот магазин?");
                    System.out.println("1 - да");
                    System.out.println("2 - нет");
                    switch (scan.nextInt()) {
                        case 1: {
                            shopDAO.deleteShop(id);
                        }
                        case 2: {

                        }
                    }
                    ShopPage();
                }
                case 3: {
                    scan.nextLine();
                    System.out.println("Выберите действие:");
                    System.out.println("1 - просмотреть весь список");
                    System.out.println("2 - поиск по рейтингу");
                    System.out.println("3 - поиск по названию");
                    switch (scan.nextInt()) {
                        case 1: {
                            List<Shop> shops = shopDAO.readShops();
                            if (shops.size() > 0) {
                                print(shops);
                            } else {
                                System.out.println("Список магазинов пуст!");
                            }
                            ShopPage();
                        }
                        case 2: {
                            scan.nextLine();
                            System.out.println("Введите оценку магазина:");
                            float rating = scan.nextFloat();
                            List<Shop> shops = shopDAO.findShopsRating(rating);
                            if (shops.size() > 0) {
                                print(shops);
                            } else {
                                System.out.println("Таких магазинов не существует");
                            }
                            ShopPage();
                        }
                        case 3: {
                            scan.nextLine();
                            System.out.println("Введите название магазина:");
                            String name = scan.nextLine();
                            List<Shop> shops = shopDAO.findShopsName(name);
                            if (shops.size() > 0) {
                                print(shops);
                            } else {
                                System.out.println("Таких магазинов не существует");
                            }
                            ShopPage();
                        }
                        default: {
                            ShopPage();
                        }

                    }

                }
                case 4: {
                    MainPage();
                }
                default: {
                    ShopPage();
                }
            }
        } catch (InputMismatchException e) {
            scan.nextLine();
            System.out.println("Вы ввели недействительный ответ, повторите попытку");
            ShopPage();
        }
    }

}
