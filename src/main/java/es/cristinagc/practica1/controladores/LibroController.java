package es.cristinagc.practica1.controladores;

/**
 * @author Cristina Gómez Campos
 */

import es.cristinagc.practica1.entidades.Codigo;
import es.cristinagc.practica1.entidades.Genero;
import es.cristinagc.practica1.entidades.Idioma;
import es.cristinagc.practica1.entidades.Libro;
import es.cristinagc.practica1.servicios.CodigoService;
import es.cristinagc.practica1.servicios.GeneroService;
import es.cristinagc.practica1.servicios.IdiomaService;
import es.cristinagc.practica1.servicios.LibroService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@Data
@Slf4j
@RequestMapping("/libro")
public class LibroController {


    private final LibroService servicio;

    private final IdiomaService idiomaService;
    private final GeneroService generoService;
    private final CodigoService codigoService;


    @ModelAttribute("listaIdiomas")
    public List<Idioma> listaIdiomas() {
        return idiomaService.findAll();
    }

    @ModelAttribute("listaGeneros")
    public List<Genero> listaGeneros() {
        return generoService.findAll();
    }

    @ModelAttribute("listaCodigos")
    public List<Codigo> listaCodigos() {
        return codigoService.findAll();
    }

    @GetMapping("/list")
    public String listado(@RequestParam(name="q", required=false) String query,Model model){
        model.addAttribute("listaLibros",(query == null) ? servicio.findAll() : servicio.buscador(query));
        return "list";
    }
    @GetMapping( "/list/filter/titulo")
    public String listadoFiltradoTitulo(@RequestParam("titulo") String titulo, Model model) {
        model.addAttribute("listaLibros", servicio.findByTitulo(titulo));
        return "fragmentos::lista-libros";
    }

    @GetMapping( "/list/filter/autor")
    public String listadoFiltradoAutor(@RequestParam("autor") String autor, Model model) {
        model.addAttribute("listaLibros", servicio.findByAutor(autor));
        return "fragmentos::lista-libros";
    }

    @GetMapping("/new")
    public String nuevoLibroForm(Model model){
        model.addAttribute("libroForm", new Libro());
        return "form";
    }

    @PostMapping("/new/submit")
    public String nuevoLibroSubmit(@Valid @ModelAttribute("libroForm") Libro nuevoLibro, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "form";

        } else {
            nuevoLibro.setId(servicio.findAll().stream().count() + 1);
           servicio.add(nuevoLibro);
           return "redirect:/libro/list";

       }
    }

    @GetMapping("/edit/{id}")
    public String editarLibroForm(@PathVariable long id, Model model){
        Libro libro = servicio.findById(id);
        if (libro != null){
            model.addAttribute("libroForm", libro);
            return "form";
        }else {
            return "redirect:/libro/new";
        }
    }

    @PostMapping("/edit/submit")
    public String editarLibroSubmit(@Valid @ModelAttribute("libroForm") Libro libro, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "form";
        }else {
            servicio.edit(libro);
            return "redirect:/libro/list";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteLibro(@PathVariable long id, Model model) {

        Libro libro = servicio.findById(id);
        if (libro != null) {
            servicio.delete(libro);
            return "redirect:/libro/list";
        } else {
            return "form";
        }
    }

    @GetMapping({"/list/privacidad" , "/new/privacidad"})
    public String privacidad(){
        return "privacidad";
    }

    @GetMapping({"/list/aviso" , "/new/aviso"})
    public String aviso(){
        return "aviso";
    }
}
