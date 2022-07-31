package tasks;

public class Task {

    protected String name;    //Название, кратко описывающее суть задачи
    protected String description;  //Описание, в котором раскрываются детали
    protected int id;   //УИН задачи
    protected Status status;  //Статус, отображающий её прогресс. ("NEW", "IN_PROGRESS", "DONE")
    protected Type type;

    public Task(String name, String description, Type type){
        this.name = name;
        this.description = description;
        this.type = type;
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

    @Override
    public  String toString(){
        return  id + "," + type.toString() + "," + name + "," + status.toString() + "," + description;
    }
}

