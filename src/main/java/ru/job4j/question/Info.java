package ru.job4j.question;

import java.util.Objects;

public class Info {

    private int added;
    private int changed;
    private int deleted;

    public Info(int added, int changed, int deleted) {
        this.added = added;
        this.changed = changed;
        this.deleted = deleted;
    }

    /* Геттеры, сеттеры, equals() & hashCode() */
    public int getAdded() {
        return added;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public int getChanged() {
        return changed;
    }

    public void setChanged(int changed) {
        this.changed = changed;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Info)) {
            return false;
        }
        Info info = (Info) o;
        return getAdded() == info.getAdded()
                && getChanged() == info.getChanged()
                && getDeleted() == info.getDeleted();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAdded(), getChanged(), getDeleted());
    }
}
