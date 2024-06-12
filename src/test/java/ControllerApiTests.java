import controllers.PetController;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.Pet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static testdata.ApiTestData.DEFAULT_PET_BUILDER;

@Feature("ControllerTests")
@Tag("API")
class ControllerApiTests {
    PetController petController = new PetController();

    @BeforeEach
    @AfterEach
    void clear() {
        petController.deletePetById(DEFAULT_PET_BUILDER.getId());
    }

    @Test
    @Tag("smoke")
    @DisplayName("Check add pet is returns 200 status ok")
    void checkAddPetTest() {
        Response response = petController.addDefaultPet();
        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    @Tag("extended")
    @DisplayName("Check pet added correctly")
    void checkAddPetExtendedTest() {
        Response addPetResponse = petController.addPet(DEFAULT_PET_BUILDER);
        String expectedName = addPetResponse.jsonPath().getString("name");
        assertThat(addPetResponse.statusCode()).isEqualTo(200);

        Response getPetResponse = petController.getPetById(DEFAULT_PET_BUILDER.getId());
        Pet actualPet = getPetResponse.as(Pet.class);

        assertThat(getPetResponse.statusCode()).isEqualTo(200);
        assertThat(actualPet.getName()).isEqualTo(expectedName);
        assertThat(actualPet).usingRecursiveComparison().ignoringFields("id").isEqualTo(DEFAULT_PET_BUILDER);
    }
}