package tuan.com.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieNode<T> {
    public final Map<Character, TrieNode<T>> children = new HashMap<>();
    public final List<T> matchProduct = new ArrayList<>();
}
