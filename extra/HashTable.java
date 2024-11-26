import java.util.LinkedList;

class UserProfile {
    String userID;
    String name;
    LinkedList<String> posts;
    LinkedList<String> comments;
    String preferences;

    UserProfile(String userID, String name, String preferences) {
        this.userID = userID;
        this.name = name;
        this.posts = new LinkedList<>();
        this.comments = new LinkedList<>();
        this.preferences = preferences;
    }

    void addPost(String post) {
        posts.add(post);
    }

    void addComment(String comment) {
        comments.add(comment);
    }
}

class HashTable {
    private int TABLE_SIZE = 128;
    private LinkedList<UserProfile>[] table;

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(String key) {
        int hashValue = key.hashCode();
        return Math.abs(hashValue % TABLE_SIZE);
    }

    public void insert(String key, UserProfile value) {
        int hashIndex = hash(key);
        for (UserProfile profile : table[hashIndex]) {
            if (profile.userID.equals(key)) {
                profile = value;
                return;
            }
        }
        table[hashIndex].add(value);
    }

    public UserProfile get(String key) {
        int hashIndex = hash(key);
        for (UserProfile profile : table[hashIndex]) {
            if (profile.userID.equals(key)) {
                return profile;
            }
        }
        return null;
    }

    public void remove(String key) {
        int hashIndex = hash(key);
        table[hashIndex].removeIf(profile -> profile.userID.equals(key));
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

        UserProfile user1 = new UserProfile("user123", "Alice", "Likes tech and gadgets");
        UserProfile user2 = new UserProfile("user456", "Bob", "Enjoys hiking and outdoor activities");

        user1.addPost("Excited about the new tech conference!");
        user1.addComment("Great article on AI advancements.");
        
        user2.addPost("Hiking up the mountains this weekend!");
        user2.addComment("Beautiful scenery!");

        hashTable.insert(user1.userID, user1);
        hashTable.insert(user2.userID, user2);

        UserProfile retrievedUser = hashTable.get("user123");
        if (retrievedUser != null) {
            System.out.println("User Profile for " + retrievedUser.name);
            System.out.println("Posts: " + retrievedUser.posts);
            System.out.println("Comments: " + retrievedUser.comments);
        } else {
            System.out.println("User not found!");
        }
    }
}
