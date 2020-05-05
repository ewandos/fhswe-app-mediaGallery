package main.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchViewModel {
    private StringProperty searchText = new SimpleStringProperty();

    public SearchViewModel() {
        searchText.setValue("Hello World!");
    }

    public StringProperty searchTextProperty() {
        return searchText;
    }
}
