package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                session.save(item);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean res = false;
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                session.createQuery(
                        "UPDATE Item SET name = :fName created = :fCreated WHERE id = :fId",
                                Item.class)
                        .setParameter("fName", item.getName())
                        .setParameter("fCreated", item.getCreated())
                        .setParameter("fId", id)
                        .executeUpdate();
                session.getTransaction().commit();
                res = true;
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
        return res;
    }

    @Override
    public boolean delete(int id) {
        boolean res = false;
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                session.createQuery(
                                "DELETE Item WHERE id = :fId")
                        .setParameter("fId", id)
                        .executeUpdate();
                session.getTransaction().commit();
                res = true;
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
        return res;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new  ArrayList<>();
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                Query<Item> query = session.createQuery("from Item", Item.class);
                session.getTransaction().commit();
                result.addAll(query.list());
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new  ArrayList<>();
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                Query<Item> query = session.createQuery("from Item as i where i.name = :fKey",
                                Item.class)
                        .setParameter("fKey", key);
                session.getTransaction().commit();
                result.addAll(query.list());
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
        return result;
    }

    @Override
    public Item findById(int id) {
        Item result = null;
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                Query<Item> query = session.createQuery("from Item as i where i.id = :fId",
                                Item.class)
                        .setParameter("fId", id);
                session.getTransaction().commit();
                result = query.uniqueResult();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
        return result;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}