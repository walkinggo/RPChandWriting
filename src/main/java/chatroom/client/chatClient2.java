package chatroom.client;

public class chatClient2 {
    public static void main(String[] args) {
        new Thread(() -> {
            chatClient chatClient1 = new chatClient("127.0.0.1", 8899);
            chatClient1.run();
        })
                .start();
    }
}
