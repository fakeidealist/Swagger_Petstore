package apiTest;

import helpers.TestDataGenerator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Pet;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetPet {
    private long petId;
    private Pet pet;

    @BeforeMethod
    public void createPet(){
        pet = TestDataGenerator.generateRandomPet();
        Response response =  given()
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/pet")
                .body(pet)
                .header("accept", "application/json")
                .header("Content-type", "application/json")
                .when()
                .post()// can change here to GET, POST, PUT, DELETE, or PATCH
                .then()
                .statusCode(200) // We assert status code is 200
                .extract()
                .response();
        petId = response.jsonPath().getLong("id");
    }

    @AfterMethod
    public void deletePet(){
        Response response =  given()
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/pet/"+petId)
                .header("accept", "application/json")
                .header("Content-type", "application/json")
                .when()
                .delete()
                .then()
                .statusCode(200) // We assert status code is 200
                .extract()
                .response();
    }
    @Test
    public void getPetById(){
        // Rest assured uses Gherkin like syntax
        Response response =  given()
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/pet/"+petId)
                .header("accept", "application/json")
                .header("Content-type", "application/json")
                .when()
                .get()// can change here to POST, PUT, DELETE, or PATCH
                .then()
                .statusCode(200) // We assert status code is 200
                .extract()
                .response();
        //System.out.println(response.jsonPath().prettify());
        JsonPath jsonPath = response.jsonPath();
        Pet petResponse = jsonPath.getObject("$",Pet.class); // "$" means root, it will get whole object
        Assert.assertEquals(pet.getName(), petResponse.getName());
        Assert.assertEquals(pet.getName(),petResponse.getName());
        Assert.assertEquals(pet.getCategory().getName(),petResponse.getCategory().getName());
        Assert.assertEquals(pet.getCategory().getId(),petResponse.getCategory().getId());
        Assert.assertEquals(pet.getPhotoUrls()[0],petResponse.getPhotoUrls()[0]);
    }

    @Test
    public void updatePet(){
        Pet updatedPet = TestDataGenerator.generateRandomPet();
        updatedPet.setId(petId);
        Response response =  given()
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/pet")
                .body(updatedPet)
                .header("accept", "application/json")
                .header("Content-type", "application/json")
                .when()
                .put()
                .then()
                .statusCode(200) // We assert status code is 200
                .extract()
                .response();
        JsonPath jsonPath = response.jsonPath();
        Pet petResponse = jsonPath.getObject("$",Pet.class);
        Assert.assertEquals(updatedPet.getName(), petResponse.getName());
        Assert.assertEquals(updatedPet.getCategory().getName(),petResponse.getCategory().getName());
        Assert.assertEquals(updatedPet.getCategory().getId(),petResponse.getCategory().getId());
        Assert.assertEquals(updatedPet.getPhotoUrls()[0],petResponse.getPhotoUrls()[0]);
    }
}