package me.marcocortes.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marcocortes.todo.domain.Todo;
import me.marcocortes.todo.repo.TodoRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TodoServiceImpl implements TodoService {

    private final TodoRepo todoService;

    @Override
    public Todo saveTodo(Todo todo) {
        return todoService.save(todo);
    }

    @Override
    public Boolean deleteTodo(Long todo) {
        if(findById(todo) != null){
            todoService.deleteById(todo);
            return true;
        }
        return false;
    }

    @Override
    public List<Todo> getTodos() {
        return todoService.findAll();
    }

    @Override
    public Boolean deleteChecked() {
        try{
            List<Todo> lista = getTodos();
            for (Todo t: lista) {
                if(t.getChecked()) {
                    deleteTodo(t.getId());
                }
            }
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public Todo findById(Long id) {
        return todoService.findById(id).orElse(null);
    }
}
