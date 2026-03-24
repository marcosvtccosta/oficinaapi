package com.oficina.api.application;

import com.oficina.api.domain.entity.OrdemServico;
import com.oficina.api.domain.entity.OrdemServicoStatus;
import com.oficina.api.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService {
    private final OrdemServicoRepository ordemServicoRepository;

    @Autowired
    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
    }

    public Optional<OrdemServico> findById(Long id) {
        return ordemServicoRepository.findById(id);
    }

    public OrdemServico save(OrdemServico ordemServico) {
        return ordemServicoRepository.save(ordemServico);
    }

    public void deleteById(Long id) {
        ordemServicoRepository.deleteById(id);
    }

    public Optional<OrdemServico> atualizarStatusComRetorno(Long id, OrdemServicoStatus status) {
        try {
            Optional<OrdemServico> ordemOptional = ordemServicoRepository.findById(id);
            if (ordemOptional.isEmpty()) {
                return Optional.empty();
            }

            OrdemServico ordemServico = ordemOptional.get();
            ordemServico.setStatus(status);
            OrdemServico saved = ordemServicoRepository.save(ordemServico);
            return Optional.of(saved);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean atualizarStatus(Long id, OrdemServicoStatus status) {
        return atualizarStatusComRetorno(id, status).isPresent();
    }

    public List<OrdemServico> listarOrdensAbertasOrdenadas() {
        List<OrdemServicoStatus> statusExcluidos = List.of(
                OrdemServicoStatus.FINALIZADA,
                OrdemServicoStatus.ENTREGUE
        );

        return ordemServicoRepository.findByStatusNotIn(statusExcluidos)
                .stream()
                .sorted(
                        Comparator.comparingInt(this::prioridadeStatus)
                                .thenComparing(
                                        OrdemServico::getDataInicio,
                                        Comparator.nullsLast(Comparator.reverseOrder())
                                )
                )
                .toList();
    }

    private int prioridadeStatus(OrdemServico ordemServico) {
        if (ordemServico == null || ordemServico.getStatus() == null) {
            return Integer.MAX_VALUE;
        }

        return switch (ordemServico.getStatus()) {
            case EM_EXECUCAO -> 0;
            case AGUARDANDO_APROVACAO -> 1;
            case EM_DIAGNOSTICO -> 2;
            case RECEBIDA -> 3;
            default -> 4;
        };
    }
}
