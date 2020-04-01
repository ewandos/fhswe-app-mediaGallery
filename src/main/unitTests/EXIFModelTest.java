package main.unitTests;

import main.model.EXIFModel;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EXIFModelTest {
    @Test
    void canSetDateByString() {
        EXIFModel exif = new EXIFModel();
        String time = "02.01.2020";
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(time);
            exif.setDate(time);
        } catch(Exception e) {
            fail();
        }
        assertEquals(date, exif.getDate());
    }
}
