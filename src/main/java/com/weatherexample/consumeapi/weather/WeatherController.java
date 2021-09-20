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

    @Autowired
    private RestTemplate restTemplate;
    private final String API_KEY="1814d49f2dd7f3a9d477e486ee113895";
    private static String url ="https://api.openweathermap.org/data/2.5/weather?q=Bogota";

    @GetMapping("/weather")
    @ResponseBody
    public Object getWeather(@RequestParam(value = "units",defaultValue = "standard") Optional<String> units,
                             @RequestParam(value = "lang",defaultValue = "es") Optional<String> lang){//
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                                        .queryParam("appid",API_KEY)
                                        .queryParam("units",units)
                                        .queryParam("lang",lang);
        Object response = restTemplate.getForObject(builder.toUriString(),Object.class);
        return response;
    }
    

}
