package main.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import main.model.PhotographerModel;

import java.time.LocalDate;

public class PhotographerViewModel {
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>();
    private StringProperty notes = new SimpleStringProperty();

    public PhotographerViewModel(PhotographerModel photographer) {
        refresh(photographer);
    }

    public void refresh(PhotographerModel photographer) {
        firstName.setValue(photographer.getFirstName());
        lastName.setValue(photographer.getLastName());
        birthday.setValue(photographer.getBirthday());
        notes.setValue(photographer.getNotes());
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public StringProperty notesProperty() {
        return notes;
    }
}
