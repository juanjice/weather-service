package com.weatherexample.consumeapi.weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class WeatherController {
    //use rest template to consume public api, and inyect the appropiate bean
    @Autowired
    private RestTemplate restTemplate;
    private final String API_KEY="1814d49f2dd7f3a9d477e486ee113895";
    //pdf tell us that only Bogota information is required
    private static String url ="https://api.openweathermap.org/data/2.5/weather?q=Bogota";
    //end point for the rest going to be /weather
    @GetMapping("/weather")
    @ResponseBody
    public Object getWeather(@RequestParam(value = "units",defaultValue = "standard") Optional<String> units,
                             @RequestParam(value = "lang",defaultValue = "es") Optional<String> lang){
        //build UriComponentsBuilder with the static metod from UriString, and add the params in the request
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                                        .queryParam("appid",API_KEY)
                                        .queryParam("units",units)
                                        .queryParam("lang",lang);
        //get template from uri
        Object response = restTemplate.getForObject(builder.toUriString(),Object.class);
        //return an object
        return response;
    }
    

}
