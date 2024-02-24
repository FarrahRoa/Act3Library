import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class moodTracker {
    private List<MoodEntry> moodEntries;


    public moodTracker() {
        this.moodEntries = new ArrayList<>();
    }


    public void addMood(String day, int rate) {
        MoodEntry moodEntry = new MoodEntry(day, rate);
        moodEntries.add(moodEntry);
    }


    public void displayAllMoods() {
        for (MoodEntry entry : moodEntries) {
            System.out.println("Day: " + entry.getDay() + ", Mood: " + entry.getMood());
        }
    }


    public double calculateAverageMood() {
        if (moodEntries.isEmpty()) {
            return 0.0;
        }


        int sum = 0;
        for (MoodEntry entry : moodEntries) {
            sum += entry.getRate();
        }


        return (double) sum / moodEntries.size();
    }


    public String findHappiestDay() {
        String happiestDay = "";
        int maxRate = Integer.MIN_VALUE;


        for (MoodEntry entry : moodEntries) {
            if (entry.getRate() > maxRate) {
                maxRate = entry.getRate();
                happiestDay = entry.getDay();
            }
        }


        return happiestDay;
    }


    public String findSaddestDay() {
        String saddestDay = "";
        int minRate = Integer.MAX_VALUE;


        for (MoodEntry entry : moodEntries) {
            if (entry.getRate() < minRate) {
                minRate = entry.getRate();
                saddestDay = entry.getDay();
            }
        }


        return saddestDay;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        moodTracker moodTracker = new moodTracker();


        System.out.print("Enter the number of mood entries: ");
        int numberOfEntries = scanner.nextInt();


        for (int i = 0; i < numberOfEntries; i++) {
            System.out.print("Enter day for entry: ");
            String day = scanner.next();


            System.out.print("Enter mood rate for entry " + (i + 1) + ": ");
            int rate = scanner.nextInt();


            moodTracker.addMood(day, rate);
        }


        System.out.println("\nAll Mood Entries:");
        moodTracker.displayAllMoods();


        System.out.println("\nAverage Mood: " + moodTracker.calculateAverageMood());
        System.out.println("Happiest Day: " + moodTracker.findHappiestDay());
        System.out.println("Saddest Day: " + moodTracker.findSaddestDay());


        scanner.close();
    }
}


class MoodEntry {
    private String day;
    private int rate;


    public MoodEntry(String day, int rate) {
        this.day = day;
        this.rate = rate;
    }


    public String getDay() {
        return day;
    }


    public int getRate() {
        return rate;
    }


    public String getMood() {
        if (rate >= 5) {
            return "Happiest";
        } else if (rate == 4) {
            return "Happy";
        } else if (rate == 3) {
            return "Eh";
        } else if (rate == 2) {
            return "Annoyed";
        } else if (rate == 1) {
            return "Angry";
        } else {
            return "Invalid mood rate";
        }
    }
    
}
