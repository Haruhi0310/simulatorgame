/* MinimHelper class:
 *  - Uses Minim to read and get the audio file.
 *  - Uses try-catch to handle any runtime exception.
 */

package util;

import java.io.FileInputStream;
import java.io.InputStream;

public class MinimHelper {

    public String sketchPath( String fileName ) {
        return "assets/"+fileName;
    }

    public InputStream createInput(String fileName) {
        InputStream is = null;
        try{
            is = new FileInputStream(sketchPath(fileName));
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return is;
    }
}