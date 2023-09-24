package com.pridepoints.api.services;

import com.pridepoints.api.DTO.Avaliacao.AvaliacaoDTO;
import com.pridepoints.api.DTO.Avaliacao.AvaliacaoMapper;
import com.pridepoints.api.DTO.Evento.EventoCriacaoDTO;
import com.pridepoints.api.DTO.Evento.EventoDTO;
import com.pridepoints.api.DTO.Evento.EventoMapper;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Evento;
import com.pridepoints.api.repositories.EmpresaRepository;
import com.pridepoints.api.repositories.EventoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public List<EventoDTO> listarEventos() {
        List<Evento> result = eventoRepository.findAll();

            return EventoMapper.ofListDtos(result);
        }

    @Transactional
    public EventoDTO adicionarEvento(EventoCriacaoDTO eventoCriacaoDTO, Long idEmpresa) {
        Optional<Empresa> result = empresaRepository.findById(idEmpresa);

        if(result.isPresent()){
            Evento eventoMapeado = EventoMapper.of(eventoCriacaoDTO);
            Empresa empresa = result.get();

            eventoMapeado.setEmpresa(empresa);
            empresa.adicionarEvento(eventoMapeado);

            empresaRepository.save(empresa);

            return EventoMapper.of(eventoRepository.save(eventoMapeado));
        }

        return null;
    }
    @Transactional
    public EventoDTO atualizarEvento(EventoCriacaoDTO eventoAtualizado, Long idEmpresa, Long idEvento) {
        Optional<Empresa> result = empresaRepository.findById(idEmpresa);

        if(result.isPresent()){
            Empresa empresa = result.get();
            boolean exists = eventoRepository.existsById(idEvento);

            if(exists){
                Evento eventoMapeado = EventoMapper.of(eventoAtualizado);
                eventoMapeado.setId(idEvento);
                eventoMapeado.setEmpresa(empresa);

                return EventoMapper.of(eventoRepository.save(eventoMapeado));
            }
        }
        return null;
    }

    @Transactional
    public boolean removerEvento(Long idEvento) {
        Optional<Evento> resultEvent = eventoRepository.findById(idEvento);

        if(resultEvent.isPresent()){
            Evento evento = resultEvent.get();

            eventoRepository.delete(evento);

            return true;
        }

        return false;
    }

    public List<EventoDTO> listarEventosDaEmpresa(Long idEmpresa) {
        Optional<Empresa> result = empresaRepository.findById(idEmpresa);

        if(result.isPresent()){

            Empresa empresaBanco = result.get();

            List<EventoDTO> eventos = EventoMapper.ofListDtos(empresaBanco.getEventos());

            return eventos;
        }

        return null;
    }

    public EventoDTO buscarEventoPorId(Long idEmpresa, Long idEvento) {
        Optional<Empresa> result = empresaRepository.findById(idEmpresa);
        Optional<Evento> resultEvent = eventoRepository.findById(idEvento);

        if(result.isPresent()){
            Empresa empresaBanco = result.get();
            Evento eventoBanco = resultEvent.get();

            for(Evento evento : empresaBanco.getEventos()){
                if(evento.equals(eventoBanco)){
                    return EventoMapper.of(eventoBanco);
                }
            }
        }
        return null;
    }
}
