package com.gonnect.cleansing.rules.app.service;

import com.gonnect.cleansing.rules.app.model.Person;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DataCleansingService<Fact> {

    @Autowired
    private KieContainer kContainer;

    public List<Fact> evaluate(List<Fact> facts) {

        // Objects are passed by reference
        KieSession kieSession = kContainer.newKieSession();
        for (Fact fact: facts) {
            kieSession.insert(fact);
        }
        kieSession.fireAllRules();
        kieSession.dispose();

        return facts;
    }

    public static void main(String[] args) {

        Person person = new Person();
        person.setDob("15-06/1980");
        person.setFirstName("Gaurav");
        person.setLastName("Malhotra");

        System.out.println(person);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        boolean isValid = true;
        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(person.getDob());
            System.out.println(date);

        } catch (ParseException e) {

            e.printStackTrace();
            System.out.println("INVALID DATE");
            isValid = false;
        }

        if (isValid) {
            System.out.println("VALID DATE");
        }

        person.setValid(isValid);

    }
}
