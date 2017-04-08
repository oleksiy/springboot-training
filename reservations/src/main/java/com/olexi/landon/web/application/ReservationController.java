package com.olexi.landon.web.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by oblavat on 4/8/17.
 */
@Controller
@RequestMapping(value="/reservations")
public class ReservationController {

    @RequestMapping(method= RequestMethod.GET)
    public String getReservations(){
        return "reservations";
    }
}
