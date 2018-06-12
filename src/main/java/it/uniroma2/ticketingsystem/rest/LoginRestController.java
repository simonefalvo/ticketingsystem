package it.uniroma2.ticketingsystem.rest;

import it.uniroma2.ticketingsystem.controller.UtenteController;
import it.uniroma2.ticketingsystem.dao.UtenteDao;
import it.uniroma2.ticketingsystem.entity.Ruolo;
import it.uniroma2.ticketingsystem.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;


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

    @RequestMapping(value="/redirect_login")
    public ModelAndView redirect_login(){
        //ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utente user = utenteController.cercaPerUsername(auth.getName());
        Ruolo ruolo = user.getRuolo();
        if (ruolo.getName().equals("ADMIN")){
            return new ModelAndView("redirect:index").addObject("userName",user.getUsername());
        }else if (ruolo.getName().equals("USER")){
            return new ModelAndView("redirect:index").addObject("userName","USER");
        }
        return null;
    }

    @RequestMapping(value="/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value="/dashboard/dash")
    public ModelAndView dash(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dashboard/dash");
        return modelAndView;
    }

    @RequestMapping("/logout")
    public void exit(HttpServletRequest request, HttpServletResponse response) {
        // token can be revoked here if needed
        new SecurityContextLogoutHandler().logout(request, null, null);
        try {
            //sending back to client app
            response.sendRedirect(request.getHeader("referer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
