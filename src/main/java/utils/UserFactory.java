package utils;

import dto.User;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();

//    public static void main(String[] args) {
//        String name = faker.name().fullName(); // Miss Samanta Schmidt
//        String firstName = faker.name().firstName(); // Emory
//        String lastName = faker.name().lastName(); // Barton
//        String streetAddress = faker.address().streetAddress();
//
//        System.out.println(name);
//        System.out.println(firstName);
//        System.out.println(lastName);
//        System.out.println(streetAddress);
//        System.out.println(faker.internet().emailAddress());
//    }

    public static User positiveUser(){
        User user = new User(faker.internet().emailAddress(), "Password123!");
        return user;
    }

}