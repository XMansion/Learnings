package org.deeaae.learnings.ironman;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.SourceTree;
import okhttp3.*;
import sun.net.www.http.HttpClient;

import javax.net.ssl.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import static java.lang.System.out;

public class Iterator {

    public Iterator () {
        keyMap = new ConcurrentHashMap<>();
    }
    public String ironmanID;
    int globalCounter =0;
    int requestSend=0;
    int requestRevievedd=0;
    int sucessRequest=0;
    public String filterID(String row) {
        try {
            String ret = row.substring(16, row.length() - 3);
            return ret;
        }catch (Exception ex){
            return null;
        }
    }

    public void writeData() {

        try(FileWriter fw = new FileWriter("/home/anunay/result.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outFile = new PrintWriter(bw))
        {
            outFile.println("Entry>>\n");
            keyMap.keySet().forEach(s -> {try {
                outFile.write(s);
            }catch (Exception  e){

            }});

            outFile.write("Total Count Keys" +keyMap.keySet().size()+ "\n");
            outFile.write("[Send " + requestSend + "][Recvd "+ requestRevievedd+"][Success "+sucessRequest+"]|| Global "+globalCounter);


        } catch (IOException e) {
            System.out.println("Writing error");
            //exception handling left as an exercise for the reader
        }

    }

    public void process(String line) {
        System.out.print(" | " + globalCounter + " | ");
        if(globalCounter%5000 ==0 ) {
            System.out.println(">>>>>>>>Writing Data<<<<<<<<");
            writeData();
            System.out.println(">>>>>>>>Data Written<<<<<<<<");
        }
        if(globalCounter%1000 == 0 ) {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String id = filterID(line);
        if(id!=null) {
            getAccountObject(id);
        }
        globalCounter++;
    }

    public void iterate(String filename) {
        FileReader fileReader = new FileReader();
        fileReader.filepath=filename;
        Stream stream =fileReader.getStream();
        stream.forEach(line -> process((String)line));
    }

    private static final TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }
    };
    private static final SSLContext trustAllSslContext;
    static {
        try {
            trustAllSslContext = SSLContext.getInstance("SSL");
            trustAllSslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }
    private static final SSLSocketFactory trustAllSslSocketFactory = trustAllSslContext.getSocketFactory();

    public static OkHttpClient trustAllSslClient(OkHttpClient client) {
        OkHttpClient.Builder builder = client.newBuilder();
        builder.sslSocketFactory(trustAllSslSocketFactory, (X509TrustManager)trustAllCerts[0]);
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        return builder.build();
    }

    OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient();
        OkHttpClient client2 = trustAllSslClient(client);
        return client2;

        }

    void getAccountObject(String accountID) {

        OkHttpClient client = getClient();
        Request request = new Request.Builder()
                .url("https://kong.ailiens.com:8443/account/"+accountID)
                .header("authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJRbzE5WS1WTVBqUGRtdUVveVZyWmRWQjR2ZmxPODlObzVLQjNmbldQbFZjIn0.eyJqdGkiOiI4OTI0NDA4Ny1hMWNmLTRjMDYtYmI5ZS01NzFjMmQyNTM2MTUiLCJleHAiOjE1MDM1ODQzNDYsIm5iZiI6MCwiaWF0IjoxNTAzNTY5OTQ2LCJpc3MiOiJodHRwczovL3NlY3VyZS5ubm5vdy5jb20vYXV0aC9yZWFsbXMvQUlMU2VjdXJlIiwiYXVkIjoib2RpbiIsInN1YiI6Ijc0Y2RjMTA3LWM4YzQtNGNmMC04ZTIxLThkZTc0MmM1ZTM1MyIsInR5cCI6IkJlYXJlciIsImF6cCI6Im9kaW4iLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiJlMWEzZWVlNC01MWFkLTRlMWEtYTNkMy1lMzZkMDczZjJiNGUiLCJhY3IiOiIxIiwiY2xpZW50X3Nlc3Npb24iOiI0ODVmYzNkYy1jNmRjLTRlMjItYjA1MS02OGNmZjA0NDliNTIiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cHM6Ly9hc2ljcy5ubm5vdy5jb20iLCJodHRwczovL3NtLm5ubm93LmNvbSIsImh0dHBzOi8vYXJyb3cubm5ub3cuY29tIiwiaHR0cHM6Ly9wcnltLmFpbGllbnMuY29tIiwiaHR0cHM6Ly9hZXJvcG9zdGFsZS5haWxpZW5zLmNvbSIsImh0dHBzOi8vdHJ1ZWJsdWUubm5ub3cuY29tIiwiaHR0cHM6Ly91bmxpbWl0ZWQubm5ub3cuY29tIiwiaHR0cHM6Ly9wcnltLm5ubm93LmNvbSIsImh0dHBzOi8vZWxsZS5ubm5vdy5jb20iLCJodHRwczovL25hdXRpY2EuYWlsaWVucy5jb20iLCJodHRwczovL2ZseWluZ21hY2hpbmUuYWlsaWVucy5jb20iLCJodHRwczovL3RoZWNoaWxkcmVuc3BsYWNlLm5ubm93LmNvbSIsImh0dHBzOi8vb2Rpbi5haWxpZW5zLmNvbSIsImh0dHBzOi8vZmx5aW5nbWFjaGluZS5ubm5vdy5jb20iLCJodHRwOi8vbG9jYWxob3N0OjgwODEiLCJodHRwczovL2Fycm93LmFpbGllbnMuY29tIiwiaHR0cHM6Ly91c3BvbG9hc3NuLmFpbGllbnMuY29tIiwiaHR0cHM6Ly9lbGxlLmFpbGllbnMuY29tIiwiaHR0cHM6Ly93d3cubm5ub3cuY29tIiwiaHR0cHM6Ly9lZGhhcmR5Lm5ubm93LmNvbSIsImh0dHBzOi8vYXNpY3MuYWlsaWVucy5jb20iLCJodHRwczovL2dhcC5ubm5vdy5jb20iLCJodHRwczovL2NhbHZpbmtsZWluLmFpbGllbnMuY29tIiwiaHR0cHM6Ly90aGVjaGlsZHJlbnNwbGFjZS5haWxpZW5zLmNvbSIsImh0dHBzOi8vdGhlLWNoaWxkcmVucy1wbGFjZS5haWxpZW5zLmNvbSIsImh0dHBzOi8vd3d3LnRydWVibHVlLm5ubm93LmNvbSIsImh0dHBzOi8vc2h1ZmZsZS5ubm5vdy5jb20iLCJodHRwczovL2Flcm9wb3N0YWxlLm5ubm93LmNvbSIsImh0dHBzOi8vc2h1ZmZsZS5haWxpZW5zLmNvbSIsImh0dHBzOi8vbG9jYWxob3N0OjgwODEiLCJodHRwczovL2dhbnQubm5ub3cuY29tIiwiaHR0cHM6Ly9vZmZlcnMubm5ub3cuY29tIiwiaHR0cHM6Ly90b21teWhpbGZpZ2VyLmFpbGllbnMuY29tIiwiaHR0cHM6Ly90b21teWhpbGZpZ2VyLm5ubm93LmNvbSIsImh0dHBzOi8vZWRoYXJkeS5haWxpZW5zLmNvbSIsImh0dHBzOi8vcmVhZHkubm5ub3cuY29tIiwiaHR0cHM6Ly9uYXV0aWNhLm5ubm93LmNvbSIsImh0dHA6Ly9zbS1wcm9kLm5ubm93LmNvbSIsImh0dHBzOi8vc20tcHJvZC5ubm5vdy5jb20iLCJodHRwczovL2NhbHZpbmtsZWluLm5ubm93LmNvbSIsImh0dHBzOi8vdW5saW1pdGVkLmFpbGllbnMuY29tIiwiaHR0cHM6Ly9ubm5vdy5jb20iLCJodHRwczovL2JlaW5ncmVhZHkubm5ub3cuY29tIiwiaHR0cDovL3NtLm5ubm93LmNvbSIsImh0dHBzOi8vc2VwaG9yYS5ubm5vdy5jb20iLCJodHRwczovL3VzcG9sb2Fzc24ubm5ub3cuY29tIiwiaHR0cHM6Ly9nYW50LmFpbGllbnMuY29tIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJST0xFX1dhcmVob3N1ZV9NTV9RQyIsIkRFRkVOREVSU19DQVRBTE9HVUUiLCJPTVNfVUlfV0hfU1VQRVJWSVNPUiIsIlBDTV9BRE1JTiIsIlJPTEVfTE1TX3BhcnRuZXJzIiwiQ19VU0lfQURNSU4iLCJPTVNfVUlfU1RPUkVfQUdFTlQiLCJST0xFX09QVElNVVNQUklNRV9BRE1JTiIsIlJPTEVfU3RvcmVfQWdlbnRzIiwiQ19PTVNfVUlfTU1fUElDSyIsIkNfTE1TX1BBUlRORVIiLCJST0xFX1dhcmVob3VzZV9QYWNraW5nIiwiU09NX0FHRU5UIiwiUk9MRV9JbWFnZV9FZGl0b3IiLCJQQ01fUVVBTElUWSIsIk9NU19VSV9XSF9QSUNLIiwiQ19TVE9SRV9BR0VOVFMiLCJDX09NU19VSV9XSF9QSUNLIiwiT01TX1VJX01NX1BJQ0siLCJERUZFTkRFUlNfV0FSRUhPVVNFIiwiUk9MRV9IT0xNRVNfVVNFUiIsIlNPTV9LSU9TSyIsIlJPTEVfRkNNX0FkbWluIiwiQ19MTVNfQURNSU4iLCJST0xFX1JPQklOSE9PRF9BRE1JTiIsIlJPTEVfQ3JlYXRpdmUiLCJST0xFX1NISUVMRCIsIlJPTEVfQ1JNX0FnZW50cyIsIlJPTEVfV2FyZWhvdXNlX05vcm1hbF9RQyIsIlJPTEVfU1RPUk1fVVNFUiIsIkNfT01TX1VJX1BBQ0tBR0UiLCJST0xFX0NNU19BZG1pbiIsIkRFRkVOREVSU19PTU5JIiwiUk9MRV9MTVNfVVNFUiIsIkRFRkVOREVSU19GSU5BTkNFIiwiUk9MRV9NZXJjaGFuZGl6aW5nIiwiUk9MRV9MTVNfVVNFUl9XSF9GVyIsIlJPTEVfR0FMQUNUVVNfVVNFUiIsIlJPTEVfRU5EVVNFUiIsIlJPTEVfV2FyZWhvc3VlX01NX1BpY2siLCJST0xFX1VOQ0xFU0NST09HRV9BRE1JTiIsIlJPTEVfRmxhc2giLCJST0xFX09NU19BRE1JTiIsIlJPTEVfUk9CSU5IT09EX1VTRVIiLCJQQ01fTUVSQ0hfQURNSU4iLCJQQ01fU0laRUNIQVJUIiwiUk9MRV9QQVlNRU5UX0FETUlOIiwiUk9MRV9BRE1JTiIsIlJPTEVfQXV0aG9yIiwiUk9MRV9MTVNfQURNSU4iLCJDX09NU19VSV9GSU5BTkNFIiwiT01TX1VJX0FETUlOIiwiUk9MRV9VTkNMRVNDUk9PR0VfVVNFUiIsIlJPTEVfR0FMQUNUVVNfQURNSU4iLCJDX09NU19VSV9NTV9RQyIsIlBDTV9DT05URU5UIiwiUk9MRV9RQV9BRE1JTiIsIlJPTEVfV2FyZWhvdXNlX1N1cGVydmlzb3IiLCJST0xFX1VTSV9BZG1pbiIsIkRFRkVOREVSU19TVE9SRSIsIlJPTEVfV2FyZWhvdXNlX05vcm1hbF9QaWNrIiwiUENNX0NSRUFUSVZFIiwiUk9MRV9QQVlNRU5UX1VTRVIiLCJST0xFX0FORE9fQURNSU4iLCJST0xFX1BDTV9BRE1JTiIsIlJPTEVfV2FyZWhvdXNlX1JldHVybnMiLCJQQ01fSU1BR0UiLCJST0xFX0JyYW5kcyIsIkNfT01TX1VJX1dIX1JFVFVSTiIsIlJPTEVfVEVTVEFETUlOIiwiUk9MRV9DSEVDS09VVF9VU0VSIiwiT01TX1VJX1BBQ0tBR0UiLCJDX0NSTV9BR0VOVFMiLCJDX0ZDTV9BRE1JTiIsIkRFRkVOREVSU19DUyIsIlJPTEVfRWRpdG9yIiwiUk9MRV9BSUxfU1RBRkYiLCJDX09NU19VSV9BRE1JTiIsIlJPTEVfUEVfTUFLRVIiLCJQQ01fTUVSQ0hBTkRJU0lORyIsIkNfT01TX1VJX1dIX1NVUEVSVklTT1IiLCJST0xFX1F1YWxpdHlfQXNzdXJhbmNlIiwiUk9MRV9Db250ZW50X0VkaXRvciIsIlJPTEVfT01TX0NPTEwiLCJPTVNfVUlfTU1fUUMiLCJST0xFX0lST05NQU5fQURNSU4iLCJQQ01fV0VCU0lURVVTRVIiLCJST0xFX0xNU19VU0VSX1dIX1JUIiwiUk9MRV9PUFRJTVVTUFJJTUVfVVNFUiIsIkRFRkVOREVSU19URUNIIiwiT01TX1VJX1dIX1JFVFVSTiIsIlJPTEVfQU5ET19VU0VSIiwiU3RvcmVfU3RhZmYiLCJPTVNfVUlfV0hfUUMiLCJST0xFX1NUT1JNX0FETUlOIiwiUk9MRV9SRURIT09EIiwiREVGRU5ERVJTX0xPR0lTVElDUyIsIlJPTEVfUEVfQ0hFQ0tFUiIsIlJPTEVfU2l6ZV9DaGFydCIsIlJPTEVfTE1TX0NSTSIsIk9NU19VSV9GSU5BTkNFIiwiUk9MRV9MTVNfVVNFUl9TVE9SRSIsIkNfT01TX1VJX1dIX1FDIiwiUk9MRV9DSEVDS09VVF9BRE1JTiIsIkRFRkVOREVSU19BRE1JTiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFuZG8iOnsicm9sZXMiOlsiUk9MRV9BTkRPX1VTRVIiLCJST0xFX0FORE9fQURNSU4iXX0sImdhbGFjdHVzIjp7InJvbGVzIjpbIlJPTEVfR0FMQUNUVVNfVVNFUiIsIlJPTEVfR0FMQUNUVVNfQURNSU4iXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJ2aWV3LXByb2ZpbGUiXX19LCJuYW1lIjoiTm9kZSBVc2VyIiwicHJlZmVycmVkX3VzZXJuYW1lIjoibm9kZXVzZXIiLCJnaXZlbl9uYW1lIjoiTm9kZSIsImZhbWlseV9uYW1lIjoiVXNlciIsImVtYWlsIjoibm9kZXVzZXJAYXJ2aW5kaW50ZXJuZXQuY29tIn0.C5WpKABiFjgXN5ZD3zK0Slm_4bsHkqD4PR8batyXVHcSqttpVBUGp7w8Mv56hD4YeMvgRyBwmwdqMg7z6liHcL78YLVgJsPvw6qUxG-01k-SEJtbkwiSl9yBxsGonvImiezvERFbpaZt60LxAOZNWb2DtI2e-bHKo7FgoEfQ7yaioPx2pEdr1WlthAXtIVAGRbylkEtyDacYnI6KQCYdZ0hmhZeoR1yiLzieSU7TJ9Bp1-msKq6f9oQMx4CvKoNCVKy54f7Pv8SHk0orXPU3H6ZBz6pt51f93QOZCWiAv7VcIwPtReHnO61YP44xaqAxCj2YuWr4BtcbWzBzhwttZw")
                .build();
        try {
            requestSend++;
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    out.println("Failed : " + call.request().url()+" : " +e.getMessage()+ "\n");
                    requestRevievedd++;
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //System.out.println(response.body().string());
                    if(response.code()!=200) {
                        out.println("Response failed with status code" + response.code() + "\n" );
                        out.println(response.body().string() + "\n");
                        requestRevievedd++;
                        return;
                    }
                    processAccount(response);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void processAccount(Response response) {
        sucessRequest++;

        JsonFactory factory = new JsonFactory();

        ObjectMapper mapper = new ObjectMapper(factory);
        try {
            JsonNode rootNode = mapper.readTree(response.body().string());
            JsonNode addressNode = rootNode.get("address");
            if(addressNode == null) {
                //out.println("Address is null");
                return;
            }
            addressNode=addressNode.get("SHIPPINGADDRESS");
            if(addressNode == null) {
                //out.println("Shipping Address is null");
                return;
            }
            java.util.Iterator<Map.Entry<String,JsonNode>>   shiipingFieldsIterator = addressNode.fields();
            while (shiipingFieldsIterator.hasNext()) {

                Map.Entry<String,JsonNode> field = shiipingFieldsIterator.next();
                java.util.Iterator<Map.Entry<String,JsonNode>> shiipingaddressFieldsIterator = field.getValue().fields();
                while (shiipingaddressFieldsIterator.hasNext()) {
                    String value= shiipingaddressFieldsIterator.next().getValue().toString();
                    if(value!=null) {
                        //System.out.println(value);
                        manageKeyMap(value);
                    }

                }
            }
            



        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    Map<String,String> keyMap;
    public void manageKeyMap(String data) {
        String[] array = data.split("");
        for (String s : array) {
            keyMap.put(s,s);
        }
    }

    public void end() {
        System.out.println("OUT>>>"+ "\n");
        keyMap.keySet().forEach(s -> System.out.print(s+ "\n"));
        System.out.println("Total Count Keys" +keyMap.keySet().size()+ "\n");
        System.out.println("[Send " + requestSend + "][Recvd "+ requestRevievedd+"][Success "+sucessRequest+"]|| Global "+globalCounter);

    }




}
