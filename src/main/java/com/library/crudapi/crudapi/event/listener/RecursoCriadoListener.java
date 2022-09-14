package com.library.crudapi.crudapi.event.listener;
import com.library.crudapi.crudapi.event.RecursoCriadoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.UUID;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {
    @Override
    public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent){
        HttpServletResponse response = recursoCriadoEvent.getResponse();
        UUID id =recursoCriadoEvent.getId();

        adicionarHeaderLocation(response,id);
    }

    private void adicionarHeaderLocation(HttpServletResponse response, UUID id) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(id).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}