package com.example.CollegeScheduler;

public class Class extends ListItem  {
    private String className;
    private String professorName;
    private boolean[] meetingDates;
    private int startTime;
    private int endTime;

    public Class(String className, String professorName, boolean[] meetingDates, int startTime, int endTime) {
        this.className = className;
        this.professorName = professorName;
        this.meetingDates = meetingDates;
        this.startTime = startTime;
        this.endTime = endTime;
    }



    public String getClassName() {
        return className;
    }
    public String getProfessorName() {
        return professorName;
    }
    public boolean[] getMeetingDates() {
        return meetingDates;
    }
    public int getStartTime() {
        return startTime;
    }
    public int getEndTIme() {
        return endTime;
    }


    @Override
    public int compareTo(ListItem o) {
        Class item = ((Class) o);
        if (getSortingMethod() == 1) {
            return className.compareTo(item.getClassName());
        } else {
            for (int i = 0; i < 5; i++) {
                if(meetingDates[i] && !item.getMeetingDates()[i]) {
                    return -1;
                } else if(!meetingDates[i] && item.getMeetingDates()[i]) {
                    return 1;
                }
            }
            return startTime - item.getStartTime();
        }
    }
}
