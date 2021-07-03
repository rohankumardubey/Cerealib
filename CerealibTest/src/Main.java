import com.cerealib.CLArray;
import com.cerealib.CLField;
import com.cerealib.CLObject;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Main {
    static Random random = new Random();

    static void printBytes(byte[] data) {
        for (int i = 0; i < data.length; ++i) {
            System.out.printf("0x%x ", data[i]);
        }
    }

    static void saveToFile(String path, byte[] data) {
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path));
            stream.write(data);
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int[] data = new int[50000];
        for (int i = 0; i < data.length; ++i) {
            data[i] = random.nextInt();
        }
        CLArray array = CLArray.Integer("RandomNumbers", data);
        CLField field = CLField.Integer("Integer", 8);

        CLObject object = new CLObject("Entity");
        object.addArray(array);
        object.addField(field);

        byte[] stream = new byte[object.getSize()];

        object.getBytes(stream, 0);
        saveToFile("test.cld", stream);

    }
}
