package com.estoque.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estoque.model.EstoqueModel;
import com.estoque.model.ProdutoModel;
import com.estoque.repository.EstoqueRepository;
import com.estoque.repository.ProdutoRepository;

import configure.ClassifyImage;

@Controller
public class EstoqueController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@RequestMapping(value="/cadastrarEstoque", method=RequestMethod.GET)
	public String form(){
		return "estoque/formEstoque";
	}
	
	@RequestMapping(value="/cadastrarEstoque", method=RequestMethod.POST)
	public String form(EstoqueModel estoque){
		estoqueRepository.save(estoque);
		return "redirect:/cadastrarEstoque";
	}
	
	@RequestMapping(value="/")
	public ModelAndView listEstoque(){
		ModelAndView mv = new ModelAndView("estoque/listEstoque");
		Iterable<EstoqueModel> estoques = estoqueRepository.findAll();
		mv.addObject("estoques", estoques);
		return mv;
	}

	
	@RequestMapping(value="/detalhe/{id}", method=RequestMethod.GET)
	public String detalheNota(@PathVariable("id") long id, Model model){
		EstoqueModel estoque = estoqueRepository.findById(id);
		model.addAttribute("estoque", estoque);
		Iterable<ProdutoModel> produto = produtoRepository.findByEstoque(estoque);
		model.addAttribute("produtos", produto);
		return "estoque/detalheNota";
	}
	
	@RequestMapping(value="/atualizar/{id}", method=RequestMethod.GET)
	public String atualizarNota(@PathVariable("id") long id, Model model){
		EstoqueModel estoque = estoqueRepository.findById(id);
		model.addAttribute("estoque", estoque);
		Iterable<ProdutoModel> produto = produtoRepository.findByEstoque(estoque);
		model.addAttribute("produtos", produto);
		return "estoque/atualizarEstoque";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String updateEstoque(@PathVariable("id") long id, @Valid EstoqueModel estoque, BindingResult result, Model model){
		estoque.setId(id);
		estoqueRepository.save(estoque);
		return "redirect:/";	
	}
	
	@RequestMapping(value="/deletarEstoque")
	public String deletarEstoque(long id){
		EstoqueModel estoque =  estoqueRepository.findById(id);
		estoqueRepository.delete(estoque);
		return "redirect:/estoque";
	}
	
	@RequestMapping(value="/detalhe/{id}", method=RequestMethod.POST)
	public String detalheNotaP(@PathVariable("id") long id, @Valid ProdutoModel produto, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/detalhe/{id}";
		}
		EstoqueModel estoque = estoqueRepository.findById(id);
		produto.setEstoque(estoque);
		produtoRepository.save(produto);
		attributes.addFlashAttribute("mensagem", "Produto adicionado com sucesso!");
		return "redirect:/detalhe/{id}";
	}
	
	@RequestMapping(value="/deletarProduto")
	public String deletarProduto(long codigo){
		ProdutoModel produto = produtoRepository.findByCodigo(codigo);
		produtoRepository.delete(produto);
		EstoqueModel estoque = produto.getEstoque();
		long idEstoque = estoque.getId();
		String idString = "" + idEstoque;
		return "redirect:/detalhe/" + idString;
	}
	
	@RequestMapping(value="/classifyImage", method=RequestMethod.GET)
	public String imageClass(){
		return "estoque/classifyImage";
	}
	
	@RequestMapping(value="/classifyImage", method=RequestMethod.POST)
	public String uploadImage(EstoqueModel img, RedirectAttributes attributes){
		ClassifyImage classifyImage = new ClassifyImage();
		classifyImage.classfy(img.getImg());
		attributes.addFlashAttribute("mensagem",classifyImage.getText());
		return "redirect:/classifyImage";
		
	}
	
	
}
