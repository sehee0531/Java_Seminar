import java.io.Serializable;

public class JavaSerialSample implements Serializable {

    private String name;
    private transient String password;
    private String email;
    private int age;

    public JavaSerialSample(String name, String password, String email, int age) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    @Override
    public String toString() {
        return "JavaSerialSample{" +
                "Name ='" + name + '\'' +
                ", PassWord =" + password +
                ", Email =" + email +
                ", Age =" + age +
                '}';
    }
}

