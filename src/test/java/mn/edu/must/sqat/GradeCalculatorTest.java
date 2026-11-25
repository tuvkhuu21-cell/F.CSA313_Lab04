
package mn.edu.must.sqat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeCalculatorTest {

    private final GradeCalculator calc = new GradeCalculator();

    @Test
    @DisplayName("95 оноо A үнэлгээтэй байх ёстой")
    void excellentGrade() {
        double score = 95;
        String actual = calc.letterGrade(score);
        assertEquals("A", actual);
    }

    @Test
    @DisplayName("85 оноо B үнэлгээтэй байх ёстой")
    void goodGrade() {
        double score = 85;
        String actual = calc.letterGrade(score);
        assertEquals("B", actual);
    }

    @Test
    @DisplayName("75 оноо C үнэлгээтэй байх ёстой")
    void averageGrade() {
        double score = 75;
        String actual = calc.letterGrade(score);
        assertEquals("C", actual);
    }

    @Test
    @DisplayName("65 оноо D үнэлгээтэй байх ёстой")
    void passingGrade() {
        double score = 65;
        String actual = calc.letterGrade(score);
        assertEquals("D", actual);
    }

    @Test
    @DisplayName("30 оноо F үнэлгээтэй байх ёстой")
    void failingGrade() {
        double score = 30;
        String actual = calc.letterGrade(score);
        assertEquals("F", actual);
    }

    @Test
    @DisplayName("90 оноо яг A үнэлгээтэй байх ёстой")
    void ninetyIsExactlyA() {
        double score = 90;
        String actual = calc.letterGrade(score);
        assertEquals("A", actual);
    }

    @Test
    @DisplayName("89.99 оноо B үнэлгээтэй байх ёстой")
    void justBelowNinety() {
        double score = 89.99;
        String actual = calc.letterGrade(score);
        assertEquals("B", actual);
    }

    @Test
    @DisplayName("Сөрөг оноонд алдаа үүсэх ёстой")
    void negativeScoreThrows() {
        double score = -1;
        assertThrows(IllegalArgumentException.class,
                () -> calc.letterGrade(score));
    }

    @Test
    @DisplayName("100-аас их оноонд алдаа үүсэх ёстой")
    void overHundredThrows() {
        double score = 101;
        assertThrows(IllegalArgumentException.class,
                () -> calc.letterGrade(score));
    }

    @Test
    @DisplayName("Бүх бүрэлдэхүүн дээд оноотой бол нийлбэр 100")
    void fullTotalScore() {
        double actual = calc.totalScore(10, 40, 10, 10, 30);
        assertEquals(100.0, actual);
    }

    @Test
    @DisplayName("Ирц сөрөг бол алдаа үүсэх ёстой")
    void negativeAttendanceThrows() {
        double att = -5;
        assertThrows(IllegalArgumentException.class,
                () -> calc.totalScore(att, 30, 8, 8, 20));
    }

    @Test
    @DisplayName("Лабораторийн оноо 40-өөс их бол алдаа үүсэх ёстой")
    void excessiveLabScoreThrows() {
        double lab = 41;
        assertThrows(IllegalArgumentException.class,
                () -> calc.totalScore(10, lab, 8, 8, 20));
    }
    
    @ParameterizedTest
    @CsvSource({
        "100, A",
        "95, A",
        "90, A",
        "89.99, B",
        "80, B",
        "79.99, C",
        "70, C",
        "69.99, D",
        "60, D",
        "59.99, F",
        "0, F"
    })
    @DisplayName("Үсгэн үнэлгээний хязгаарын утгуудыг шалгах")
    void letterGradeBoundaries(double score, String expected) {
        // Өгөгдөл бэлтгэх
        double input = score;

        // Үйлдэл гүйцэтгэх
        String actual = calc.letterGrade(input);

        // Үр дүн шалгах
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "10, 40, 10, 10, 30, 100",
        "5, 20, 5, 5, 15, 50",
        "0, 0, 0, 0, 0, 0",
        "10, 30, 8, 7, 25, 80"
    })
    @DisplayName("Нийт онооны өөр өөр хувилбаруудыг шалгах")
    void totalScoreCases(double att, double lab,
                         double quiz1, double quiz2,
                         double exam, double expected) {
        // Өгөгдөл бэлтгэх
        double attendance = att;

        // Үйлдэл гүйцэтгэх
        double actual = calc.totalScore(
                attendance, lab, quiz1, quiz2, exam);

        // Үр дүн шалгах
        assertEquals(expected, actual);
    }
}
