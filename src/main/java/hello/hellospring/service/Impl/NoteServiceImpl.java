package hello.hellospring.service.Impl;

import hello.hellospring.entity.Note;
import hello.hellospring.dto.NoteDto;
import hello.hellospring.repo.NoteRepository;
import hello.hellospring.service.NoteService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public Long register(NoteDto noteDto) {

        Note note = dtoToEntity(noteDto);

        log.info("=======================");
        log.info(note);

        noteRepository.save(note);

        return note.getNum();
    }

    @Override
    public NoteDto get(Long num) {

        Optional<Note> result = noteRepository.getWithWriter(num);

        if(result.isPresent()){
            return entityToDTO(result.get());
        }
        
        return null;
    }

    @Override
    public void modify(NoteDto noteDto) {

        Long num = noteDto.getNum();

        Optional<Note> result = noteRepository.findById(num);

        if(result.isPresent()){

            Note note = result.get();

            note.changeTitle(noteDto.getTitle());
            note.changeContent(noteDto.getContent());
            noteRepository.save(note);
        }


    }

    @Override
    public void remove(Long num) {

        noteRepository.deleteById(num);
    }

    @Override
    public List<NoteDto> getAllWithWriter(String writerEmail) {

        List<Note> noteList = noteRepository.getList(writerEmail);

        return noteList.stream().map(note -> entityToDTO(note))
            .collect(Collectors.toList());
    }
}
