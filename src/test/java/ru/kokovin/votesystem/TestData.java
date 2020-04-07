package ru.kokovin.votesystem;

import ru.kokovin.votesystem.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.Date;

import static ru.kokovin.votesystem.model.AbstractBaseEntity.START_SEQ;

public class TestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int RESTAURANT1_ID = START_SEQ + 2;
    public static final int DISH1_ID = START_SEQ + 7;
    public static final LocalDate TODAY = LocalDateTime.now().toLocalDate();
    public static final int VOTE1_ID = START_SEQ + 52;


    public static final User USER = new User(USER_ID, "User", "user@yandex.ru",
            "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com",
            "admin", Role.ROLE_ADMIN, Role.ROLE_USER);

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "1rest");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "2rest");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "3rest");
    public static final Restaurant RESTAURANT4 = new Restaurant(RESTAURANT1_ID + 3, "4rest");
    public static final Restaurant RESTAURANT5 = new Restaurant(RESTAURANT1_ID + 4, "5rest");

    public static final Dish DISH1 = new Dish(DISH1_ID, "today-1st_dish_1rest", 30000L, TODAY);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "today-1st_dish_2rest", 30000L, TODAY);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "today-1st_dish_3rest", 30000L, TODAY);
    public static final Dish DISH4 = new Dish(DISH1_ID + 3, "today-1st_dish_4rest", 30000L, TODAY);
    public static final Dish DISH5 = new Dish(DISH1_ID + 4, "today-1st_dish_5rest", 30000L, TODAY);

    public static final Dish DISH6 = new Dish(DISH1_ID + 5, "today-second_dish_1rest", 30000L, TODAY);
    public static final Dish DISH7 = new Dish(DISH1_ID + 6, "today-second_dish_2rest", 30000L, TODAY);
    public static final Dish DISH8 = new Dish(DISH1_ID + 7, "today-second_dish_3rest", 30000L, TODAY);
    public static final Dish DISH9 = new Dish(DISH1_ID + 8, "today-second_dish_4rest", 30000L, TODAY);
    public static final Dish DISH10 = new Dish(DISH1_ID + 9, "today-second_dish_5rest", 30000L, TODAY);

    public static final Dish DISH11 = new Dish(DISH1_ID + 10, "today-third_dish_1rest", 30000L, TODAY);
    public static final Dish DISH12 = new Dish(DISH1_ID + 11, "today-third_dish_2rest", 30000L, TODAY);
    public static final Dish DISH13 = new Dish(DISH1_ID + 12, "today-third_dish_3rest", 30000L, TODAY);
    public static final Dish DISH14 = new Dish(DISH1_ID + 13, "today-third_dish_4rest", 30000L, TODAY);
    public static final Dish DISH15 = new Dish(DISH1_ID + 14, "today-third_dish_5rest", 30000L, TODAY);

    public static final Dish DISH16 = new Dish(DISH1_ID + 15, "today-fourth_dish_1rest", 30000L, TODAY);
    public static final Dish DISH17 = new Dish(DISH1_ID + 16, "today-fourth_dish_2rest", 30000L, TODAY);
    public static final Dish DISH18 = new Dish(DISH1_ID + 17, "today-fourth_dish_3rest", 30000L, TODAY);
    public static final Dish DISH19 = new Dish(DISH1_ID + 18, "today-fourth_dish_4rest", 30000L, TODAY);
    public static final Dish DISH20 = new Dish(DISH1_ID + 19, "today-fourth_dish_5rest", 30000L, TODAY);

    public static final Dish DISH21 = new Dish(DISH1_ID + 20, "1-0st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 25));
    public static final Dish DISH22 = new Dish(DISH1_ID + 21, "1-1st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 25));
    public static final Dish DISH23 = new Dish(DISH1_ID + 22, "1-2st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 26));
    public static final Dish DISH24 = new Dish(DISH1_ID + 23, "1-3st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 25));
    public static final Dish DISH25 = new Dish(DISH1_ID + 24, "1-4st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 26));

    public static final Dish DISH26 = new Dish(DISH1_ID + 25, "2-0st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 25));
    public static final Dish DISH27 = new Dish(DISH1_ID + 26, "2-1st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 25));
    public static final Dish DISH28 = new Dish(DISH1_ID + 27, "2-2st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 26));
    public static final Dish DISH29 = new Dish(DISH1_ID + 28, "2-3st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 25));
    public static final Dish DISH30 = new Dish(DISH1_ID + 29, "2-4st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 26));

    public static final Dish DISH31 = new Dish(DISH1_ID + 30, "3-0st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 25));
    public static final Dish DISH32 = new Dish(DISH1_ID + 31, "3-1st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 25));
    public static final Dish DISH33 = new Dish(DISH1_ID + 32, "3-2st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 26));
    public static final Dish DISH34 = new Dish(DISH1_ID + 33, "3-3st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 26));
    public static final Dish DISH35 = new Dish(DISH1_ID + 34, "3-4st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 26));

    public static final Dish DISH36 = new Dish(DISH1_ID + 35, "4-0st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 25));
    public static final Dish DISH37 = new Dish(DISH1_ID + 36, "4-1st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 25));
    public static final Dish DISH38 = new Dish(DISH1_ID + 37, "4-2st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 26));
    public static final Dish DISH39 = new Dish(DISH1_ID + 38, "4-3st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 25));
    public static final Dish DISH40 = new Dish(DISH1_ID + 39, "4-4st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 26));

    public static final Dish DISH41 = new Dish(DISH1_ID + 40, "5-0st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 25));
    public static final Dish DISH42 = new Dish(DISH1_ID + 41, "5-1st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 26));
    public static final Dish DISH43 = new Dish(DISH1_ID + 42, "5-2st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 25));
    public static final Dish DISH44 = new Dish(DISH1_ID + 43, "5-3st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 26));
    public static final Dish DISH45 = new Dish(DISH1_ID + 44, "5-4st_dish", 30000L, LocalDate.of(2020, Month.FEBRUARY, 26));

    public static final Vote VOTE1 = new Vote(VOTE1_ID, USER, RESTAURANT1, LocalDateTime.of(2020, Month.FEBRUARY, 25, 10, 0, 0));
    public static final Vote VOTE2 = new Vote(VOTE1_ID + 1, USER, RESTAURANT4, LocalDateTime.of(2020, Month.FEBRUARY, 26, 10, 0, 0));
    public static final Vote VOTE3 = new Vote(VOTE1_ID + 2, ADMIN, RESTAURANT2, LocalDateTime.of(2020, Month.FEBRUARY, 25, 10, 0, 0));
    public static final Vote VOTE4 = new Vote(VOTE1_ID + 3, ADMIN, RESTAURANT5, LocalDateTime.of(2020, Month.FEBRUARY, 26, 10, 0, 0));

    public static User getNewUser() {
        return new User(null, "New", "new@gmail.com",
                "newPass", false, new Date(), Collections.singleton(Role.ROLE_USER));
    }

    public static Restaurant getNewRestaurant() {
        return new Restaurant(null, "NewRestaurant");
    }

    public static Dish getNewDish() {
        return new Dish(null, "NewDish", 40000L, LocalDate.of(2020, Month.MARCH, 20));
    }

    public final static Dish[] DISHES = new Dish[]{DISH1, DISH2, DISH3, DISH4, DISH5, DISH6, DISH7, DISH8, DISH9, DISH10,
            DISH11, DISH12, DISH13, DISH14, DISH15, DISH16, DISH17, DISH18, DISH19, DISH20,
            DISH21, DISH22, DISH23, DISH24, DISH25, DISH26, DISH27, DISH28, DISH29, DISH30,
            DISH31, DISH32, DISH33, DISH34, DISH35, DISH36, DISH37, DISH38, DISH39, DISH40,
            DISH41, DISH42, DISH43, DISH44, DISH45};

    public final static Vote[] ALLVOTES = new Vote[]{VOTE1, VOTE2, VOTE3, VOTE4};
    public final static Vote[] USER_VOTES = new Vote[]{VOTE1, VOTE2};
    public final static Vote[] ADMIN_VOTES = new Vote[]{VOTE3, VOTE4};

    public final static Dish[] TODAYS_RESTAURANT1_MENU = new Dish[]{DISH1, DISH6, DISH11, DISH16};

    public static final User UPDATED_USER = new User(USER_ID, "newName", "newemail@ya.ru",
            "newPassword", Role.ROLE_USER);

    public static final Restaurant UPDATED_RESTAURANT = new Restaurant(RESTAURANT1_ID, "UpdatedRestaurant");

    public static final Dish UPDATED_DISH = new Dish(DISH1_ID, "UpdatedDish",50000L, LocalDate.of(2020, 4, 4));

    public static TestMatchers<User> USER_MATCHERS = TestMatchers.useFieldsComparator(User.class, "registered", "password");
    public static TestMatchers<Restaurant> RESTAURANT_MATCHERS = TestMatchers.useFieldsComparator(Restaurant.class, "menu");
    public static TestMatchers<Dish> DISH_MATCHERS = TestMatchers.useFieldsComparator(Dish.class, "restaurant");
    public static TestMatchers<Vote> VOTE_MATCHERS = TestMatchers.useFieldsComparator(Vote.class);

}
