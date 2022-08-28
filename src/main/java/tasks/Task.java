package tasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    protected String name;    //Название, кратко описывающее суть задачи
    protected String description;  //Описание, в котором раскрываются детали
    protected int id;   //УИН задачи
    protected Status status;  //Статус, отображающий её прогресс. ("NEW", "IN_PROGRESS", "DONE")
    protected TypeTask typeTask;
    protected LocalDateTime startTime;
    protected long duration;
    protected LocalDateTime endTime;
//    protected User user;


    public Task(int id, String name, String description, TypeTask typeTask, Status status, LocalDateTime startTime, long duration) { //для тестов
        this.id = id;
        this.name = name;
        this.description = description;
        this.typeTask = typeTask;
        this.status = status;
        this.startTime = startTime;
        this.duration = duration;
       }

    public Task(String name, String description, TypeTask typeTask, LocalDateTime startTime, long duration) {
        this.name = name;
        this.description = description;
        this.typeTask = typeTask;
        this.startTime = startTime;
        this.duration = duration;
        this.endTime = getEndTime();
    }

//    public Task(String name, String description, TypeTask typeTask, LocalDateTime startTime, long duration, User user) {
//        this.name = name;
//        this.description = description;
//        this.typeTask = typeTask;
//        this.startTime = startTime;
//        this.duration = duration;
//        this.user = user;
//    }
    public Task(String name, String description, TypeTask typeTask){
        this.name = name;
        this.description = description;
        this.typeTask = typeTask;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

//    public User getUser() {
//        return user;
//    }

//    public void setUser(User user) {
//        this.user = user;
//    }


    @Override
    public  String toString(){
        return  id + ","
                + typeTask.toString() + ","
                + name + ","
                + status.toString()
                + "," + description +  ","
                + (startTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)) + ","
                + duration + "," + (getEndTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)) ;
    }


    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public LocalDateTime getEndTime() {
        return startTime.plus(Duration.ofMinutes(duration));
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}

