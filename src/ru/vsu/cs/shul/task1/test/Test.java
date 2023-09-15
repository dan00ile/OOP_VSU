package ru.vsu.cs.shul.task1.test;

import ru.vsu.cs.shul.task1.trie.Trie;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Trie obj = new Trie();

        obj.insert("abcdefg");

        String inputFilename = "test.txt";

        String arr2 = String.valueOf(Path.of(inputFilename));

        obj.addWordsFromFile(arr2);



        System.out.println(obj.containsNode("abcdefg"));
        List<String> a = obj.findWordsWithPrefix("жив");

        System.out.println(a);

    }



}
