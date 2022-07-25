import java.util.*;
import java.io.*;


public class FindName{
    public static void main(String[] args)throws IOException{
        try {
    
            //file을 가져와 file 객체 생성
            File aText = new File("C:/Users/tpgml/Desktop/test.txt");
            
            //file 입력 스트림을 생성
            FileReader aReader = new FileReader(aText);
            
            //스트림으로 입력 버퍼를 생성
            BufferedReader aBufReader = new BufferedReader(aReader);
            
            List<String> aLines = new ArrayList<String>();
            
            String aLine = "";
            
            //텍스트 내용을 한 줄씩 읽어와 aLine에 담고, 이를 aLines에 add 함.
            while((aLine = aBufReader.readLine()) != null) {
                aLines.add(aLine);
            }
        
            //버퍼를 닫아줌 ( 파일을 닫아줌 )
            aBufReader.close();

            System.out.println("메모장 리스트 : " + aLines);

           /* Vector<String> name = new Vector<>(aLines);
            for (String str : aLines) {            
                System.out.println(str + " : " + Collections.frequency(name, str));
            }*/
            //hashmap을 쓴 경우
            
            /*HashMap<String,Integer> hashmap = new HashMap<String,Integer>();
            for(String key :aLines){
                hashmap.put(key, hashmap.getOrDefault(key, 0)+1);
            }
            System.out.println(hashmap);
*/
            //hashset을 쓴 경우
            List<String> set = new ArrayList<String>(aLines);       
            for (String str : set) {            
            System.out.println(str + " : " + Collections.frequency(aLines, str));
            }
            
        }
        catch(Exception e) 
        {
            //TODO
        }
    }
}