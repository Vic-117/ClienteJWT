/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vPerez.ProgramacionNCapasNov2025.Components;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author digis
 */
public class JwtInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        //Extraccion de peticion http actual que recibió el controlador
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest currentRequest = attributes.getRequest();

        HttpSession session = currentRequest.getSession(false);
        if (session != null) {

            String token = (String) session.getAttribute("token");

            if (token != null) {
                String cleanToken = token.replace("Bearer ", "").trim();
                request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + cleanToken);
                request.getHeaders().remove(HttpHeaders.COOKIE);
                request.getHeaders().setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            }
        }

        return execution.execute(request, body);
    }

}
