package testdata;

import models.Category;
import models.Pet;
import models.User;

public class ApiTestData {
    public static final User DEFAULT_USER = new User(0, "FPMI_user_1", "firstName", "lastName", "email@gmail.com", "qwerty", "12345678", 0);
    public static final User DEFAULT_USER_BUILDER = User.builder()
            .id(0)
            .username("FPMI_user_1")
            .firstName("firstName")
            .lastName("lastName")
            .email("email@gmail.com")
            .password("qwerty")
            .phone("12345678")
            .userStatus(0)
            .build();

    public static final User TEST = User.builder()
            .id(1)
            .username("test")
            .build();

    public static final Category dogCategory = new Category(0,"dog");
    public static final Category catCategory = new Category(0,"cat");

    public static final Pet DEFAULT_PET_BUILDER = Pet.builder()
            .id(1234123)
            .category(dogCategory)
            .name("Buddy")
            .status("available")
            .build();
    public static final Pet TEST_PET_CAT = Pet.builder()
            .id(12341)
            .category(catCategory)
            .name("Kitty")
            .status("sold")
            .build();
}