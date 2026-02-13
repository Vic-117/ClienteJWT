/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vPerez.ProgramacionNCapasNov2025.Configurations;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("logout")
public class LogoutController {

    @GetMapping
    public String salir(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        if (session != null && session.getAttribute("token") != null) {
            request.getSession().invalidate();
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
//            session.invalidate();

        }

        //Eliminar cookie para que no se guarde ninguna pagina
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/logout/exit";

    }
    
    @GetMapping("/exit")
    public String salir(){
        return "Logout";
    }

}
