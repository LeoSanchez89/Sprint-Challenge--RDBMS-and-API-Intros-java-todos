package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;

public interface TodosService
{
    Todos save(Long userid, Todos todos);
    void markComplete(long todoid);
}
