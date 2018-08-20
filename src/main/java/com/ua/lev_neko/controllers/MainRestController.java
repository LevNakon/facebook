package com.ua.lev_neko.controllers;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.ua.lev_neko.dao.CustomerDAO;
import com.ua.lev_neko.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@RestController
public class MainRestController {

    @Autowired
    private CustomerDAO customerDAO;

    /*@PutMapping("/findUsers")
    public Set<Customer> findUsers(@RequestBody Param param ){
        String parametr = param.getParametr();
        String[] parametros = parametr.split(" ");
        if (parametros.length == 1) {
            String param1 = parametros[0];
            Set<Customer> allByName1 = UserController.finByOneParam(param1);
            return allByName1;
        } else if (parametros.length == 2){
            String p1 = parametros[0];
            String p2 = parametros[1];
            Set<Customer> list = UserController.findByTwoParam(p1, p2);
            return list;
        }else{
            return null;
        }
//        System.out.println("lololololololololololo");
//        String parametr = param.getParametr();
//        String[] parametros = parametr.split(" ");
//        List<String> list = new LinkedList<>();
//        list.addAll(Arrays.asList(parametros));
//        return list;
    }*/

}
