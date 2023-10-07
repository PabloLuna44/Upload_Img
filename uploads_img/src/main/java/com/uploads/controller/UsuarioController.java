package com.uploads.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uploads.dao.IUsuarioDAO;
import com.uploads.entity.Usuario;

@Controller
public class UsuarioController {
	
	@Autowired
	private IUsuarioDAO usuarioDao;
	
	@GetMapping("/")
	public String form(Model model) {
		model.addAttribute("usuarios",new Usuario());
		return "form";
	}
	@PostMapping("/")
    public String guardar(@RequestParam(name = "file", required = false) MultipartFile foto, Usuario usuario,
                          RedirectAttributes flash) {


        if(!foto.isEmpty()){
            String ruta="C://Users//TEMP//uploads";
           try {
               byte[] bytes =foto.getBytes();
               Path rutaAbsoluta= Paths.get(ruta +"//"+foto.getOriginalFilename());
               Files.write(rutaAbsoluta,bytes);
               usuario.setFoto(foto.getOriginalFilename());

           }catch (Exception e){
               //e
           }

            usuarioDao.save(usuario);
            flash.addFlashAttribute("success","Foto Subida!!");

        }
     return "redirect:/";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("usuarios", usuarioDao.findAll());
        return "listar";
    }



}
