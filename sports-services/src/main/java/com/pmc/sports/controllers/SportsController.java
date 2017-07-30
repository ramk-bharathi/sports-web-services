package com.pmc.sports.controllers;

import com.pmc.sports.beans.NewSportsResponseBean;
import com.pmc.sports.beans.SportsBean;
import com.pmc.sports.services.SportsServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sports")
public class SportsController {
    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public Map<String, Object> getVersion(){
        Map<String, Object> version = new HashMap<>();
        version.put("version", 1.0);
        return version;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public SportsBean getSport(@PathVariable("id") int id){
        SportsServices services = new SportsServices();
        SportsBean bean = services.getSport(id);
        return bean;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<?> newSport(@RequestBody SportsBean bean){
        SportsServices services = new SportsServices();
        NewSportsResponseBean responseBean = services.newSports(bean);
        if(responseBean!=null){
            return new ResponseEntity<NewSportsResponseBean>(responseBean, HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }
}
