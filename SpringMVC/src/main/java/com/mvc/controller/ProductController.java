package com.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mvc.entity.products.Product;
import com.mvc.service.ProductService;

@Controller
@RequestMapping("/product")
//@SessionAttributes({"products","groups","sizes","levels"})
public class ProductController {
	@Autowired
	private ProductService productService;

	@RequestMapping(value = { "/", "/index" })
	public String index(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("products", productService.query());
		model.addAttribute("groups", productService.groups.values());
		model.addAttribute("sizes", productService.sizes.values());
		model.addAttribute("levels", productService.levels.values());
		model.addAttribute("action", "save");
		return "product";
	}

	// 若要使用JSR303驗證要在資料驗證模組前加上@Valid
	@PostMapping(value = "/save")
	public String save(@Valid Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {// 是否有錯誤發生?
			model.addAttribute("products", productService.query());
			model.addAttribute("groups", productService.groups.values());
			model.addAttribute("sizes", productService.sizes.values());
			model.addAttribute("levels", productService.levels.values());
			model.addAttribute("action", "save");
			return "product";// 將錯誤資訊帶給指定JSP頁面
		}
		productService.save(product);
		return "redirect:/mvc/product/";
	}

	@GetMapping(value = "/get/{name}")
	public String get(@PathVariable("name") String name, Model model) {
		Product product = productService.get(name);
		model.addAttribute("product", product);
		model.addAttribute("products", productService.query());
		model.addAttribute("groups", productService.groups.values());
		model.addAttribute("sizes", productService.sizes.values());
		model.addAttribute("levels", productService.levels.values());
		model.addAttribute("action", "update");
		return "product";
	}

	@PostMapping(value = "/update")
	public String update(@Valid Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {// 是否有錯誤發生?
			model.addAttribute("product", product);
			model.addAttribute("products", productService.query());
			model.addAttribute("groups", productService.groups.values());
			model.addAttribute("sizes", productService.sizes.values());
			model.addAttribute("levels", productService.levels.values());
			model.addAttribute("action", "update");
			return "product";// 將錯誤資訊帶給指定JSP頁面
		}
		productService.update(product);
		return "redirect:/mvc/product/";
	}

	@GetMapping(value = "/delete/{name}")
	public String delete(@PathVariable("name") String name) {
		productService.delete(name);
		return "redirect:/mvc/product/";
	}

	// 將資料轉成json格式對外發布
	@RequestMapping(value = "/query_json", 
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Product> queryJson() {
		return productService.query();
	}
}
