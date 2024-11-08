package To_do_Fullstack.TaiNgonheng.service.impl;

import To_do_Fullstack.TaiNgonheng.dto.TodoDto;
import To_do_Fullstack.TaiNgonheng.entity.Todo;
import To_do_Fullstack.TaiNgonheng.exception.ResourceNotFoundException;
import To_do_Fullstack.TaiNgonheng.repository.TodoRepository;
import To_do_Fullstack.TaiNgonheng.service.TodoService;
import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor // will create an automatically for each class
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

    //convert TodoDto into todo jpa entity
        Todo todo = modelMapper.map(todoDto, Todo.class);


    //todo jpa ent
        Todo savedTodo = todoRepository.save(todo);

    //convert saved todo jpa entity obejct into TodoDto object
        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Todo not found with id"+id));

        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodo() {
       List<Todo> todos =todoRepository.findAll();
        return todos.stream().map((todo)-> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo = todoRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("To do not found with Id"+id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updateTodo = todoRepository.save(todo);
        return modelMapper.map(updateTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo deleteTodo = todoRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("todo not found with Id"+id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo =todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("To do with this ID are not found:"+id));
        todo.setCompleted(Boolean.TRUE);
        Todo updateTodo = todoRepository.save(todo);
        return modelMapper.map(updateTodo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo is not found "+id));
        todo.setCompleted(Boolean.FALSE);
        Todo updateTodo = todoRepository.save(todo);

        return modelMapper.map(updateTodo, TodoDto.class);
    }
}
