package main.unittest;

import main.service.DBService;
import org.h2.bnf.context.DbColumn;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBServiceTest {
    @Test
    void establishes_connection() {
        DBService db = DBService.getInstance();
        assertNotNull(db.getConnection());
    }

    @Test
    void can_get_picture_by_id() {
        DBService db = DBService.getInstance();
        ResultSet res = db.getPictureByID(1);
        assertNotNull(res);
        int size = 0;
        try {
            res.beforeFirst();
            res.last();
            size = res.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(1, size);
    }
}