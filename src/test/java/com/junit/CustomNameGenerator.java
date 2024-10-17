package com.junit;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;

public class CustomNameGenerator {

    public static class ReplaceLowerCamelCase implements DisplayNameGenerator {

        @Override
        public String generateDisplayNameForClass(Class<?> testClass) {
            return testClass.getSimpleName();
        }

        @Override
        public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
            return nestedClass.getSimpleName();
        }

        @Override
        public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
            return replaceCamelCase(testMethod.getName());
        }

        public String replaceCamelCase(String input) {
            String normalizedInput = input.replace("_", "")
                    .replace("given", "Given")
                    .replace("when", "When")
                    .replace("then", "Then");
            String[] words = splitByCharacterTypeCamelCase(normalizedInput);
            List<String> sb = new ArrayList<>();
            for (String word : words) {
                if (!word.equals("A") && word.equals(word.toUpperCase())) {
                    sb.add(word);
                } else {
                    sb.add(word.toLowerCase());
                }
            }

            return capitalize(join(sb, SPACE));
        }
    }

}
