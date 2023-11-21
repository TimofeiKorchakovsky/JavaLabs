//Корчаковский Тимофей 5 группа
//3.Строка состоит из слов. За один просмотр символов строки найти все слова,
//начинающиеся с гласных букв,  и занести их в новую строку,
//заменяя первую букву каждого слова на прописную.
// Слова в исходной строке разделяются некоторым множеством разделителей.
//Слова в новой строке должны разделяться ровно одним пробелом
import java.lang.*;
import java.io.*;
import java.util.*;
import java.lang.String;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) {
        String str;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your string:") ;
        str = in.nextLine();
        String[] wordArray = str.split("[\\s,.:;!?_&]+");
        Pattern pattern = Pattern.compile("^[аоэиуыеёюяeaiouyАОЭИУЫЕЁЮЯEIOUY].*");
        String [] result=new String[wordArray.length];
        int j=0;
        for (int i = 0;i < wordArray.length;++i) {
            Matcher matcher = pattern.matcher(wordArray[i]);
            if (matcher.find()) {
                wordArray[i] = wordArray[i].substring(0, 1).toUpperCase() + wordArray[i].substring(1);
                result[j]=wordArray[i];
                ++j;
            }
        }
        System.out.print("Result: ");
        for(int i = 0;i < j;++i)
        {
            System.out.print(result[i] + " ");
        }

    }
}