package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    HashSet<FilesProperty> found = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FilesProperty property = new FilesProperty(attrs.size(), file.toFile().getName());
        if (!found.add(property))  {
            System.out.println(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}