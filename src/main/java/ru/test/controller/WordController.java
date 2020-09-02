package ru.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.test.dto.WordDto;
import ru.test.model.Dictionary;
import ru.test.model.Translation;
import ru.test.model.Word;
import ru.test.service.DictionaryService;
import ru.test.service.TranslationService;
import ru.test.service.WordService;

import javax.swing.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WordController {

    private WordService wordService;
    private DictionaryService dictionaryService;
    private TranslationService translationService;

    @Autowired
    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Autowired
    public void setTranslationService(TranslationService translationService) {
        this.translationService = translationService;
    }

    @Autowired
    public void setWordService(WordService wordService) {
        this.wordService = wordService;
    }

    @RequestMapping (value = "/words-list",method = RequestMethod.GET)
    public String wordsListPage (Model model){
        model.addAttribute("translate", new Translation());
        model.addAttribute("wordsList", wordService.show());
        model.addAttribute("translationList", translationService.showTranslations());
        model.addAttribute("dictionaryList", dictionaryService.showDictionaries());
        return "words-list";
    }

    @RequestMapping (value = "/addWord",method = RequestMethod.GET)
    public String addWordPage (Model model){
        model.addAttribute("word", new WordDto());
//        model.addAttribute("translate", new Translation());
        model.addAttribute("dictionaryList", dictionaryService.showDictionaries());
        return "add-word";
    }

    @RequestMapping (value = "/words/add", method = RequestMethod.POST)
    public String addWord (@ModelAttribute("word") @Valid WordDto wordDto, BindingResult bindingResult, Model model){
//            @NotEmpty(message = "Origin value must not be empty")
//                           @RequestParam  String originValue,
//                           @NotEmpty(message = "Origin value must not be empty")
//                           @RequestParam ("translation") String translation,
//                           @RequestParam ("dictionary") long id){

        model.addAttribute("dictionaryList", dictionaryService.showDictionaries());

        if (!bindingResult.hasErrors()){
            Dictionary dictionary = dictionaryService.findById(wordDto.getDictionary());
            if (wordDto.getOriginValue().matches(dictionary.getConsistenceCriteria()) && wordDto.getOriginValue().length() <= dictionary.getLengthCriteria()) {
                Word word = new Word(wordDto.getOriginValue().toLowerCase(), translationService.showTranslations(), dictionary);
                wordService.addWord(word);
                translationService.addTranslation(new Translation(wordDto.getTranslation(), wordService.findByKey(wordDto.getOriginValue())));
            }
            else {
                bindingResult.rejectValue("originValue", "", "Does not meet the requirements dictionary");
            }
        }

        if (!bindingResult.hasErrors()){
            return "redirect:/words-list";
        }
        else {
            return "/add-word";
        }
    }

    @RequestMapping (value = "/words/addTranslation/{originValue}", method = RequestMethod.GET)
    public String editWordPage (@PathVariable("originValue") String originValue, Model model){
        model.addAttribute("word", new WordDto((wordService.findByKey(originValue))));
//        model.addAttribute("wordsList",wordService.show());
//        model.addAttribute("dictionaryList", dictionaryService.showDictionaries());
        return "add-translation";
    }

    @RequestMapping (value = "/words/addTranslation", method = RequestMethod.POST)
    public String editWord (@ModelAttribute("word") @Valid WordDto wordDto, BindingResult bindingResult, Model model){
//                            @RequestParam ("originValue")String originValue,
//                            @RequestParam ("translation") String newTranslation,
//                            @RequestParam ("dictionary") long id){

        wordDto.setWord(wordService.findByKey(wordDto.getOriginValue()));
        model.addAttribute("word", wordDto);

        if (!bindingResult.hasErrors()) {
            Dictionary dictionary = dictionaryService.findById(wordDto.getDictionary());
            List<Translation> translationList = translationService.showTranslations();
//            if (wordDto.getOriginValue().matches(dictionary.getConsistenceCriteria()) && wordDto.getOriginValue().length() <= dictionary.getLengthCriteria()) {
            Word word = new Word(wordDto.getOriginValue().toLowerCase(), translationList, dictionary);
            translationService.addTranslation(new Translation(wordDto.getTranslation(), word));
//            }
            return "redirect:/words-list";
        }else
        {
            return "/add-translation";
//            return "redirect:/words/addTranslation/" + wordDto.getOriginValue();
        }
    }

    @RequestMapping (value = "/words/editTranslation/{originValue}")
    public String editDeleteTranslationPage (@PathVariable ("originValue") String originValue, Model model){
        Word word = wordService.findByKey(originValue);
        WordDto wordDto = new WordDto(word);
        model.addAttribute("word", wordDto);
//        model.addAttribute("translate", new Translation());
//        model.addAttribute("dictionary", dictionaryService.findById(word.getDictionary().getId()));
//        model.addAttribute("dictionaryList", dictionaryService.showDictionaries());
        return "edit-delete-translation";
    }

    @RequestMapping (value = "/words/editTranslation", method = RequestMethod.POST)
    public String editTranslation (@RequestParam ("oldTranslation") long id,
                                   @ModelAttribute ("word") @Valid WordDto wordDto, BindingResult bindingResult, Model model
//                                   @RequestParam ("newTranslation")String newTranslation
    ) {

        wordDto.setWord(wordService.findByKey(wordDto.getOriginValue()));
        model.addAttribute("word", wordDto);

        if (!bindingResult.hasErrors()) {
            Translation translation = translationService.findById(id);
            translation.setTranslation(wordDto.getTranslation());
            translationService.updateTranslation(translation);
            return "redirect:/words-list";
        } else {
            return "edit-delete-translation";
        }
    }

    @RequestMapping (value = "/words/delete/{originValue}")
    public String deleteWord (@PathVariable("originValue") String originValue){
        wordService.deleteByKey(originValue);
//        model.addAttribute("wordsList", wordService.show());
        return "redirect:/words-list";
    }

    @RequestMapping (value = "/words/deleteTranslation")
    public String deleteTranslation (@RequestParam ("id") long id,
                                     @RequestParam ("origin") String origin){

        Translation translation = translationService.findById(id);
        translationService.deleteTranslation(translation);
        return "redirect:/words/editTranslation/"+origin;
    }

    @RequestMapping (value = "/words-list/filter")
    public String filterDictionary (@RequestParam (value = "id") long id, Model model){
        if (id == 0){
            return "redirect:/words-list";
        }
        else {
            model.addAttribute("translate", new Translation());
            model.addAttribute("translationList", translationService.showTranslations());
            model.addAttribute("dictionaryList", dictionaryService.showDictionaries());
            model.addAttribute("wordsList",wordService.showWordsListByDictionary(id));
            return "words-list";
        }
    }

    @RequestMapping (value = "/words/search/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<String> searchWord (@RequestParam("term") String searchStr,
                                    @PathVariable (value = "id") long id){

        List<Word> words;
        List<String> wordsList = new ArrayList<>();

        if (id == 0){
            words = wordService.searchByKeyOrTranslation(searchStr);
        }
        else {
            words = wordService.searchByKeyOrTranslationAndDictionaryID(searchStr, id);
        }
        for (Word word: words) {
            wordsList.add(word.getOriginValue());
        }
        return wordsList;
    }

    @RequestMapping (value = "/word-data", method = RequestMethod.POST)
    public String wordData (@RequestParam ("originValue") String originValue){
        if (wordService.findByKey(originValue) != null) {
            return "redirect:/word-data/" + originValue;
        }
        else {
            JDialog window = new JOptionPane("Word not found", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION).createDialog("Not found");
            window.setAlwaysOnTop(true);
            window.setVisible(true);
            return "redirect:/words-list";
        }
    }

    @RequestMapping (value = "/word-data/{originValue}", method = RequestMethod.GET)
    public String wordDataPage (@PathVariable ("originValue") String originValue, Model model){
        model.addAttribute("word", wordService.findByKey(originValue));
        return "word-data";
    }
}
