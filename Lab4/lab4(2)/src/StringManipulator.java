//Корчаковский Тимофей 5 группа
//Разработать консольное приложение на Java.
//Постановка задачи
//Для выполнения заданий использовать регулярные выражения.
//Каждое задание реализовать в отдельном методе.
//Строку получать из текстового файла input.txt.
//Результат работы методов записывать в выходной текстовый файл output.txt.
//
//    1. Из заданной строки исключить символы, расположенные внутри круглых скобок. Сами скобки тоже должны быть исключены.
//
//    2. Из заданной строки удалить из каждой группы идущих подряд цифр, в которой более двух цифр, все цифры, начиная с третьей.
//
//    3. Из заданной строки удалить из каждой группы идущих подряд цифр все незначащие нули.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringManipulator {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try {
            String inputString = readFromFile(inputFile);

            String result1 = excludeCharactersInsideBrackets(inputString);

            String result2 = removeExtraDigits(inputString);

            String result3 = removeLeadingZeros(inputString);

            writeToFile(outputFile, result1, result2, result3);

            System.out.println("Результаты выполнения задач записаны в файл " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private static void writeToFile(String fileName, String... results) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String result : results) {
                writer.write(result);
                writer.newLine();
            }
        }
    }

    // Исключение символов внутри круглых скобок
    private static String excludeCharactersInsideBrackets(String inputString) {
        Pattern pattern = Pattern.compile("\\([^()]*\\)");
        Matcher matcher = pattern.matcher(inputString);
        return matcher.replaceAll("");
    }

    // Удаление лишних цифр
    private static String removeExtraDigits(String inputString) {
        Pattern pattern = Pattern.compile("\\d{3,}");
        Matcher matcher = pattern.matcher(inputString);
        return matcher.replaceAll(matchResult -> matchResult.group().substring(0, 2));
    }

    // Удаление незначащих нулей
    private static String removeLeadingZeros(String inputString) {
        Pattern pattern = Pattern.compile("(?<=\\D|^)0+(?=\\d)");
        Matcher matcher = pattern.matcher(inputString);
        return matcher.replaceAll("");
    }
}