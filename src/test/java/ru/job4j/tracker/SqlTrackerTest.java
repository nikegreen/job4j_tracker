package ru.job4j.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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
                "test.properties.rename")) {
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
        Item item1 = tracker.add(new Item("item1"));
        assertThat(tracker.findById(item1.getId()), is(item1));
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame2() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = tracker.add(new Item("item1"));
        Item item2 = tracker.add(new Item("item2"));
        tracker.delete(item2.getId());
        assertNull(tracker.findById(item2.getId()));
    }

    @Test
    public void whenSaveItemAndFindAll() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("item"));
        assertThat(tracker.findById(item.getId()), is(item));
        assertEquals(tracker.findAll(), List.of(item));
    }

    @Test
    public void whenSaveItemAndFindAll2() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = tracker.add(new Item("item1"));
        assertThat(tracker.findById(item1.getId()), is(item1));
        Item item2 = tracker.add(new Item("item2"));
        assertThat(tracker.findById(item2.getId()), is(item2));
        assertEquals(tracker.findAll(), List.of(item1, item2));
    }

    @Test
    public void whenSaveItemAndFindAll3() {
        SqlTracker tracker = new SqlTracker(connection);
        assertEquals(tracker.findAll(), new ArrayList<Item>());
    }

    @Test
    public void whenSaveItemAndFindByName() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("item"));
        assertEquals(tracker.findByName(item.getName()), List.of(item));
    }

    @Test
    public void whenSaveItemAndFindByName2() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = tracker.add(new Item("item"));
        Item item2 = tracker.add(new Item("item"));
        tracker.add(new Item("item3"));
        assertEquals(tracker.findByName(item1.getName()), List.of(item1, item2));
    }

    @Test
    public void whenSaveItemAndDelete() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("item"));
        assertTrue(tracker.delete(item.getId()));
        assertEquals(tracker.findByName(item.getName()).size(), 0);
    }

    @Test
    public void whenSaveItemAndDelete2() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("item"));
        assertTrue(tracker.delete(item.getId()));
        assertEquals(tracker.findByName(item.getName()).size(), 0);
        assertFalse(tracker.delete(item.getId()));
    }

    @Test
    public void whenSaveItemAndDelete3() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = tracker.add(new Item("item1"));
        Item item2 = tracker.add(new Item("item2"));
        assertTrue(tracker.delete(item1.getId()));
        assertEquals(tracker.findByName(item1.getName()).size(), 0);
        assertFalse(tracker.delete(item1.getId()));
        assertTrue(tracker.delete(item2.getId()));
        assertEquals(tracker.findByName(item2.getName()).size(), 0);
    }

    @Test
    public void whenSaveItemAndReplace() {
        SqlTracker tracker = new SqlTracker(connection);
        Item newItem = new Item("newItem");
        Item item = tracker.add(new Item("item"));
        assertTrue(tracker.replace(item.getId(), newItem));
        newItem.setId(item.getId());
        assertEquals(tracker.findById(item.getId()), newItem);
    }
}
