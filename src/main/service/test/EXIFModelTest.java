package main.service.test;

import main.model.IPTCModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EXIFModelTest {

    @Test
    void addsTagsToList() {
        IPTCModel iptc = new IPTCModel();
        String tag = "Baum";
        iptc.addTag(tag);
        List<String> tags = iptc.getTags();
        assertTrue(tags.contains(tag));
    }

    @Test
    void getTagsNeverReturnsNull() {
        IPTCModel iptc = new IPTCModel();
        assertNotNull(iptc.getTags());
    }

    @Test
    void deletesTagsInList() {
        IPTCModel iptc = getFilledObject();
        int count1 = iptc.getTags().size();
        iptc.deleteTag("Katze");
        int count2 = iptc.getTags().size();
        assertEquals(count1, count2 + 1);
    }

    IPTCModel getFilledObject() {
        IPTCModel iptc = new IPTCModel();
        List<String> tags = new ArrayList<String>();
        tags.add("Baum");
        tags.add("Katze");
        tags.add("Hund");
        iptc.setTags(tags);
        return iptc;
    }
}