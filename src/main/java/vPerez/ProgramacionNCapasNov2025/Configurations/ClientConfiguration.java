/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vPerez.ProgramacionNCapasNov2025.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import vPerez.ProgramacionNCapasNov2025.Components.JwtInterceptor;

/**
 *
 * @author digis
 */
@Configuration
public class ClientConfiguration {
    
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        
        restTemplate.getInterceptors().add(new JwtInterceptor());
        return restTemplate;
    }
    
    
}
