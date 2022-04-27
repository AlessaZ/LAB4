package edu.pucp.gtics.lab4_gtics_20221.controller;

import edu.pucp.gtics.lab4_gtics_20221.entity.Distribuidoras;
import edu.pucp.gtics.lab4_gtics_20221.entity.Paises;
import edu.pucp.gtics.lab4_gtics_20221.repository.DistribuidorasRepository;
import edu.pucp.gtics.lab4_gtics_20221.repository.PaisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/distribuidoras")

public class DistribuidorasController {

    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @Autowired
    PaisesRepository paisesRepository;

    @GetMapping(value = {"/lista",""})
    public String listaDistribuidoras (Model model){

        model.addAttribute("listaDistribuidoras",distribuidorasRepository.findAll());

        return "distribuidoras/lista";
    }

    @GetMapping("/editar")
    public String editarDistribuidoras(@ModelAttribute("distribuidora") Distribuidoras distribuidora,
                                       Model model, RedirectAttributes atr,
                                       @RequestParam("id") int id){

        Optional<Distribuidoras> dist = distribuidorasRepository.findById(id);
        if (dist.isPresent()){
            distribuidora=dist.get();
            model.addAttribute("distribuidora",distribuidora);
            model.addAttribute("listaPaises",paisesRepository.findAll());
            return "distribuidoras/editarFrm";
        }
        atr.addFlashAttribute("err","Hubo un problema, inténtelo nuevamente.");
        return "redirect:/distribuidoras/lista";
    }

    @GetMapping("/nuevo")
    public String nuevaDistribuidora(Model model){
        model.addAttribute("listaPaises",paisesRepository.findAll());
        return "distribuidoras/editarFrm";
    }

    @PostMapping("/guardar")
    public String guardarDistribuidora(@ModelAttribute("distribuidora") Distribuidoras distribuidora,
                                       RedirectAttributes atr){
        if(distribuidora.getIddistribuidora() != 0){
            atr.addFlashAttribute("msg","Distribuidora actualizada exitosamente");
        }else{
            atr.addFlashAttribute("msg","Distribuidora creada exitosamente");
        }
        distribuidorasRepository.save(distribuidora);
        return "redirect:/distribuidoras/lista";
    }

    @GetMapping("/borrar")
    public String borrarDistribuidora(@RequestParam("id") int id){
        Optional<Distribuidoras> opt = distribuidorasRepository.findById(id);
        if (opt.isPresent()) {
            distribuidorasRepository.deleteById(id);
        }
        return "redirect:/distribuidoras/lista";
    }

}
