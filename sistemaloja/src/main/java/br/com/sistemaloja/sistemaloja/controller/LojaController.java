package br.com.sistemaloja.sistemaloja.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sistemaloja.sistemaloja.model.Loja;
import br.com.sistemaloja.sistemaloja.model.Roupa;
import br.com.sistemaloja.sistemaloja.repository.LojaRepository;
import br.com.sistemaloja.sistemaloja.repository.RoupaRepository;

@Controller
public class LojaController {

	@Autowired
	private LojaRepository lr;

	@Autowired
	private RoupaRepository rr;

	private static String caminhoImagemLoja = "F:\\Hiago\\Documents\\Eclipse\\sistemaloja\\sistemaloja\\src\\main\\resources\\static\\imagens\\loja-img\\";

	private static String caminhoImagemRoupa = "F:\\Hiago\\Documents\\Eclipse\\sistemaloja\\sistemaloja\\src\\main\\resources\\static\\imagens\\roupa-img\\";
	
	//Metodo GET que chama o formulario de cadastro das lojas
	@RequestMapping("/cadastrarLoja")
	public String formCadastraLoja() {
		return "loja/cadastro-loja";
	}
	
	//Metodo POST que cadastra a loja
	@RequestMapping(value = "/cadastrarLoja", method = RequestMethod.POST)
	public String cadastrarLoja(@Valid Loja loja, BindingResult result, RedirectAttributes attributes,
			@RequestParam("imagem") MultipartFile file) {
		
		try {
			//Condição que salva a imagem no caminho da pasta
			if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				Path caminho = Paths.get(caminhoImagemLoja + file.getOriginalFilename());
				Files.write(caminho, bytes);
				loja.setNomeImagem(file.getOriginalFilename());
				
				lr.save(loja);
				attributes.addFlashAttribute("mensagem", "Loja cadastrada com sucesso!");
			}else {
				result.hasErrors();
				attributes.addFlashAttribute("mensagem_erro", "Verifique os campos!");
				return "redirect:/cadastrarLoja";
			}
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/cadastrarLoja";
	}
	
	//Metodo GET que lista as lojas
	@RequestMapping("/listarLojas")
	public ModelAndView listarLojas() {
		ModelAndView mv = new ModelAndView("loja/lista-lojas");
		Iterable<Loja> lojas = lr.findAll();
		mv.addObject("lojas", lojas);
		
		return mv;
	}
	
	//Metodo GET que chama o formulario de edição da loja
	@RequestMapping("/editarLoja")
	public ModelAndView editarLoja(long id) {
		Loja loja = lr.findById(id);
		ModelAndView mv = new ModelAndView("loja/edita-loja");
		mv.addObject("loja", loja);
		
		return mv;
	}
	
	//Metodo POST que atualiza a loja
	@RequestMapping(value = "/editarLoja", method = RequestMethod.POST)
	public String updateLoja(@Valid Loja loja, BindingResult result, RedirectAttributes attributes,
			@RequestParam("novaImagem") MultipartFile file) {
		
			try {
				if (file != null) {
					byte[] bytes = file.getBytes();
					Path caminho = Paths.get(caminhoImagemLoja + file.getOriginalFilename());
					Files.write(caminho, bytes);
					loja.setNomeImagem(file.getOriginalFilename());
					
					lr.save(loja);
					attributes.addFlashAttribute("success", "Loja atualizada com sucesso!");
				}else {
					result.hasErrors();
					attributes.addFlashAttribute("mensagem_erro", "Verifique os campos!");
					return "redirect:/editarLoja";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
			
		long idLong = loja.getId();
		String id = "" + idLong;
		return "redirect:/detalhesLoja/" + id;
	}
	
	// Metodo GET que deleta loja
	@RequestMapping("/deletarLoja")
	public String deletarLoja(long id) {
		Loja loja = lr.findById(id);
		lr.delete(loja);
		return "redirect:/listarLojas";
	}
	
	//Metodo GET que mostra os detalhes da loja
	@RequestMapping("/detalhesLoja/{id}")
	public ModelAndView detalhesLoja(@PathVariable("id")long id) {
		Loja loja = lr.findById(id);
		ModelAndView mv = new ModelAndView("loja/detalhes-loja");
		mv.addObject("lojas", loja);
		
		Iterable<Roupa> roupas = rr.findByLoja(loja);
		mv.addObject("roupas", roupas);
		
		return mv;
	}
	
	//Meotodo POST que cadastra roupa
	@RequestMapping(value = "/detalhesLoja/{id}", method = RequestMethod.POST)
	public String cadastroRoupa(@PathVariable("id")long id, Roupa roupas, BindingResult result,
			RedirectAttributes attributes, @RequestParam("imagem") MultipartFile file) {
		
		try {
			if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				Path caminho = Paths.get(caminhoImagemRoupa + file.getOriginalFilename());
				Files.write(caminho, bytes);
				roupas.setImagemRoupa(file.getOriginalFilename());
				
	
				Loja loja = lr.findById(id);
				roupas.setLoja(loja);
				rr.save(roupas);
				attributes.addFlashAttribute("mensagem", "Roupa cadastrada com sucesso!");
			}else {
				result.hasErrors();
				attributes.addFlashAttribute("mensagem_erro", "Verifique os campos!");
				return "redirect:/detalhesLoja/{id}";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return "redirect:/detalhesLoja/{id}";
	}
	
	//Metodo GET que exibi a imagem da roupa
	@RequestMapping("/exibirImagemRoupa/{imagem}")
	@ResponseBody
	public byte[] exibirImagemRoupa(@PathVariable("imagem")String imagem) throws IOException{
		File imagemFile = new File(caminhoImagemRoupa + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
			return Files.readAllBytes(imagemFile.toPath());
		}
		
		return null;
	}
	
	// Metodo GET que deleta roupa
	@RequestMapping("/deletarRoupa")
	public String deletarRoupa(long codigo) {
		Roupa roupa = rr.findByCodigo(codigo);
		
		Loja loja = roupa.getLoja();
		String codigo_r = "" + loja.getId();
		
		rr.delete(roupa);
		return "redirect:/detalhesLoja/" + codigo_r;
	}
	
}
