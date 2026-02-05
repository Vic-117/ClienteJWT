/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vPerez.ProgramacionNCapasNov2025.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import vPerez.ProgramacionNCapasNov2025.ML.Result;
import vPerez.ProgramacionNCapasNov2025.ML.Usuario;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("login")
public class LoginController {
    
    @GetMapping
    public String cargar(Model model){
        model.addAttribute("Usuario", new Usuario());
        return "Login";
    }

    @PostMapping
    public String login(@ModelAttribute("Usuario") Usuario usuario, HttpSession sesion, Model model) {
        try {
            RestTemplate resTemplate = new RestTemplate();
            HttpEntity<Usuario> requestEntity = new HttpEntity<>(usuario);
            ResponseEntity<Result> response = resTemplate.exchange("http://localhost:8081/login", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<Result>() {
            });
            sesion.setAttribute("token", response.getBody().Object);
            return "redirect:/Usuario";
        }catch(Exception ex){
            return "Login";
        }

    }

}
