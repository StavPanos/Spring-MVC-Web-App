package com.edu.springbootwebapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.springbootwebapp.model.ToDo;

@Service
public class ToDoService {
	
    private static List<ToDo> toDos = new ArrayList<ToDo>();
    private static int ToDoCount = 3;

    static {
    	toDos.add(new ToDo(1, "Panos", "Learn Spring MVC", new Date(), false));
    	toDos.add(new ToDo(2, "Panos", "Learn Struts", new Date(), false));
    	toDos.add(new ToDo(3, "Panos", "Learn Hibernate", new Date(), false));
    }

    public List<ToDo> retrieveToDos(String user) {
        List<ToDo> filteredToDos = new ArrayList<ToDo>();
        for (ToDo ToDo : toDos) {
            if (ToDo.getUser().equals(user)) {
                filteredToDos.add(ToDo);
            }
        }
        return filteredToDos;
    }
    
    public ToDo retrieveToDo(int id) {
        for (ToDo toDo : toDos) {
            if (toDo.getId() == id) {
                return toDo;
            }
        }
        return null;
    }

    public void updateToDo(ToDo toDo) {
    	toDos.remove(toDo);
    	toDos.add(toDo);
    }
    
    public void addToDo(String name, String desc, Date targetDate, boolean isDone) {
        toDos.add(new ToDo(++ToDoCount, name, desc, targetDate, isDone));
    }

    public void deleteToDo(int id) {
        Iterator<ToDo> iterator = toDos.iterator();
        while (iterator.hasNext()) {
            ToDo ToDo = iterator.next();
            if (ToDo.getId() == id) {
                iterator.remove();
            }
        }
    }
}