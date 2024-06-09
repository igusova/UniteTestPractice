package controllers;

import configurations.TestConfig;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.restassured.AllureRestAssured;
import models.Pet;

import static io.restassured.RestAssured.given;
import static testdata.ApiTestData.DEFAULT_PET_BUILDER;

public class PetController {
    TestConfig config = new TestConfig();
    RequestSpecification requestSpecification = given();

    public PetController() {
        RestAssured.defaultParser = Parser.JSON;
        this.requestSpecification.contentType(ContentType.JSON);
        this.requestSpecification.accept(ContentType.JSON);
        this.requestSpecification.baseUri(config.getBaseUrl());
        this.requestSpecification.filter(new AllureRestAssured());
    }

    @Step("Add default pet")
    public Response addDefaultPet() {
        this.requestSpecification.body(DEFAULT_PET_BUILDER);
        return given(this.requestSpecification).post("user").andReturn();
    }

    @Step("Add user")
    public Response addPet(Pet pet) {
        this.requestSpecification.body(pet);
        return given(this.requestSpecification).post("pet").andReturn();
    }

    @Step("Get pet by id")
    public Response getPetById(int id) {
        return given(this.requestSpecification).get("pet/" + id).andReturn();
    }

    @Step("Delete user by id")
    public Response deletePetById(int id) {
        return given(this.requestSpecification).delete("pet/" + id).andReturn();
    }

}
