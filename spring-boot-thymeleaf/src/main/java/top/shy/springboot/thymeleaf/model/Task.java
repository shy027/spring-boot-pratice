package top.shy.springboot.thymeleaf.model;

public class Task {
    private Long id;
    private String name;
    private Boolean complated;
    public Task(Long id, String name){
        this.id = id;
        this.name = name;
        this.complated = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getComplated() {
        return complated;
    }

    public void setComplated(Boolean complated) {
        this.complated = complated;
    }
}
