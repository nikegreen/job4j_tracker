package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    private Connection cn;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection connection) {
        cn = connection;
    }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties.rename")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        Item resItem = null;
        try (PreparedStatement statement = cn.prepareStatement(
                "insert into items (name, created) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, item.getCreated());
            int count = statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    resItem = new Item(generatedKeys.getInt(1), item.getName(), item.getCreated());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resItem;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        try (PreparedStatement statement =
                     cn.prepareStatement("update items set name=?, created=? where id=?")) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, item.getCreated());
            statement.setInt(3, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement =
                     cn.prepareStatement("delete from items where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement("select * from items")) {
            Item item;
            try (ResultSet resultSet = statement.executeQuery()) {
                while ((item = getItemFromResultSet(resultSet)) != null) {
                    items.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement =
                     cn.prepareStatement("select * from items where name=?")) {
            statement.setString(1, key);
            Item item;
            try (ResultSet resultSet = statement.executeQuery()) {
                while ((item = getItemFromResultSet(resultSet)) != null) {
                    items.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try (PreparedStatement statement = cn.prepareStatement("select * from items where id=?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                item = getItemFromResultSet(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    private Item getItemFromResultSet(ResultSet resultSet) throws SQLException {
        Item item = null;
        if (resultSet.next()) {
            item = new Item(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getTimestamp("created")
            );
        }
        return item;
    }
}