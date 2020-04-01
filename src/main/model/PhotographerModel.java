package main.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotographerModel {
    private String firstName = null;
    private String lastName = null;
    private Date birthday = null;
    private String notes = null;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setBirthday(String birthdayString) throws ParseException {
        this.birthday = new SimpleDateFormat("dd.MM.yyyy").parse(birthdayString);
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
