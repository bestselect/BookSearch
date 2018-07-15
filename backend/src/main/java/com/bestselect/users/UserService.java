package com.bestselect.users;

import com.bestselect.error.Message;
import com.bestselect.utils.ParameterStringBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private UserRepositoty userRepositoty;

    public List<Users> getAllUsers(){
        List<Users> users = new ArrayList<>();
        return StreamSupport.stream(userRepositoty.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Users getUser(String id){
        return userRepositoty.findOne(id);
    }

    public void addUser(Users user){
        userRepositoty.save(user);
    }

    public Object loginUser(Map parameters){

        try {
            String content = httpGetRequest(parameters);

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(content);

            if(jsonObject.containsKey("id")) {
                String id = jsonObject.get("id") + "";

                JSONObject properties = (JSONObject) jsonObject.get("properties");
                String name = properties.get("nickname") + "";

                Users user = new Users(id, parameters.get("token") + "", name);

                userRepositoty.save(user);
                return user;
            }else{

                return new Message(jsonObject.get("msg") + "");
            }
        }catch(Exception e){
            e.printStackTrace();;
        }

        return new Message("Exception thrown. login fail." + "");

    }

    public String httpGetRequest(Map parameters) throws URISyntaxException, IOException, InterruptedException {

        String api_url = "https://kapi.kakao.com/v2/user/me";
        URL url = new URL(api_url);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        // header parameters
        String token = parameters.get("token") + "";
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + token);

        // res
        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        return content.toString();
    }
    public void deleteUser(String id){

    }

}
