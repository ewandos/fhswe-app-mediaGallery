package main.viewmodel.subViewModel;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;

import main.model.PhotographerModel;

import java.time.LocalDate;

public class PhotographerViewModel {
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>();
    private StringProperty notes = new SimpleStringProperty();

    private BooleanBinding incorrectBirthday;

    private PhotographerModel photographerModel;

    public PhotographerViewModel(PhotographerModel photographer) {
        refresh(photographer);

        birthday.addListener((s,o,n) -> incorrectBirthday.invalidate());
        incorrectBirthday = new BooleanBinding() {
            @Override
            protected boolean computeValue() {
                LocalDate today = LocalDate.now();
                if (birthday.get() != null)
                    return birthday.get().isAfter(today) || birthday.get().isEqual(today);
                else
                    return false;
            }
        };
    }

    public void refresh(PhotographerModel photographer) {
        this.photographerModel = photographer;
        firstName.setValue(photographer.getFirstName());
        lastName.setValue(photographer.getLastName());
        birthday.setValue(photographer.getBirthday());
        notes.setValue(photographer.getNotes());
    }

    public void updateModel() {
        photographerModel.setFirstName(firstName.get());
        photographerModel.setLastName(lastName.get());
        photographerModel.setBirthday(birthday.get());
        photographerModel.setNotes(notes.get());
    }

    public PhotographerModel getPhotographerModel() {
        return photographerModel;
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

    public BooleanBinding cannotSaveProperty() {
        return Bindings.or(lastName.isEmpty(), incorrectBirthday);
    }

    public StringProperty notesProperty() {
        return notes;
    }



}
