package com.gonnect.cleansing.rules.app.api;

import com.gonnect.cleansing.rules.app.model.Person;
import com.gonnect.cleansing.rules.app.service.DataCleansingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/datacleansing")
@Api("APIs to demonstrate Drools for creating data cleansing rules")
public class DataCleansingRestApis {

    @Autowired
    private DataCleansingService<Person> dataCleansingService;


    @PostMapping(value = "/persons", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public List<Person> valiate(@RequestBody List<Person> persons) {

        List<Person> validatedPersons = dataCleansingService.evaluate(persons);

        return validatedPersons;
    }

    @GetMapping("/version")
    public String version() {

        return "0.0.1";
    }

}
