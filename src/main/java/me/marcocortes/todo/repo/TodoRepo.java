package me.marcocortes.todo.repo;

import me.marcocortes.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<Todo, Long> {}
