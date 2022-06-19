public class Task {

    protected String name;    //Название, кратко описывающее суть задачи

    protected String description;  //Описание, в котором раскрываются детали.

    protected int id;   //УИН задачи

    protected Status status;  //Статус, отображающий её прогресс. ("NEW", "IN_PROGRESS", "DONE")

    Task(String name, String description){
        this.name = name;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

