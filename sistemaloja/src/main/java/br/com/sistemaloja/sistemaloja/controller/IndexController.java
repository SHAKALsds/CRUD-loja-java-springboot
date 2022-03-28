package br.com.sistemaloja.sistemaloja.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sistemaloja.sistemaloja.model.Loja;
import br.com.sistemaloja.sistemaloja.repository.LojaRepository;

@Controller
public class IndexController {

	@Autowired
	private LojaRepository lr;
	
	private static String caminhoImagemLoja = "F:\\Hiago\\Documents\\Eclipse\\sistemaloja\\sistemaloja\\src\\main\\resources\\static\\imagens\\loja-img\\";
	
	//Metodo GET que chama a index e lista as lojas cadastradas
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Loja>lojas = lr.findAll();
		mv.addObject("lojas", lojas);
		return mv;
	}
	
	//Metodo GET que exibi a imagem da loja
	@RequestMapping("/exibirImagemLoja/{imagem}")
	@ResponseBody
	public byte[] exibirImagemLoja(@PathVariable("imagem")String imagem) throws IOException{
		File imagemFile = new File(caminhoImagemLoja + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
			return Files.readAllBytes(imagemFile.toPath());
		}
		
		return null;
	}
}
