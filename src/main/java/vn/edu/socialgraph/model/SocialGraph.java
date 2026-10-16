package vn.edu.socialgraph.model;

import java.util.*;

/** Đồ thị vô hướng: mỗi cạnh biểu diễn quan hệ bạn bè hai chiều. */
public final class SocialGraph {
    private final Map<String, User> users = new LinkedHashMap<>();
    private final Map<String, Set<String>> adjacency = new LinkedHashMap<>();

    public void addUser(User user) {
        Objects.requireNonNull(user, "user");
        if (users.containsKey(user.getId())) throw new IllegalArgumentException("ID đã tồn tại: " + user.getId());
        users.put(user.getId(), user);
        adjacency.put(user.getId(), new LinkedHashSet<>());
    }

    public void updateUser(String id, String newName) { requireUser(id).setName(newName); }

    public void removeUser(String id) {
        requireUser(id);
        adjacency.get(id).forEach(friend -> adjacency.get(friend).remove(id));
        adjacency.remove(id);
        users.remove(id);
    }

    public void connect(String firstId, String secondId) {
        requireUser(firstId); requireUser(secondId);
        if (firstId.equals(secondId)) throw new IllegalArgumentException("Không thể tự kết bạn");
        adjacency.get(firstId).add(secondId);
        adjacency.get(secondId).add(firstId);
    }

    public void disconnect(String firstId, String secondId) {
        requireUser(firstId); requireUser(secondId);
        adjacency.get(firstId).remove(secondId);
        adjacency.get(secondId).remove(firstId);
    }

    public User requireUser(String id) {
        User user = users.get(id);
        if (user == null) throw new IllegalArgumentException("Không tìm thấy người dùng: " + id);
        return user;
    }

    public Optional<User> findUser(String id) { return Optional.ofNullable(users.get(id)); }
    public Collection<User> getUsers() { return Collections.unmodifiableCollection(users.values()); }

    public Set<String> neighborIds(String id) {
        requireUser(id);
        return Collections.unmodifiableSet(adjacency.get(id));
    }

    public List<User> friendsOf(String id) {
        return neighborIds(id).stream().map(users::get).toList();
    }

    public List<User> commonFriends(String firstId, String secondId) {
        requireUser(firstId); requireUser(secondId);
        return adjacency.get(firstId).stream()
                .filter(adjacency.get(secondId)::contains).map(users::get).toList();
    }

    public void clear() {
        users.clear();
        adjacency.clear();
    }
}
