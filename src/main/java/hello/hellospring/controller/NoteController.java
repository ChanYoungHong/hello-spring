package hello.hellospring.controller;


import hello.hellospring.dto.NoteDto;
import hello.hellospring.service.NoteService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/notes/")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping(value = "")
    public ResponseEntity<Long> register(@RequestBody NoteDto noteDto){

        log.info("==========register =====================");
        log.info(noteDto);

        Long num = noteService.register(noteDto);

        return new ResponseEntity<>(num, HttpStatus.OK);
    }

    @GetMapping(value = "/{num}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDto> read(Long num){
        log.info("============read==================");
        log.info(num);

        return new ResponseEntity<>(noteService.get(num), HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NoteDto>> getList(String email){

        log.info("------------getList---------------------");
        log.info(email);


        return new ResponseEntity<>(noteService.getAllWithWriter(email),
            HttpStatus.OK);
    }

    @PutMapping(value = "/{num}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modify(@RequestBody NoteDto noteDto){

        log.info("==============modify =====================");
        log.info(noteDto);

        noteService.modify(noteDto);

        return new ResponseEntity<>("modified", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{num}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("num") Long num){

        log.info("==================remove=================");
        log.info(num);

        noteService.remove(num);

        return new ResponseEntity<>("removed", HttpStatus.OK);
    }

}
