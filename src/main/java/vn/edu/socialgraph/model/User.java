package vn.edu.socialgraph.model;

import java.util.Objects;

/** Người dùng là một đỉnh của đồ thị. ID là duy nhất và không thay đổi. */
public final class User {
    private final String id;
    private String name;

    public User(String id, String name) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("ID không được để trống");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Tên không được để trống");
        this.id = id.trim();
        this.name = name.trim();
    }

    public String getId() { return id; }
    public String getName() { return name; }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Tên không được để trống");
        this.name = name.trim();
    }

    @Override public boolean equals(Object other) {
        return this == other || other instanceof User user && id.equals(user.id);
    }

    @Override public int hashCode() { return Objects.hash(id); }
    @Override public String toString() { return name + " (" + id + ")"; }
}
