package main.viewmodel.children;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchViewModel {
    private StringProperty searchText = new SimpleStringProperty();

    public StringProperty searchTextProperty() {
        return searchText;
    }
}
