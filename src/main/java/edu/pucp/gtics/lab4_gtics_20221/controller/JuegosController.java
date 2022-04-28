package edu.pucp.gtics.lab4_gtics_20221.controller;

import edu.pucp.gtics.lab4_gtics_20221.entity.*;
import edu.pucp.gtics.lab4_gtics_20221.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;



@Controller
@RequestMapping("/juegos")
public class JuegosController {

    @Autowired
    JuegosRepository juegosRepository;

    @Autowired
    PlataformasRepository plataformasRepository;

    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @Autowired
    GenerosRepository generosRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = {"", "/","/lista"})
    public String listaJuegos (Model model){
        List<Juegos> juegosList = juegosRepository.findByOrderByPrecioDesc();
        model.addAttribute("juegosList", juegosList);
        return "juegos/lista";
    }

    @GetMapping("/new")
    public String nuevoJuegos(@ModelAttribute("juego") Juegos juego, Model model) {
        model.addAttribute("listaGenero",generosRepository.findAll());
        model.addAttribute("listaPlataform",plataformasRepository.findAll());
        model.addAttribute("listaDistribuidoras",distribuidorasRepository.findAll());
        return "juegos/editarFrm";
    }

    @PostMapping("/save")
    public String guardarJuego(@ModelAttribute("juego") @Valid Juegos juego, BindingResult bindingResult,
                                  RedirectAttributes attr, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("listaGenero",generosRepository.findAll());
            model.addAttribute("listaPlataform",plataformasRepository.findAll());
            model.addAttribute("listaDistribuidoras",distribuidorasRepository.findAll());
            return "juegos/editarFrm";
        }else {
            if (juego.getId() == 0) {
                attr.addFlashAttribute("msg", "Juego creado exitosamente");
                juegosRepository.save(juego);
                return "redirect:/juegos/lista";
            } else {
                Juegos juegoDB = juegosRepository.getById(juego.getId());
                juegosRepository.save(juegoDB);
                attr.addFlashAttribute("msg", "Juego actualizado exitosamente");
                return "redirect:/juegos/lista";
            }
        }
    }
    @GetMapping("/edit")
    public String editarJuego(@ModelAttribute("juego") Juegos juegos, Model model,
                                 @RequestParam("id") int id) {
        Optional<Juegos> juegosOptional = juegosRepository.findById(id);
        if(juegosOptional.isPresent()){
            Juegos juego = juegosOptional.get();
            model.addAttribute("juego",juego);
            model.addAttribute("listaGenero",generosRepository.findAll());
            model.addAttribute("listaPlataform",plataformasRepository.findAll());
            model.addAttribute("listaDistribuidoras",distribuidorasRepository.findAll());
            return "juegos/editarFrm";
        }
        return "redirect:/juegos/lista";
    }

//    public String vistaJuegos ( ){
//    }

    @GetMapping("/delete")
    public String borrarDistribuidora(@RequestParam(value = "id",defaultValue = "1") int id , RedirectAttributes attr ){
        Optional<Juegos> opt = juegosRepository.findById(id);
        if (opt.isPresent()) {
            juegosRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Juego borrado exitosamente");
        }
        return "redirect:/juegos/lista";
    }

}
