package services;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsFileConcatService {
    public static void serviceMethod(HttpServletRequest request,
                                     HttpServletResponse response) throws IOException, ServletException {

        List<File> jsFileList = new ArrayList<File>();
        BufferedWriter bw = new BufferedWriter(new FileWriter("./src/main/webapp/src.js",false));

        jsFileList.add(new File("./src/main/webapp/home2.js"));

        bw.write("(function(){\n");
        for(File file : jsFileList) {
            BufferedReader br = null;

            try {
                br = new BufferedReader(new FileReader(file.getPath()));

                String sCurrentLine;

                while ((sCurrentLine = br.readLine() ) != null) {

                    bw.write(sCurrentLine+"\n");

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (br != null)
                        br.close();
                }
                catch (IOException ex) {

                    ex.printStackTrace();

                }
            }
        }
        bw.write("})();");
        bw.close();
    }
}
