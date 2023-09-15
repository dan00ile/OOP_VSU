package ru.vsu.cs.shul.task1.trie;

import org.apache.commons.cli.*;


import java.io.*;

public class Main {

    public static void main(String[] args) {

        Trie prefixTree = new Trie();


        Options options = new Options();
        options.addOption("add", "add", true, "Add a word to the tree");
        options.addOption("s", "search", true, "Search for a word in the tree");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            formatter.printHelp(pw, formatter.getWidth(), "prefix_tree.jar", null, options, formatter.getLeftPadding(), formatter.getDescPadding(), null, true);
            pw.flush();
            System.out.println(sw.toString());
            return;
        }

        if (cmd.hasOption("add")) {
            String wordToAdd = cmd.getOptionValue("add");
            prefixTree.insert(wordToAdd);
        } else if (cmd.hasOption("s")) {
            String wordToSearch = cmd.getOptionValue("s");
            boolean found = prefixTree.containsNode(wordToSearch);
            if (found) {
                System.out.println("Слово найдено в дереве.");
            } else {
                System.out.println("Слово не найдено в дереве.");
            }
        } else {
            formatter.printHelp("prefix_tree.jar", options);
        }
    }

}




