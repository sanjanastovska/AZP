/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sanja.azp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Sanja
 */
@Controller
public class RoutingController {

    @GetMapping("/zakaziPregled")
    public String routeToZakaziPregled() {
        return "zakazi";
    }

    @GetMapping("/listaPregledi")
    public String routeToListPregled() {

        return "pregledi";
    }

    @GetMapping("/pregledtable")
    public String routeToPregTab() {
        return "pregleditable";
    }

    @GetMapping("/pregledView")
    public String routeToPregDet(@RequestParam("id") Long idNapregled) {
        return "pregdetail";
    }

    @GetMapping("/calendar")
    public String routeToCalendar() {
        return "calendar";

    }
}
