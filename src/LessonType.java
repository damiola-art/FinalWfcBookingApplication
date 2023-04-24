import java.util.ArrayList;
import java.util.HashMap;

public class LessonType {
    private double price;
    private String fitnessActivity;
    private HashMap<Integer, ArrayList<Lesson>> lessonsByMonth;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFitnessActivity() {
        return fitnessActivity;
    }

    public void setFitnessActivity(String fitnessActivity) {
        this.fitnessActivity = fitnessActivity;
    }

    public LessonType(double price, String fitnessActivity) {
        this.price = price;
        this.fitnessActivity = fitnessActivity;
        this.lessonsByMonth = new HashMap<Integer, ArrayList<Lesson>>();
    }

    public void addLesson(Lesson lesson){
        this.lessonsByMonth.putIfAbsent(lesson.getMonth(), new ArrayList<Lesson>());
        this.lessonsByMonth.get(lesson.getMonth()).add(lesson);
    }

    public double getTotalEarningPerMonth(int month){
        ArrayList<Lesson> lessonsList = this.lessonsByMonth.get(month);
        double totalAttendance = 0;
        for(Lesson lesson : lessonsList){
            totalAttendance += lesson.getNumberOfAttendance();
        }
        return totalAttendance * this.getPrice();
    }
}
