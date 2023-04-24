import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;


public class Timetable {
    LessonType bodySculpt = new LessonType(10, "Bodysculpt");
    LessonType yoga = new LessonType(5, "Yoga");
    LessonType zumba = new LessonType(15, "Zumba");
    LessonType aquacise = new LessonType(20, "Aquacise");
    LessonType boxFit = new LessonType(25, "Box Fit");
    LessonType spin = new LessonType(30, "Spin");

    ArrayList<LessonType> allTypes = new ArrayList<LessonType>();


    HashMap<String, ArrayList<Lesson>> lessonsByDay;
    HashMap<String, ArrayList<Lesson>> lessonsByLessonType;
    HashMap<Integer, ArrayList<Lesson>> lessonsByMonth;

    public Timetable() {
        this.allTypes.add(bodySculpt);
        this.allTypes.add(yoga);
        this.allTypes.add(zumba);
        this.allTypes.add(aquacise);
        this.allTypes.add(boxFit);
        this.allTypes.add(spin);

        this.lessonsByDay = new HashMap<String, ArrayList<Lesson>>();
        this.lessonsByLessonType = new HashMap<String, ArrayList<Lesson>>();
        this.lessonsByMonth = new HashMap<Integer, ArrayList<Lesson>>();

        this.createNewLesson(bodySculpt, 1,1,"saturday", 1);
        this.createNewLesson(yoga, 1,1,"sunday", 2);
        this.createNewLesson(zumba, 1,2,"saturday", 1);
        this.createNewLesson(spin, 1,2,"sunday", 2);
        this.createNewLesson(zumba, 1,3,"saturday", 1);
        this.createNewLesson(spin, 1,3,"sunday", 2);
        this.createNewLesson(aquacise, 1,4,"saturday", 1);
        this.createNewLesson(boxFit, 1,4,"sunday", 2);
        this.createNewLesson(yoga, 2,5,"sunday", 1);
        this.createNewLesson(zumba, 2,5,"saturday", 2);
        this.createNewLesson(boxFit, 2,6,"sunday", 1);
        this.createNewLesson(spin, 2,6,"saturday", 2);
        this.createNewLesson(boxFit, 2,7,"sunday", 1);
        this.createNewLesson(spin, 2,7,"saturday", 2);
        this.createNewLesson(boxFit, 2,8,"sunday", 1);
        this.createNewLesson(spin, 2,8,"saturday", 2);













//        this.createNewLesson();
//        this.createNewLesson();
//        this.createNewLesson();
//        this.createNewLesson();



//      this.createNewLesson(bodySculpt, 1,2,"saturday", 1);
//        this.createNewLesson();
//        this.createNewLesson();
//        this.createNewLesson();
//        this.createNewLesson();
//        this.createNewLesson();
//        this.createNewLesson();
//        this.createNewLesson();
//        this.createNewLesson();
//        this.createNewLesson();
//        this.createNewLesson();
//        this.createNewLesson();
//        this.createNewLesson();
//        this.createNewLesson();
//        this.createNewLesson();
    }

    public void createNewLesson(LessonType type, int month, int weekNumber, String day, int period){
        Lesson lesson = new Lesson(type, month,weekNumber, day, period);
        this.lessonsByDay.putIfAbsent(day.toLowerCase(), new ArrayList<Lesson>());
        this.lessonsByLessonType.putIfAbsent(type.getFitnessActivity().toLowerCase(), new ArrayList<Lesson>());
        this.lessonsByMonth.putIfAbsent(month, new ArrayList<Lesson>());

        this.lessonsByDay.get(day.toLowerCase()).add(lesson);
        this.lessonsByLessonType.get(type.getFitnessActivity().toLowerCase()).add(lesson);
        this.lessonsByMonth.get(month).add(lesson);
        type.addLesson(lesson);
    }

    public ArrayList<Lesson> getLessonByDay(String day){
        return this.lessonsByDay.get(day.toLowerCase());
    }

    public ArrayList<Lesson> getLessonByType(String type){
        return this.lessonsByLessonType.get(type.toLowerCase());
    }

    public ArrayList<Lesson> getLessonByMonth(int month){
        return this.lessonsByMonth.get(month);
    }

    public String getMonthlyReport(int month){
        ArrayList<Lesson> lessonsList = this.lessonsByMonth.get(month);
        String report = "";
        for(Lesson lesson : lessonsList){
            report += lesson.toString() + "\n" + "Attendance: " + lesson.getNumberOfAttendance() + " | Average Rating: " + lesson.getAverageRating() + "\n";
        }
        return report;
    }

    public String getChampionsReport(int month){
        String report = "";
        ArrayList<Lesson> lessonsList = this.lessonsByMonth.get(month);
//        double yogaTotal = 0;
//        double zumbaTotal = 0;
//        double spinTotal = 0;
//        double boxFitTotal =0;
//        double
        LessonType championType = bodySculpt;
        for (LessonType type : allTypes){
            if(type.getTotalEarningPerMonth(month) > championType.getTotalEarningPerMonth(month)){
                championType = type;
            }
        }

        report += "Highest Earning Fitness Activity: " + championType.getFitnessActivity() + ". Total Earning: GBP " + championType.getTotalEarningPerMonth(month) + "\n";
        for (LessonType type : allTypes){
            if(!type.equals(championType)){
                report += type.getFitnessActivity() + ": GBP" + type.getTotalEarningPerMonth(month);
            }
        }

       return report;
    }

}
