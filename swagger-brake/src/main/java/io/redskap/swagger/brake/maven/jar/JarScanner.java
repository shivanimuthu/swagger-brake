package io.redskap.swagger.brake.maven.jar;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.collections4.EnumerationUtils;
import org.springframework.stereotype.Component;

@Component
public class JarScanner {
    public Optional<JarEntry> find(File jarFile, Predicate<JarEntry> criteria) throws IOException {
        Optional<JarEntry> result = Optional.empty();
        try (JarFile jar = new JarFile(jarFile)) {
            List<JarEntry> jarEntries = EnumerationUtils.toList(jar.entries());
            for (JarEntry entry : jarEntries) {
                if (criteria.test(entry)) {
                    result = Optional.of(entry);
                }
            }
        }
        return result;
    }
}
