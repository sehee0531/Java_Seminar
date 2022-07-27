import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class JavaSerialFile {
    public static void main(String[] args) {
        JavaSerialFile manage = new JavaSerialFile();
        String fullPath = "C:/Users/tpgml/Desktop/test.txt";

        JavaSerialSample smp = new JavaSerialSample("JungSeeHee", "000531", 
        "tpgml000531@abc.com", 23);
        manage.saveObject(fullPath, smp);
    }

    public void saveObject(String fullPath, JavaSerialSample smp) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(fullPath);
            oos = new ObjectOutputStream(fos); //객체를 직렬화, 역직렬화하는 메소드를 제공하는 스트림
            oos.writeObject(smp);
            System.out.println("Write Success");
        } catch (Exception e) { 
            e.printStackTrace();//단계별 에러출력 메시지
        } finally { //마지막에 반드시 실행해야 하는 코드
            if (oos != null) {
                try {
                    oos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

