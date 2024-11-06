package To_do_Fullstack.TaiNgonheng.service.impl;

import To_do_Fullstack.TaiNgonheng.dto.TodoDto;
import To_do_Fullstack.TaiNgonheng.entity.Todo;
import To_do_Fullstack.TaiNgonheng.repository.TodoRepository;
import To_do_Fullstack.TaiNgonheng.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor // will create an automatically for each class
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

    //convert TodoDto into todo jpa entity
        Todo todo=new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setComplete(todoDto.isCompleted());

    //todo jpa ent
        Todo savedTodo = todoRepository.save(todo);

    //convert saved todo jpa entity obejct into TodoDto object
        TodoDto savedTodoDto = new TodoDto();
        savedTodoDto.setId(savedTodo.getId());
        savedTodoDto.setTitle(savedTodo.getTitle());
        savedTodoDto.setDescription(savedTodo.getDescription());
        savedTodoDto.setCompleted(savedTodo.isComplete());

        return savedTodoDto;
    }
}
