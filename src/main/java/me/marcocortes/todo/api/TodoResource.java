package me.marcocortes.todo.api;

import lombok.RequiredArgsConstructor;
import me.marcocortes.todo.domain.Todo;
import me.marcocortes.todo.service.TodoService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TodoResource {

    private final TodoService todoService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Todo>> getAll(@RequestHeader("x-token") String token, @PathVariable String id) {
        authorization(token);
        return ResponseEntity.ok().body(todoService.getTodos(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Todo> save(@RequestBody Todo todo, @RequestHeader("x-token") String token) {
        authorization(token);
        return ResponseEntity.ok().body(todoService.saveTodo(todo));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id, @RequestHeader("x-token") String token) {
        authorization(token);
        return ResponseEntity.ok().body(todoService.deleteTodo(id));
    }

    @DeleteMapping("/deletechecked/{id}")
    public ResponseEntity<Boolean> deleteChecked(@RequestHeader("x-token") String token, @PathVariable String id) {
        authorization(token);
        return ResponseEntity.ok().body(todoService.deleteChecked(id));
    }

    private void authorization(String token){
        try {
            new RestTemplate().exchange("http://localhost:4000/api/auth/validate", HttpMethod.GET, new HttpEntity<String>(new HttpHeaders(){{
                set("x-token", token);
            }}), String.class);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
