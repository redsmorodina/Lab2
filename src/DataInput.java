import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class DataInput {




    public static Double getDouble (String wr){
        writeText(wr);
        String s = "";
        try{
            s=getString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Double value = Double.valueOf(s);
        return value;
    }


    private static void writeText(String wr){
        if (wr == null)
            System.out.print("Введіть дані: ");
        else
            System.out.print(wr);
    }

    public static Long getLong() throws IOException{
        String s = getString();
        Long value = Long.valueOf(s);
        return value;
    }

    public static char getChar() throws IOException{
        String s = getString();
        return s.charAt(0);
    }

    public static Integer getInt(String wr){
        writeText(wr);
        String s = "";
        try {
            s = getString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Integer value = Integer.valueOf(s);
        return value;

    }

    public static String getString() throws IOException{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static String getString(String wr) throws IOException{
        writeText(wr);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();

        String ans ="";
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i)!=' ') {
                for(int c = i; c<s.length(); c++) {
                    ans = ans+s.charAt(c);
                }
                return ans;
            }
        }
        return s;

    }

}



