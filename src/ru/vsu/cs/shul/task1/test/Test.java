package ru.vsu.cs.shul.task1.test;

import ru.vsu.cs.shul.task1.trie.Trie;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Trie obj = new Trie();

        obj.insert("abcdefg");

        System.out.println(obj.containsNode("abcdefg"));
        List<String> a = obj.findWordsWithPrefix("abcde");

        System.out.println(a);

    }



}
