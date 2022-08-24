package elcom.ex1.LibraryBooks.controller;


import elcom.ex1.LibraryBooks.entity.FirstLetter;
import elcom.ex1.LibraryBooks.service.FirstLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/first-letter-ordinal")
public class FirstLetterController {

    @Autowired
    private final FirstLetterService firstLetterService;

    public FirstLetterController(FirstLetterService firstLetterService) {
        this.firstLetterService = firstLetterService;
    }

    @GetMapping("/{ID}")
    public FirstLetter findByID(@PathVariable Long ID){
        return firstLetterService.findById(ID);
    }

    @PostMapping
    public FirstLetter create(@RequestBody FirstLetter firstLetter ){
        return firstLetterService.create(firstLetter);
    }

    @PutMapping ("/{ID}")
    public FirstLetter update(@PathVariable Long ID, @RequestBody FirstLetter firstLetter){
        return firstLetterService.update(ID, firstLetter);
    }

    @DeleteMapping("/{ID}")
    public void delete(@PathVariable Long ID){
        firstLetterService.delete(ID);
    }

    @GetMapping
    public Iterable<FirstLetter> findAll(){
        return firstLetterService.findAll();
    }

}
