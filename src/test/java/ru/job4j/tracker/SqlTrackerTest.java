package ru.job4j.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader().getResourceAsStream(
                "test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        item = tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenSaveItemAndFindAll() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        item = tracker.add(item);
        List<Item> items = new ArrayList<>();
        items.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
        List<Item> itemsRes = tracker.findAll();
        assertEquals(itemsRes.get(0), items.get(0));
    }

    @Test
    public void whenSaveItemAndFindByName() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        item = tracker.add(item);
        List<Item> items = new ArrayList<>();
        items.add(item);
        List<Item> itemsRes = tracker.findByName(item.getName());
        assertEquals(itemsRes.get(0), items.get(0));
    }

    @Test
    public void whenSaveItemAndDelete() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        item = tracker.add(item);
        boolean res = tracker.delete(item.getId());
        assertTrue(res);
        List<Item> itemsRes = tracker.findByName(item.getName());
        assertEquals(itemsRes.size(), 0);
    }

    @Test
    public void whenSaveItemAndReplace() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        Item newItem = new Item("newItem");
        item = tracker.add(item);
        boolean res = tracker.replace(item.getId(), newItem);
        assertTrue(res);
        newItem.setId(item.getId());
        Item itemRes = tracker.findById(item.getId());
        assertEquals(itemRes, newItem);
    }
}
