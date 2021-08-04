package helpers;

import com.github.javafaker.Faker;
import enums.Status;
import models.Category;
import models.Pet;
import models.Tag;

import java.util.Random;

public class TestDataGenerator {
    public static Pet generateRandomPet(){
        Faker faker = new Faker();
        Random random = new Random();
        Category category = new Category(random.nextInt(),faker.funnyName().name());
        String petName = faker.animal().name();
        String[] photoUrls = {faker.internet().url()};
        Tag tag = new Tag(random.nextInt(), faker.space().constellation());
        Tag[] tags = {tag};
        return new Pet(petName, photoUrls, category, tags, Status.available);
    }
}