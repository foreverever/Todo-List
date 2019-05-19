package programmers.domain.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findByTitle(String title);
    List<Todo> findByExpiredAndDeadlineBefore(boolean expired, LocalDateTime now);
}
