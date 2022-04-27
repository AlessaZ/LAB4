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



//    public String vistaJuegos ( ){
//
//    }
//
//    public String nuevoJuegos( ){
//
//    }
//
//    public String editarJuegos( ){
//
//    }
//
//    public String guardarJuegos( ){
//
//    }

    @GetMapping("/juegos/borrar")
    public String borrarDistribuidora(@RequestParam("id") int id){
        Optional<Juegos> opt = juegosRepository.findById(id);
        if (opt.isPresent()) {
            juegosRepository.deleteById(id);
        }
        return "redirect:/juegos/lista";
    }

}
