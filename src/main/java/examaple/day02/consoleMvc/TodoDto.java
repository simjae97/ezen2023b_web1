package examaple.day02.consoleMvc;

public class TodoDto {
    private int id;
    private String content;
    private String deadline;

    private boolean state;

    public TodoDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public TodoDto(int id, String content, String deadline, boolean state) {
        this.id = id;
        this.content = content;
        this.deadline = deadline;
        this.state = state;
    }

    @Override
    public String toString() {
        return "TodoDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", deadline='" + deadline + '\'' +
                ", state=" + state +
                '}';
    }
}
