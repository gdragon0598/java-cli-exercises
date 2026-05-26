package tuan.com;

public class User1 implements Runnable {
    private final LibraryService libraryService;

    public User1 (LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public void run() {
        System.out.println("User 1 Run");
        for (int i = 0; i < 100; ++i) {
//            try {
//                Thread.sleep(400);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            libraryService.addNewBook("User1 - " + i, "User 1", "Desc");
        }
    }
}
