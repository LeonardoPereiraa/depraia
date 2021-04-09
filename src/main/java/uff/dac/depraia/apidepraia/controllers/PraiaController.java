package uff.dac.depraia.apidepraia.controllers;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uff.dac.depraia.apidepraia.dto.PraiaDTO;
import uff.dac.depraia.apidepraia.dto.UserDTO;
import uff.dac.depraia.apidepraia.model.Praia;
import uff.dac.depraia.apidepraia.repositories.BanhistaRepository;
import uff.dac.depraia.apidepraia.repositories.PraiaRepository;

@Controller
@RequestMapping("/praia")
public class PraiaController {

    @Autowired
    private PraiaRepository praiaRepo;    

    @PostMapping(path = "")
    public @ResponseBody
    String addNew(@Valid @RequestBody PraiaDTO praia) {
        try {
            praiaRepo.save(praia.conversor());
            return "Saved";
        } catch (ConstraintViolationException e) {
            return e.getMessage();
        }
    }
    
   
    // ------------------------------------- QUIÓSQUE -------------------------------------------//
    @GetMapping(path = "/todos")
    public @ResponseBody
    Iterable<Praia> getAll() {
        return praiaRepo.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Praia getById(@PathVariable int id) {
        return praiaRepo.findById(id).get();
    }

    @PutMapping("/{id}")
    public @ResponseBody
    Praia updateById(@RequestBody PraiaDTO newPraia, @PathVariable int id) {
        try {
            return praiaRepo.findById(id)
                    .map(n -> {
                        n.setNome(newPraia.conversor().getNome());
                        n.setCapacidade(newPraia.conversor().getCapacidade());
                        n.getEndereco().setBairro(newPraia.conversor().getEndereco().getBairro());
                        n.getEndereco().setRua(newPraia.conversor().getEndereco().getRua());
                        n.getEndereco().setCidade(newPraia.conversor().getEndereco().getCidade());
                        n.getEndereco().setCep(newPraia.conversor().getEndereco().getCep());
                        return praiaRepo.save(n);
                    })
                    .orElseGet(() -> {
                        return praiaRepo.save(newPraia.conversor());
                    });
        } catch (Exception e) {
            return praiaRepo.save(newPraia.conversor());
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable int id) {
        try {
            praiaRepo.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
