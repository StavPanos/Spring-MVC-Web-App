package com.edu.springbootwebapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.springbootwebapp.model.ToDo;
import com.edu.springbootwebapp.service.ToDoService;

@Controller
// @SessionAttributes("name") save attributes for all session, not good to add many because of memory
public class ToDoController {

	@Autowired
	ToDoService service;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@GetMapping("/list-todos")
	public String showTodos(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("todos", service.retrieveToDos(name));
		return "list-todos";
	}

	@GetMapping("/add-todo")
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("toDo", new ToDo(0, getLoggedInUserName(model), "Default Desc", new Date(), false));
		return "todo";
	}

	@GetMapping("/delete-todo")
	public String deleteTodo(@RequestParam int id) throws Exception {
		service.deleteToDo(id);
		
		if(id==1)
			throw new Exception();
		
		return "redirect:/list-todos";
	}

	@PostMapping("/update-todo")
	public String updateTodo(@Valid ToDo toDo, BindingResult result, ModelMap model) {

		toDo.setUser(getLoggedInUserName(model));

		if (result.hasErrors()) {
			return "todo";
		}
		service.updateToDo(toDo);
		return "redirect:/list-todos";
	}

	@GetMapping("/update-todo")
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		ToDo todo = service.retrieveToDo(id);
		model.put("toDo", todo);
		return "todo";
	}

	@PostMapping("/add-todo")
	public String addTodo(ModelMap model, @Valid ToDo toDo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}

		service.addToDo(getLoggedInUserName(model), toDo.getDesc(), toDo.getTargetDate(), false);
		return "redirect:/list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getName();

		if (principal instanceof UserDetails) {
			((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}
}
