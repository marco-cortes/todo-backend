package me.marcocortes.todo.repo;

import me.marcocortes.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepo extends JpaRepository<Todo, Long> {
    @Query("SELECT t FROM Todo t WHERE t.user = ?1")
    List<Todo> getTodosById(String id);
}
