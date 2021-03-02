package dto;

/**
 * @author HuK
 * @version 1.0.0
 * @date 2021/3/2 14:51
 */
public class StudentDTO {
    String name;
    int id;
    int age;
    boolean male;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }
}

    