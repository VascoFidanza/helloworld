package com.iade.HelloWorld.controllers;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.iade.HelloWorld.models.CurricularUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/java/tester")
public class JavaTesterController {
    private Logger logger = LoggerFactory.getLogger(JavaTesterController.class);

    @GetMapping(path = "/author", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAuthor() {
        logger.info("Sending info about the author");
        String name = "Vasco Fidanza";
        String res = "";
        int number = 34589;
        double height = 1.73;
        boolean likesFootball = true;
        char firstLetterColor = 'R';

        String club = "";
        if (firstLetterColor == 'R') {
            club = "Benfica";
        } else if (firstLetterColor == 'G') {
            club = "Sporting";
        } else if (firstLetterColor == 'B') {
            club = "Porto";
        }

        res += "Done by " + name + " with number " + number + ". \n";
        res += "I am " + height + " tall and ";
        if (likesFootball) {
            res += "I am a fan of football\n";
        } else {
            res += "not a fan of football.";
        }
        if (likesFootball) {
            res += "My favorite club is " + club;
        }

        return res;
    }

    @GetMapping(path = "/access/{student}/{covid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getRequired(@PathVariable("student") boolean isStudent, @PathVariable("temperature") double hasCovid,
            @PathVariable("classType") String type) {

        return false;

    }

    // Array Methods

    private double grades[] = { 10, 13.7, 15 };
    private String ucs[] = { "FP", "BD", "POO" };

    @GetMapping(path = "/grades/range/{min}/{max}", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getGradeRange(@PathVariable("min") double min, @PathVariable("max") double max) {
        int count = 0;
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] > min && grades[i] < max) {
                // count = count + 1
                count++;
            }
        }

        return count;

    }

    // Array list and classes

    private ArrayList<CurricularUnit> units = new ArrayList<CurricularUnit>();

    @PostMapping(path = "/units", produces = MediaType.APPLICATION_JSON_VALUE)
    public CurricularUnit saveUnit(@RequestBody CurricularUnit unit) {
        logger.info("Added unit " + unit.getName());
        units.add(unit);
        return unit;
    }
    
    
    @GetMapping(path = "/units", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CurricularUnit> getUnits() {
        logger.info("Get "+units.size()+" Units");
        return units;
    }

    

    // Construir os métodos

    @GetMapping(path = "/units/range/{min}/{max}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CurricularUnit> getUnitsRange(@PathVariable("min") double min, @PathVariable("max") double max) {
        ArrayList<CurricularUnit> result = new ArrayList<>();

        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getGrade() >= min && units.get(i).getGrade() < max) {
                result.add(units.get(i));
            }
        }

        return result;

    }

    private double grades2[] = { 11, 10, 15 }; 

    @GetMapping(path = "/average", produces = MediaType.APPLICATION_JSON_VALUE)
    public String calcAverage() {
        
        int a = 0;
        int count2 = 0;

        for (int i = 0; i < grades2.length; i++) {
            a += grades2[i]; 
            count2 ++;       
        }
        
        double average = a/count2;
        return "A sua média é: " + average;

    }
    
    private double grades3[] = { 10, 13.7, 15, 18, 14.5, 12};

    @GetMapping(path = "/maximum", produces = MediaType.APPLICATION_JSON_VALUE)
    public String calcMaximum() {
        
        double b = 0;
        for (int i = 0; i < grades2.length; i++) {
            if (grades3[i] > b) {
                b = grades3[i];
            }
        }
        return "A nota mais alta é: " + b;
    }  

    private double grades4[] = { 10, 13.7, 15, };
    private String ucs2[] = { "FP", "BD", "POO" };

    @GetMapping(path = "/UCs/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String giveUcName(@PathVariable("name") String name) {
        
        boolean verifica = false;
        double nota = 0;
        for(int i = 0; i < ucs2.length; i++) {
           
            if (name.equals(ucs2[i])) {
                nota = grades4[i];
                verifica = true;
            }
        }
        
        if (!verifica) {
            return "Essa cadeira não existe";
        }
        
        
        return "A nota correspondente a essa cadeira é: " +nota;

    }


    private int sem[] = {1, 2, 3};
    private String ucs3[][] = {{"ASI", "CFM", "FP"}, {"POO", "CC", "BD"}, {"GPI", "BPM", "MCD"}};

    @GetMapping(path = "/semester/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String giveSemester(@PathVariable("numero") int numero) {

        for (int i = 0; i < sem.length; i++) {

            if (numero == sem[i]) {
                return "As cadeiras do "+sem[i]+"º semestre são: "+(ucs3[i])+".";

            } else {
                return "Esse semestre não existe. Este curso só tem 3 semestres.";
            }
        }

        return "";
        
    }
}