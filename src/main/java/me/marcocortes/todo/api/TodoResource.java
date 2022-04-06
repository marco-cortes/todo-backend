package me.marcocortes.todo.api;

import lombok.RequiredArgsConstructor;
import me.marcocortes.todo.domain.Todo;
import me.marcocortes.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TodoResource {

    private final TodoService todoService;

    @GetMapping("/")
    public ResponseEntity<List<Todo>> getAll() {
        return ResponseEntity.ok().body(todoService.getTodos());
    }

    @PostMapping("/save")
    public ResponseEntity<Todo> save(@RequestBody Todo todo) {
        return ResponseEntity.ok().body(todoService.saveTodo(todo));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(todoService.deleteTodo(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteChecked() {
        return ResponseEntity.ok().body(todoService.deleteChecked());
    }
}
