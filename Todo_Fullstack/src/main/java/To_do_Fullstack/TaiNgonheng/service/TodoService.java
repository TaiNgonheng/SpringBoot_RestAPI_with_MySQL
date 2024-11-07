package To_do_Fullstack.TaiNgonheng.service;

import To_do_Fullstack.TaiNgonheng.dto.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto); //create a method and use argumant as tododto
    TodoDto getTodo(Long id);
    List<TodoDto> getAllTodo();
    TodoDto updateTodo(TodoDto todoDto, Long id);
    void deleteTodo(Long id);
    TodoDto completeTodo(Long id);
    TodoDto inCompleteTodo(Long id);
}
