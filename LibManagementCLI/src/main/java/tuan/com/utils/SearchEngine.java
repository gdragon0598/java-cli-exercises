package tuan.com.utils;

import tuan.com.model.Book;

import java.util.*;

public class SearchEngine {

    private final TrieNode<Book> root = new TrieNode<>();

    public void addBook(Book book) {
        String simpleTitle = book.getName().toLowerCase();
        for (byte i = 0; i < simpleTitle.length(); ++i) {
            if (i == 0 || simpleTitle.charAt(i - 1) == ' ') {
                String suffix = simpleTitle.substring(i);
                insertSuffixToTree(book, suffix);
            }
        }
    }

    private void insertSuffixToTree(Book book, String suffixStr) {
        TrieNode<Book> current = root;
        for (char c : suffixStr.toCharArray()) {
            if (!current.children.containsKey(c)) {
                current.children.put(c, new TrieNode<Book>());
            }
            current = current.children.get(c);

            if (!current.matchProduct.contains(book)) {
                current.matchProduct.add(book);
            }
        }
    }

    public List<Book> search(String title) {
        TrieNode<Book> current = root;
        String simpleTitle = title.toLowerCase();
        for (char c : simpleTitle.toCharArray()) {
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            }

            if (current == null)
                return new ArrayList<>();
        }
        return current.matchProduct;
    }

    public void addBooks(Map<Integer, Book> bookMap) {
        for (Book b : bookMap.values()) {
            this.addBook(b);
        }
    }
}