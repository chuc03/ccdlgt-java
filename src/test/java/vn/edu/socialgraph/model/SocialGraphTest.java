package vn.edu.socialgraph.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SocialGraphTest {
    @Test void friendshipIsUndirected() {
        SocialGraph graph = sampleGraph();
        assertEquals(List.of("u2"), ids(graph.friendsOf("u1")));
        assertTrue(graph.neighborIds("u1").contains("u2"));
        assertTrue(graph.neighborIds("u2").contains("u1"));
    }

    @Test void findsCommonFriends() {
        SocialGraph graph = sampleGraph();
        graph.connect("u1", "u3");
        assertEquals(List.of("u2"), ids(graph.commonFriends("u1", "u3")));
    }

    @Test void rejectsDuplicateUserAndSelfFriendship() {
        SocialGraph graph = sampleGraph();
        assertThrows(IllegalArgumentException.class, () -> graph.addUser(new User("u1", "Khác")));
        assertThrows(IllegalArgumentException.class, () -> graph.connect("u1", "u1"));
    }

    @Test void disconnectRemovesFriendshipFromBothUsers() {
        SocialGraph graph = sampleGraph();
        graph.disconnect("u1", "u2");
        assertFalse(graph.neighborIds("u1").contains("u2"));
        assertFalse(graph.neighborIds("u2").contains("u1"));
    }

    @Test void removeUserAlsoRemovesRelatedEdges() {
        SocialGraph graph = sampleGraph();
        graph.removeUser("u2");
        assertTrue(graph.findUser("u2").isEmpty());
        assertFalse(graph.neighborIds("u1").contains("u2"));
        assertFalse(graph.neighborIds("u3").contains("u2"));
    }

    @Test void updateUserKeepsIdAndFriendships() {
        SocialGraph graph = sampleGraph();
        graph.updateUser("u1", "An Nguyễn");
        assertEquals("An Nguyễn", graph.requireUser("u1").getName());
        assertEquals("u1", graph.requireUser("u1").getId());
        assertEquals(List.of("u2"), ids(graph.friendsOf("u1")));
    }

    @Test void duplicateConnectionDoesNotCreateDuplicateEdge() {
        SocialGraph graph = sampleGraph();
        graph.connect("u1", "u2");
        assertEquals(1, graph.neighborIds("u1").size());
        assertEquals(2, graph.neighborIds("u2").size());
    }

    @Test void clearRemovesAllData() {
        SocialGraph graph = sampleGraph();
        graph.clear();
        assertTrue(graph.getUsers().isEmpty());
        assertTrue(graph.findUser("u1").isEmpty());
        assertThrows(IllegalArgumentException.class, () -> graph.neighborIds("u1"));
    }

    private static List<String> ids(List<User> users) {
        return users.stream().map(User::getId).toList();
    }

    private static SocialGraph sampleGraph() {
        SocialGraph graph = new SocialGraph();
        graph.addUser(new User("u1", "An"));
        graph.addUser(new User("u2", "Bình"));
        graph.addUser(new User("u3", "Chi"));
        graph.addUser(new User("u4", "Dũng"));
        graph.connect("u1", "u2");
        graph.connect("u2", "u3");
        graph.connect("u3", "u4");
        return graph;
    }
}
