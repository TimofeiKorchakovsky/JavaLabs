/*Корчаковский Тимофей 5 группа
3.	Строки текстового файла input.txt состоят из слов, разделенных одним или несколькими пробелами.
Перед первым, а также после последнего слова строки пробелы могут отсутствовать.
Требуется определить строки, содержащие максимальное количество слов.
Если таких строк несколько, найти первые 10.
Результат вывести на консоль в форме, удобной для чтения.*/
import java.lang.*;
import java.io.*;
import java.util.*;
import java.lang.String;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Vector;
public class Main {
    public static void main(String[] args) {
        File file = new File("myFile.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {

            Vector<Integer> amountWords = new Vector<>();
            int maxWordsLine = 0;
            int numLine = 1;
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] wordArray = line.split("[\\s,.:;!?_&]+");
                amountWords.add(wordArray.length);
                if(wordArray.length > maxWordsLine)
                {
                    maxWordsLine=wordArray.length;
                }
            }
            System.out.print("IDs lines with max words: ");
            for(int i = 0; i < amountWords.size(); i++)
            {
                if(amountWords.elementAt(i) == maxWordsLine)
                {
                    System.out.print(i+1 + " ");
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}