package netcracker.practice.collections;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class DataStoringJob {
    private final static String menuPath = "menu.properties";
    private final static String catsPath = "cats.properties";
    private final static String waitersPath = "waiters.properties";

    private void writeListToFile(List<String> list, String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (list != null && !list.isEmpty()) {
                ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
                writer.writeValue(new File(path), list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readListFromFile(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(path), List.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void writeHashSetToFile(HashSet<String> set, String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (set != null && !set.isEmpty()) {
                ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
                writer.writeValue(new File(path), set);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashSet<String> readHashSetFromFile(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(path), HashSet.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeMenuToFile(List<String> menu) {
        writeListToFile(menu, menuPath);
    }

    public List<String> readMenuFromFile() {
        return (List<String>) readListFromFile(menuPath);
    }

    public void writeCatsToFile(HashSet<String> cats) {
        writeHashSetToFile(cats, catsPath);
    }

    public HashSet<String> readCatsFromFile() {
        return (HashSet<String>) readHashSetFromFile(catsPath);
    }

    public void writeWaitersToFile(HashSet<String> waiters) {
        writeHashSetToFile(waiters, waitersPath);
    }

    public HashSet<String> readWaitersFromFile() {
        return (HashSet<String>) readHashSetFromFile(waitersPath);
    }
}
