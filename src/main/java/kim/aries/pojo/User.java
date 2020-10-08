package kim.aries.pojo;

import kim.aries.annotation.MyAnnotation;

/**
 * @Author aries
 * @Data 2020-09-26
 */
public class User {
    private Integer id;
    @MyAnnotation
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }
}
