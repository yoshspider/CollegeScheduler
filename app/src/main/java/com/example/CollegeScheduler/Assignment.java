package com.example.CollegeScheduler;

public class Assignment extends ListItem {
    private String assignmentName;
    private Class classOfAssignment;
    private int monthDue;
    private int dayDue;

    public Assignment(String assignmentName, Class classOfAssignment, int monthDue, int dayDue) {
        this.assignmentName = assignmentName;
        this.classOfAssignment = classOfAssignment;
        this.dayDue = dayDue;
        this.monthDue = monthDue;

    }

//comment
    public String getAssignmentName() {
        return assignmentName;
    }

    public Class getClassOfAssignment() {
        return classOfAssignment;
    }

    public int getDayDue() {
        return dayDue;
    }

    public int getMonthDue() {
        return monthDue;
    }

    @Override
    public int compareTo(ListItem o) {
        return ((Assignment)o).getClassOfAssignment().compareTo(classOfAssignment);
    }
}
