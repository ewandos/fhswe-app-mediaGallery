package main.unitTests;

import main.model.PhotographerModel;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PhotographerModelTest {

    @Test
    void canBirthdayByString() {
        PhotographerModel photographer = new PhotographerModel();
        String time = "15.02.1995";
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(time);
            photographer.setBirthday(time);
        } catch(Exception e) {
            fail();
        }
        assertEquals(date, photographer.getBirthday());
    }
}