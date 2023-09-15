package ru.vsu.cs.shul.task1.trie;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;

        for (char l : word.toCharArray()) {
            //current = current.getChildren().computeIfAbsent(l, c -> new TrieNode());
            if (!current.getChildren().containsKey(l)) {
                current.getChildren().put(l, new TrieNode());
            }
            current = current.getChildren().get(l);
        }
        current.setEndOfWord(true);
    }

    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    public boolean containsNode(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord();
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord()) {
                return false;
            }
            current.setEndOfWord(false);
            return current.getChildren().isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.getChildren().get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isEndOfWord();

        if (shouldDeleteCurrentNode) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }
        return false;
    }

    public List<String> findWordsWithPrefix(String prefix) {
        List<String> words = new ArrayList<>();

        TrieNode current = root;

        for (char l : prefix.toCharArray()) {
            TrieNode node = current.getChildren().get(l);
            if (node == null) {
                return words;
            }
            current = node;
        }

        findAllWordsWithPrefix(current, prefix, words);

        return words;
    }

    private void findAllWordsWithPrefix(TrieNode node, String prefix, List<String> words) {
        if (node.isEndOfWord()) {
            words.add(prefix);
        }

        for (Map.Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
            char ch = entry.getKey();
            TrieNode childNode = entry.getValue();

            findAllWordsWithPrefix(childNode, prefix + ch, words);
        }
    }

    public void addWordsFromFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            Pattern pattern = Pattern.compile("\\P{L}+");
            while ((line = reader.readLine()) != null) {
                String[] words = pattern.split(line);
                for (String word : words) {
                    if (!word.isEmpty()) {
                        this.insert(word.toLowerCase());
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


/*

public class Trie {

    private static class TrieNode {

        private final Map<Character, TrieNode> children;
        private boolean isWord;

        private TrieNode() {
            this.children = new TreeMap<>();
            this.isWord = false;
        }
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        TrieNode node = searchHelper(word);
        return node.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchHelper(prefix);




    }

    public TrieNode searchHelper(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                return new TrieNode();
            }
            curr = curr.children.get(c);
        }
        return curr;
    }


    public List<String> listWords() {
        List<String> list = new ArrayList<>();
        list(root, 0, "", list);
        return list;
    }
    private void list(TrieNode current, int id, String prefix, List<String> list) {
        if(current==null) return;
        for(int i=0; i<R; i++) {
            TrieNode child = current.children[i];
            if(child!=null) {
                String res = prefix + (char)i;
                if(child.isEnd) list.add(res);
                list(child, i, res, list);
            }
        }
    }







}

 */