package main.viewmodel.children;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchViewModel {
    private StringProperty searchText = new SimpleStringProperty();
    private BooleanBinding activeSearch;

    public SearchViewModel() {
        searchText.addListener((s,o,n) -> activeSearch.invalidate());
        activeSearch = new BooleanBinding() {
            @Override
            protected boolean computeValue() {
                return !getSearchText().isEmpty();
            }
        };
    }

    public StringProperty searchTextProperty() {
        return searchText;
    }

    public String getSearchText() {
        return searchText.get() != null ? searchText.get() : "";
    }

    public BooleanBinding activeSearchProperty() {
        return activeSearch;
    }
}
