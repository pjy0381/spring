package com.example.firstProject.api;

import com.example.firstProject.dto.CommentDto;
import com.example.firstProject.entity.Comment;
import com.example.firstProject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        List<CommentDto> dtos = commentService.comments(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping("/api/articles/{articleId}/comments")
    public  ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                              @RequestBody CommentDto dto){
        CommentDto createdDto = commentService.create(articleId,dto);

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    @PatchMapping("/api/articles/{id}/comments")
    public  ResponseEntity<CommentDto> update(@PathVariable Long id,
                                              @RequestBody CommentDto dto){
        CommentDto updatedDto = commentService.update(id,dto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/api/articles/{id}/comments")
    public  ResponseEntity<CommentDto> delete(@PathVariable Long id){
        CommentDto deletedDto = commentService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }

}
