package com.example.weather.controllers;

import com.example.weather.model.Weather;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class WeatherController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/")
    public String Weather(@RequestParam String cityQuery, Model model){
        Integer tempCelsius;
        String name;
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="
                    +cityQuery+"&APPID=897178061a91dabf006428405bc65968");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            String result = "";
            while ((line= br.readLine()) != null){
                result += line;
            }
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            name = jsonObject.get("name").toString();
            String main = jsonObject.get("main").toString();
            jsonParser.parse(main);
            jsonObject = (JSONObject) jsonParser.parse(main);
            double temp = Double.parseDouble(jsonObject.get("temp").toString());
            tempCelsius = (int) (temp - 273.15);
            System.out.println(temp);
        } catch (IOException e){
            throw new RuntimeException();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Weather weather = new Weather(name, tempCelsius);
        weather.setName(name);
        weather.setTempCelsius(tempCelsius);
        model.addAttribute("weather", weather);

        return "searchResult";
    }

    @GetMapping("/searchResult")
    public String searchResult(){
        return "searchResult";
    }



}
