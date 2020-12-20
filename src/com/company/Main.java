package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        File input = new File("/home/joelz/IdeaProjects/updater/src/swatplus.txt");
        Scanner scanner = new Scanner(input);
        System.out.println("Please enter the directory to be updated: ");
        final File updateDir = new File(scanner.nextLine());
        System.out.println("Please enter the directory from which it should be updated: ");
        final File updatingDir = new File(scanner.nextLine());
        System.out.println("Please enter file names that are exempted from the update as a comma separated list:\n(if all files should be updated enter 'all')\n");
        String reply = scanner.nextLine();
        File[] sourceFiles;

        if (!reply.equals("all")) {
            sourceFiles =

                    FilesForFolderList(updatingDir, reply);
        } else {
            sourceFiles =

                    FilesForFolderList(updatingDir);
        }


        for (File source : sourceFiles) {

            File newFile = new File(updateDir + "/" + source.getName());

            if (newFile.createNewFile()) {
                System.out.println("New file created: " + newFile.getAbsolutePath());
            }
            else {
                System.out.println("The file already exists: " + newFile.getAbsolutePath());
            }

            Scanner sourceScanner = new Scanner(source);

            FileOutputStream fos = new FileOutputStream(newFile);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            /*
            if(newFile.getName().equals("cbn_zhang2.f90")) {
                System.out.println(source.getAbsolutePath());
            }
            */
            while (sourceScanner.hasNextLine()) {
                String line = sourceScanner.nextLine();
                /*
                if(newFile.getName().equals("cbn_zhang2.f90")) {
                    System.out.println("Printing this line to cbn_zhang2.f90" + line);
                }
                */
                bw.write(line);
                bw.newLine();
            }
            bw.close();

            //System.out.println("Source files for update: " + source.getName());
        }
    }

    public static File[] FilesForFolderList(File dir) {
        System.out.println("Am in the not skipping function");
        File[] output = new File[0];
        int numberOfFiles = 0;
        //Count number of files
        for (File current : dir.listFiles()) {
            if (!current.isDirectory()) {
                numberOfFiles++;
                output = Arrays.copyOf(output, numberOfFiles);
                output[numberOfFiles - 1] = current;
            }
        }
        return output;
    }

    public static File[] FilesForFolderList(File dir, String skipping) {
        System.out.println("Am in the skipping function");
        File[] output = new File[0];
        int numberOfFiles = 0;
        //Count number of files
        for (File current : dir.listFiles()) {
            if ((!current.isDirectory()) && (!skipping.contains(current.getName()))) {
                numberOfFiles++;
                output = Arrays.copyOf(output, numberOfFiles);
                output[numberOfFiles - 1] = current;
            }
        }
        return output;
    }
}