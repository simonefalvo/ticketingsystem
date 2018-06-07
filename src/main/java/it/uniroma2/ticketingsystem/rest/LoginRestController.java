package it.uniroma2.ticketingsystem.rest;

import it.uniroma2.ticketingsystem.controller.UtenteController;
import it.uniroma2.ticketingsystem.dao.UtenteDao;
import it.uniroma2.ticketingsystem.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class LoginRestController {

    @Autowired
    private UtenteController utenteController;

    @GetMapping(value={"/", "/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/admin/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utente user = utenteController.cercaPerUsername(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getNome() + " " + user.getCognome() + " (" + user.getUsername() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

}
