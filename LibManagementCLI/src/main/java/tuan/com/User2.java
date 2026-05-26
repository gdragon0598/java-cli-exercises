package tuan.com;

public class User2 implements Runnable {
    private final LibraryService libraryService;

    public User2 (LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public void run() {
        System.out.println("User 2 Run");

        for (int i = 0; i < 100; ++i) {
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            libraryService.addNewBook("User2 - " + i, "User 2", "Desc");
        }
    }
}
