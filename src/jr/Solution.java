package jr;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
	try {
	    URL url = new URL("http://jsonplaceholder.typicode.com/posts/1");
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestProperty("User-Agent", "Test");
	    conn.setRequestMethod("GET");

	    if (conn.getResponseCode() != 200) {
		throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
	    }

	    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

	    String output;
	    System.out.println("Server output .... \n");
	    while ((output = br.readLine()) != null) {
		System.out.println(output);
	    }

	    conn.disconnect();

	} catch (IOException e) {
	    e.printStackTrace();
	}

    }
}