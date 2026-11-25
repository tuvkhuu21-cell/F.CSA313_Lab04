
package mn.edu.must.sqat;

public class GradeCalculator {

    // Нийт онооноос үсгэн үнэлгээ тооцох
    public String letterGrade(double score) {
        if (!Double.isFinite(score) || score < 0 || score > 100) {
            throw new IllegalArgumentException("Оноо 0-100 хооронд байх ёстой");
        }

        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    // Үнэлгээний бүрэлдэхүүн хэсгүүдийн нийлбэр оноо
    public double totalScore(double att, double lab,
                             double quiz1, double quiz2, double exam) {

        validateScore(att, 10);
        validateScore(lab, 40);
        validateScore(quiz1, 10);
        validateScore(quiz2, 10);
        validateScore(exam, 30);

        return att + lab + quiz1 + quiz2 + exam;
    }

    // Онооны зөвшөөрөгдөх хязгаарыг шалгах
    private void validateScore(double score, double max) {
        if (!Double.isFinite(score) || score < 0 || score > max) {
            throw new IllegalArgumentException(
                "Оноо 0-" + max + " хооронд байх ёстой"
            );
        }
    }
}
