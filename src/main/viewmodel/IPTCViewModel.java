package main.viewmodel;

import javafx.beans.property.*;
import main.model.IPTCModel;

import java.util.ListIterator;

public class IPTCViewModel {
    private StringProperty description = new SimpleStringProperty();
    private DoubleProperty rating = new SimpleDoubleProperty();
    private StringProperty tags = new SimpleStringProperty();

    public IPTCViewModel(IPTCModel iptc) {
        refresh(iptc);
    }

    public void refresh(IPTCModel iptc) {
        description.setValue(iptc.getDescription());
        rating.setValue(iptc.getRating());

        StringBuilder builder = new StringBuilder();
        ListIterator<String> iterator = iptc.getTags().listIterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next());
            if (iterator.hasNext())
                builder.append(", ");
        }

        tags.setValue(builder.toString());
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public DoubleProperty ratingProperty() {
        return rating;
    }

    public StringProperty tagsProperty() {
        return tags;
    }


}
