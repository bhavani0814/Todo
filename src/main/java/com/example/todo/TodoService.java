/*
 * You can use the following import statements
 * 
 * import org.springframework.web.server.ResponseStatusException;
 * import org.springframework.http.HttpStatus;
 * 
 */

package com.example.todo;

import com.example.todo.Todo;
import com.example.todo.TodoRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;

// Do not modify the below code

public class TodoService implements TodoRepository {

    private static HashMap<Integer, Todo> todoList = new HashMap<>();
    int uniqueId = 6;

    public TodoService() {
        todoList.put(1, new Todo(1, "Watch Movie", "LOW", "TO DO"));
        todoList.put(2, new Todo(2, "Finish Project", "HIGH", "IN PROGRESS"));
        todoList.put(3, new Todo(3, "Buy Groceries", "MEDIUM", "TO DO"));
        todoList.put(4, new Todo(4, "Learning from NxtWave", "HIGH", "IN PROGRESS"));
        todoList.put(5, new Todo(5, "Go for a Run", "MEDIUM", "DONE"));

    }

    // Do not modify the above code

    // Write your code here
    @Override
    public ArrayList<Todo> getTodos() {
        Collection<Todo> collect = todoList.values();
        ArrayList<Todo> r = new ArrayList<>(collect);
        return r;
    }

    @Override
    public Todo addTodo(Todo todo) {
        todo.setId(uniqueId);
        todoList.put(uniqueId, todo);
        uniqueId++;
        return todo;
    }

    @Override
    public Todo getTodoById(int id) {
        Todo todo = todoList.get(id);
        if (todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return todo;
    }

    @Override
    public Todo updateTodo(int id, Todo todo) {
        Todo x = todoList.get(id);
        if (x == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (todo.getToDo() != null) {
            x.setToDo(todo.getToDo());
        }
        if (todo.getPriority() != null) {
            x.setPriority(todo.getPriority());
        }
        if (todo.getStatus() != null) {
            x.setStatus(todo.getStatus());
        }
        return x;
    }

    @Override
    public void deleteTodo(int id) {
        Todo x = todoList.get(id);
        if (x == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            todoList.remove(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}
