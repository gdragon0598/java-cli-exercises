package tuan.com.utils;

public class BookData {
    public static class BookInfo {
        public final String title;
        public final String author;
        public final String description;

        public BookInfo(String title, String author, String description) {
            this.title = title;
            this.author = author;
            this.description = description;
        }
    }

    public static final BookInfo[] FAMOUS_BOOKS = new BookInfo[] {
            new BookInfo("Đắc Nhân Tâm", "Dale Carnegie", "Nghệ thuật ứng xử và giao tiếp giúp thu phục lòng người."),
            new BookInfo("Nhà Giả Kim", "Paulo Coelho", "Hành trình tìm kiếm kho báu của chàng chăn cừu Santiago."),
            new BookInfo("Bố Già", "Mario Puzo", "Bản anh hùng ca về thế giới ngầm mafia Corleone."),
            new BookInfo("Sapiens: Lược Sử Loài Người", "Yuval Noah Harari", "Lịch sử tiến hóa của loài người từ thời tiền sử đến hiện đại."),
            new BookInfo("Đi Tìm Lẽ Sống", "Viktor E. Frankl", "Những trải nghiệm sinh tồn trong trại tập trung phát xít."),
            new BookInfo("Hoàng Tử Bé", "Antoine de Saint-Exupéry", "Câu chuyện ngụ ngôn đầy thơ mộng về tình bạn và tình yêu."),
            new BookInfo("Giết Con Chim Nhại", "Harper Lee", "Câu chuyện về công lý và nạn phân biệt chủng tộc ở miền Nam nước Mỹ."),
            new BookInfo("Chiến Tranh và Hòa Bình", "Leo Tolstoy", "Thiên sử thi vĩ đại khắc họa lịch sử nước Nga thời Napoleon."),
            new BookInfo("1984", "George Orwell", "Tiểu thuyết viễn tưởng cảnh báo về một thế giới độc tài toàn trị."),
            new BookInfo("Cha Giàu Cha Nghèo", "Robert Kiyosaki", "Những bài học thực tế về tư duy tài chính và đầu tư.")
    };
}
