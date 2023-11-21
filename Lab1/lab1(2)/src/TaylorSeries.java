//Корчаковский Тимофей 5 группа Разработать консольное приложение на Java.
//Функция представлена в виде своего ряда Тейлора. Вычислить приближённое значение суммы этого бесконечного ряда. Вычисления заканчивать, когда очередное слагаемое окажется по модулю меньше заданного числа . Вид этого числа определяется  следующим условием:
//  = 10-k, где k – натуральное число.
import java.math.BigDecimal;
import java.math.MathContext;

public class TaylorSeries {
    public static void main(String[] args) {
        BigDecimal x = new BigDecimal("0.5");
        int k = 5;

        BigDecimal epsilon = BigDecimal.ONE.divide(BigDecimal.TEN.pow(k));

        BigDecimal approximation = BigDecimal.ZERO;
        BigDecimal term = x.negate();

        BigDecimal n = BigDecimal.ONE;

        MathContext mc = new MathContext(100);

        while (term.abs().compareTo(epsilon) >= 0) {
            approximation = approximation.add(term);
            term = term.multiply(x).multiply(n).divide(n.add(BigDecimal.ONE), mc);
            n = n.add(BigDecimal.ONE);
        }

        System.out.println("Приближенное значение суммы ряда: " + approximation);
    }
}