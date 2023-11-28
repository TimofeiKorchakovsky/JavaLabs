import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLParser {
    public static void main(String[] args) {
        String inputHTMLFile = "input1.html";
        String inputTextFile = "input2.in";
        String outputTagsFile = "output1.out";
        String outputLineNumbersFile = "output2.out";
        String outputNotFoundFile = "output3.out";

        try {
            // Чтение HTML-страницы из файла
            String htmlContent = readFromFile(inputHTMLFile);

            // Чтение списка фрагментов текста для поиска
            List<String> searchStrings = readSearchStringsFromFile(inputTextFile);

            // Поиск тегов и запись в выходной файл
            List<String> tags = findTags(htmlContent);
            writeToFile(outputTagsFile, tags);

            // Поиск позиций строк с искомыми фрагментами и запись в выходной файл
            List<Integer> lineNumbers = findLineNumbers(htmlContent, searchStrings);
            writeToFile(outputLineNumbersFile, lineNumbers);

            // Поиск ненайденных фрагментов и запись в выходной файл
            List<String> notFoundStrings = findNotFoundStrings(htmlContent, searchStrings);
            writeToFile(outputNotFoundFile, notFoundStrings);

            System.out.println("Результаты выполнения задач записаны в файлы: " +
                    outputTagsFile + ", " +
                    outputLineNumbersFile + ", " +
                    outputNotFoundFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Чтение данных из файла
    private static String readFromFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }

    // Чтение списка фрагментов текста для поиска
    private static List<String> readSearchStringsFromFile(String fileName) throws IOException {
        List<String> searchStrings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                searchStrings.add(line);
            }
        }
        return searchStrings;
    }

    // Запись данных в файл
    private static void writeToFile(String fileName, List<?> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Object item : data) {
                writer.write(item.toString());
                writer.newLine();
            }
        }
    }

    // Поиск тегов в HTML-странице
    private static List<String> findTags(String htmlContent) {
        List<String> tags = new ArrayList<>();
        Pattern pattern = Pattern.compile("<(.*?)>");
        Matcher matcher = pattern.matcher(htmlContent);
        while (matcher.find()) {
            String tag = matcher.group(1);
            tags.add(tag);
        }
        tags.sort(Comparator.comparingInt(String::length));
        return tags;
    }

    // Поиск номеров строк с искомыми фрагментами в HTML-странице
    private static List<Integer> findLineNumbers(String htmlContent, List<String> searchStrings) {
        List<Integer> lineNumbers = new ArrayList<>();
        for (String searchString : searchStrings) {
            String lowercaseHTML = htmlContent.toLowerCase();
            String lowercaseSearchString = searchString.toLowerCase();
            int lineNumber = lowercaseHTML.indexOf(lowercaseSearchString);
            lineNumbers.add(lineNumber);
        }
        return lineNumbers;
    }

    // Поиск ненайденных фрагментов в HTML-странице
    private static List<String> findNotFoundStrings(String htmlContent, List<String> searchStrings) {
        List<String> notFoundStrings = new ArrayList<>();
        for (String searchString : searchStrings) {
            String lowercaseHTML = htmlContent.toLowerCase();
            String lowercaseSearchString = searchString.toLowerCase();
            if (!lowercaseHTML.contains(lowercaseSearchString)) {
                notFoundStrings.add(searchString);
            }
        }
        return notFoundStrings;
    }
}