package To_do_Fullstack.TaiNgonheng.controller;

import To_do_Fullstack.TaiNgonheng.dto.TodoDto;
import To_do_Fullstack.TaiNgonheng.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {
    private TodoService todoService;
    // build for rest api
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
       TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    //build get to do rest api
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){
       TodoDto todoDto = todoService.getTodo(todoId);
       return new ResponseEntity<>(todoDto, HttpStatus.CREATED);
    }

    //Build get All todo rest api
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodo(){
        List<TodoDto> todos = todoService.getAllTodo();
        return ResponseEntity.ok(todos);
    }

    //build Update Todo Rest api
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable("id") Long todoId){
        TodoDto updateTodo = todoService.updateTodo(todoDto,todoId);
        return ResponseEntity.ok(updateTodo);
    }
    //build delete to do rest api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Delete successfully!.!");
    }
    //Build Complete todo rest APi
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId){
        TodoDto updatetodo = todoService.completeTodo(todoId);
        return ResponseEntity.ok(updatetodo);
    }

    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id")Long todoId){
        TodoDto updateTodo = todoService.inCompleteTodo(todoId);
        return ResponseEntity.ok(updateTodo);
    }
}
