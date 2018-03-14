package netcracker.practice.collections;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class DataStoringJob {
    private final static String MENU_PATH = "menu.json";
    private final static String CATS_PATH = "cats.json";
    private final static String WAITERS_PATH = "waiters.json";

    public static final Logger LOG = Logger.getLogger(DataStoringJob.class.getName());

    private void writeListToFile(List<String> list, String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (list != null && !list.isEmpty()) {
                ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
                writer.writeValue(new File(path), list);
            }
        } catch (IOException e) {
            LOG.error("JSON Exception in DataStoringJob class writeListToFile() method!\n", e);
        }
    }

    private List<String> readListFromFile(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(path), List.class);
        } catch (IOException e) {
            LOG.error("JSON Exception in DataStoringJob class readListFromFile() method!\n", e);
            return null;
        }
    }

    private void writeSetToFile(Set<String> set, String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (set != null && !set.isEmpty()) {
                ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
                writer.writeValue(new File(path), set);
            }
        } catch (IOException e) {
            LOG.error("JSON Exception in DataStoringJob class writeSetToFile() method!\n", e);
        }
    }

    private Set<String> readSetFromFile(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(path), Set.class);
        } catch (IOException e) {
            LOG.error("JSON Exception in DataStoringJob class readSetFromFile() method!\n", e);
            return null;
        }
    }

    public void writeMenuToFile(List<String> menu) {
        writeListToFile(menu, MENU_PATH);
    }

    public List<String> readMenuFromFile() {
        return readListFromFile(MENU_PATH);
    }

    public void writeCatsToFile(Set<String> cats) {
        writeSetToFile(cats, CATS_PATH);
    }

    public Set<String> readCatsFromFile() {
        return readSetFromFile(CATS_PATH);
    }

    public void writeWaitersToFile(Set<String> waiters) {
        writeSetToFile(waiters, WAITERS_PATH);
    }

    public Set<String> readWaitersFromFile() {
        return readSetFromFile(WAITERS_PATH);
    }
}
