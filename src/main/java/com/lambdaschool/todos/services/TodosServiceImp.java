package com.lambdaschool.todos.services;


import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "todosService")
public class TodosServiceImp implements TodosService{

    @Autowired
    private TodosRepository todosRepository;

    @Autowired
    private UserService userService;

    @Override
    public Todos save(Long userid, Todos todos) {

        User currentUser = userService.findUserById(userid);
        Todos newTodo = new Todos(todos.getDescription(), currentUser);
        todosRepository.save(newTodo);
        return newTodo;
    }

    @Override
    public void markComplete(long todoid) {

        Todos markTodo = todosRepository.findById(todoid).orElseThrow(() ->
                new EntityNotFoundException("Todo Item " + todoid + " Not Found"));
        markTodo.setCompleted(true);
        todosRepository.save(markTodo);
    }

}
