package com.pridepoints.api.DTO.Evento;

import com.pridepoints.api.entities.Evento;

import java.util.List;
import java.util.stream.Collectors;

public class EventoMapper {

    public static EventoDTO of(Evento evento){
        EventoDTO eventoDTO = new EventoDTO();

        eventoDTO.setNome(evento.getNome());
        eventoDTO.setImgEvento(evento.getImgEvento());
        eventoDTO.setDescricaoEvento(evento.getDescricaoEvento());
        eventoDTO.setDtEvento(evento.getDtEvento());

        return eventoDTO;
    }

    public static List<EventoDTO> ofListDtos(List<Evento> eventos){

        List<EventoDTO> eventoDTOS;

        eventoDTOS = eventos.stream()
                .map(EventoDTO::new)
                .collect(Collectors.toList());

        return eventoDTOS;
    }
}
