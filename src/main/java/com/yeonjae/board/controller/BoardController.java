package com.yeonjae.board.controller;

import com.yeonjae.board.DTO.WritingDTO;
import com.yeonjae.board.domain.Board;
import com.yeonjae.board.repository.BoardRepository;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("")
    public String home(Model model){
        List<Board> writings = boardRepository.findAll();
        Collections.reverse(writings);
        if(writings.isEmpty())
            model.addAttribute("writings", null);
        else
            model.addAttribute("writings", writings);
        return "home";
    }

    @GetMapping("writing/{writingId}")
    public String writingById(@PathVariable("writingId") Long writingId, Model model){

        Optional<Board> writing = boardRepository.findById(writingId);
        model.addAttribute("userName", writing.get().getUserName());
        model.addAttribute("title", writing.get().getTitle());
        model.addAttribute("content", writing.get().getContent());
        model.addAttribute("createDate", writing.get().getCreateDate());

        return "one_writing";
    }

    @GetMapping("/writing/delete/{writingId}")
    public String deleteWriting(@PathVariable("writingId") Long writingId){
        boardRepository.deleteById(writingId);
        return "redirect:/";
    }

    @GetMapping("/writing/update/{writingId}")
    public String update(@PathVariable("writingId") Long writingId, Model model){
        model.addAttribute("form", new WritingDTO());

        Optional<Board> writing = boardRepository.findById(writingId);
        model.addAttribute("id", writing.get().getId());
        model.addAttribute("userName", writing.get().getUserName());
        model.addAttribute("title", writing.get().getTitle());
        model.addAttribute("content", writing.get().getContent());

        return "update";
    }

    @PostMapping("/writing/update/{writingId}")
    public String updateWriting(@PathVariable("writingId") Long writingId, WritingDTO form){
        Optional<Board> writing = boardRepository.findById(writingId);

        writing.get().setUserName(form.getUserName());
        writing.get().setTitle(form.getTitle());
        writing.get().setContent(form.getContent());
        boardRepository.save(writing.get());
        System.out.println("here?");

        String pageURL = "redirect:/writing/" + writingId;
        return pageURL;
    }



    // 글 작성
    @GetMapping("/writing")
    public String writing(Model model){

        model.addAttribute("form", new WritingDTO());
        return "writing";
    }

    @PostMapping("/writing")
    public String createWriting(WritingDTO form){

        Board board = new Board(form);
        board.setUserName(form.getUserName());
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());
        board.setCreateDate(LocalDateTime.now());

        boardRepository.save(board);

        return "redirect:";
    }

}
