package ru.job4j.generic;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleIsMentor() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mentor"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Mentor"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mentor"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleIsMentor() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mentor"));
        store.add(new Role("1", "Master"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Mentor"));
    }

    @Test
    public void whenReplaceThenRoleIsMaster() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mentor"));
        store.replace("1", new Role("1", "Master"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Master"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mentor"));
        store.replace("10", new Role("10", "Master"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Mentor"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mentor"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleIsMentor() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mentor"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Mentor"));
    }
}