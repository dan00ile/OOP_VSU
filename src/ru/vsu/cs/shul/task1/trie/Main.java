package ru.vsu.cs.shul.task1.trie;

import org.apache.commons.cli.*;


import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Trie prefixTree = new Trie();

        Options options = new Options();
        options.addOption("a", "add", true, "Add a word to the tree");
        options.addOption("s", "search", true, "Search for a word in the tree");
        options.addOption("l", "listOfWords", true, "");
        options.addOption("i", "import", true, "Import words from file");
        options.addOption("q", "quit", false, "Quit the program");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        while (true) {
            String nextLine = getInputLine();

            try {
                cmd = parser.parse(options, nextLine.split("\\s+"));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                formatter.printHelp(pw, formatter.getWidth(), "prefix_tree.jar", null, options, formatter.getLeftPadding(), formatter.getDescPadding(), null, true);
                pw.flush();
                System.out.println(sw.toString());
                continue;
            }

            if (cmd.hasOption("q")) {
                System.out.println("Работа с деревом закончена.");
                break;
            }

            if (cmd.hasOption("l")) {
                String prefix = cmd.getOptionValue("l");

                List<String> a = prefixTree.findWordsWithPrefix(prefix);

                System.out.println(a);

            }

            if (cmd.hasOption("i")) {
                String fileName = cmd.getOptionValue("i");
                String arr2 = String.valueOf(Path.of(fileName));

                prefixTree.addWordsFromFile(arr2);
            }

            if (cmd.hasOption("a")) {
                String wordToAdd = cmd.getOptionValue("a");
                prefixTree.insert(wordToAdd);
            }

            if (cmd.hasOption("s")) {
                String wordToSearch = cmd.getOptionValue("s");
                boolean found = prefixTree.containsNode(wordToSearch);
                if (found) {
                    System.out.println("Слово найдено в дереве.");
                } else {
                    System.out.println("Слово не найдено в дереве.");
                }
            }
        }
    }

    private static String getInputLine() {
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}




