package uff.dac.depraia.apidepraia.controllers;

import io.swagger.annotations.ApiOperation;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uff.dac.depraia.apidepraia.dto.QuiosqueDTO;
import uff.dac.depraia.apidepraia.model.Quiosque;
import uff.dac.depraia.apidepraia.repositories.PraiaRepository;
import uff.dac.depraia.apidepraia.repositories.QuiosqueRepository;
import uff.dac.depraia.apidepraia.util.Mensagem;

final String FORMATO_JSON_INVALIDO = "Formato JSON inválido, verifique e tente novamente";
final String PRAIA = "Praia";

@Controller
@RequestMapping("/quiosque")
public class QuiosqueController {

    @Autowired
    private QuiosqueRepository quiosqueRepo;
    @Autowired
    private PraiaRepository praiaRepo;
    
    @ApiOperation(value = "Adiciona 1 quiosque a uma praia")
    @PostMapping(path = "")
    public @ResponseBody
    Map<String, Boolean> addEntity(@NotNull @Valid @RequestBody QuiosqueDTO entity) {        
        try {
            // Busca praia pelo ID
            return praiaRepo.findById(entity.getPraia().getId())
                    .map(n -> {
                        // Preparar                        
                        Quiosque aux = entity.conversor(n);                      
                        // Salvar                   
                        quiosqueRepo.save(aux);
                        return Mensagem.sucesso(aux.getClass().getSimpleName(), 1);
                    })
                    .orElseGet(() -> {
                        return Mensagem.error(PRAIA, 4);
                    });
        } catch (NullPointerException e) {
            return Mensagem.error(FORMATO_JSON_INVALIDO, 5);
        }
    }

    @ApiOperation(value = "Lista todos os cadastrados")
    @GetMapping(path = "/todos")
    public @ResponseBody
    Iterable<Quiosque> getAll() {
        return quiosqueRepo.findAll();
    }

    @ApiOperation(value = "Busca 1 quiosque por ID")
    @GetMapping(path = "/{id}")
    public @ResponseBody
    Optional<Quiosque> getById(@PathVariable int id) {
        return quiosqueRepo.findById(id);
    }

    @ApiOperation(value = "Atualiza o nome de um quiosque")
    @PutMapping("/{id}")
    public @ResponseBody
    Map<String, Boolean> updateById(@NotNull @Valid @RequestBody QuiosqueDTO entity, @PathVariable int id) {
        try {
            // Busca praia pelo ID                                
            return praiaRepo.findById(entity.getPraia().getId()).map(n -> {
                // Busca no banco de dados
                return quiosqueRepo.findById(id)
                        .map(m -> {
                            // Preparar 
                            m.setNome(entity.getNome());
                            m.setPraia(n);
                            // Salvar                   
                            quiosqueRepo.save(m);
                            return Mensagem.sucesso(m.getClass().getSimpleName(), 2);
                        })
                        .orElseGet(() -> {
                            return Mensagem.error("Quiosque", 4);
                        });
            })
                    .orElseGet(() -> {
                        return Mensagem.error(PRAIA, 4);
                    });
        } catch (NullPointerException e) {
            return Mensagem.error(FORMATO_JSON_INVALIDO, 5);
        } catch (Exception e) {
            return Mensagem.error(e.getMessage(), 5);
        }
    }

    @ApiOperation(value = "Deleta 1 quiosque de uma praia")
    @DeleteMapping("/{id}")
    public @ResponseBody
    Map<String, Boolean> deleteById(@NotNull @Valid @RequestBody QuiosqueDTO entity, @PathVariable int id) {
        try {
            // Busca praia pelo ID                               
            return praiaRepo.findById(entity.getPraia().getId()).map(n -> {
                // Busca no banco de dados
                return quiosqueRepo.findById(id)
                        .map(m -> {
                            // Verifica se o usuario está incluido na praia informada
                            if(!Objects.equals(n.getId(), m.getPraia().getId())) {
                                return Mensagem.error(entity.getClass().getSimpleName() + " não está cadastrado na praia informada", 5);
                            }                          
                            // Salvar                        
                            quiosqueRepo.delete(m);
                            return Mensagem.sucesso(m.getClass().getSimpleName(), 3);
                        })
                        .orElseGet(() -> {
                            return Mensagem.error("Quiosque", 4);
                        });
            })
                    .orElseGet(() -> {
                        return Mensagem.error(PRAIA, 4);
                    });
        } catch (NullPointerException e) {
            return Mensagem.error(FORMATO_JSON_INVALIDO, 5);
        } catch (Exception e) {
            return Mensagem.error(e.getMessage(), 5);
        }
    }
}