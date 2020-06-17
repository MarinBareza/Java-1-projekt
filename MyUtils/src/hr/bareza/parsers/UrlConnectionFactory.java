/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.bareza.parsers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Bareza
 */
public class UrlConnectionFactory {
    
    public static HttpsURLConnection getHttpsUrlConnection(String path, int timeout, String requestMethod)
            throws MalformedURLException, IOException {
        URL url = new URL(path);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setConnectTimeout(timeout);
        con.setReadTimeout(timeout);
        con.setRequestMethod(requestMethod);
        con.addRequestProperty("User-Agent", "Mozilla/5.0");
        return con;
    }
}
