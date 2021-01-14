package jr;

import java.io.*;
import java.net.*;
import java.util.*;



public class Solution {
    public static void main(String[] args) throws Exception {
	getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
	try {
	    Socket s=new Socket(url.getHost(), 80);
	    BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
	    OutputStream os=s.getOutputStream();
	    os.write(("GET "+url.getPath()).getBytes());
	    os.write("\n".getBytes());
	    String line;
	    while((line=in.readLine())!=null) {
		System.out.println(line);
		line=in.readLine();
	    }
	    os.close();
	    in.close();
	   
	   /* HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	    connection.setRequestMethod("GET");

	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    String responseLine;

	    while ((responseLine = bufferedReader.readLine()) != null) {
		System.out.println(responseLine);
	    }
	    bufferedReader.close();*/

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
