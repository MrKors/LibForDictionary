package ru.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.test.model.Dictionary;
import ru.test.model.Translation;
import ru.test.model.Word;
import ru.test.service.DictionaryService;
import ru.test.service.TranslationService;
import ru.test.service.WordService;

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
    public String wordsList (Model model){
        model.addAttribute("translate", new Translation());
        model.addAttribute("wordsList", wordService.show());
        model.addAttribute("translationList", translationService.showTranslations());
    return "words-list";
    }

    @RequestMapping (value = "/add-word",method = RequestMethod.GET)
    public String addWordPage (Model model){
        model.addAttribute("word", new Word());
        model.addAttribute("translate", new Translation());
        model.addAttribute("dictionaryList", dictionaryService.showDictionaries());

        return "add-word";
    }

    @RequestMapping (value = "/words/add", method = RequestMethod.POST)
    public String addWord (@RequestParam String originValue,@RequestParam ("translation")String translation, @RequestParam ("dictionary") long id){
        Dictionary dictionary = dictionaryService.findById(id);

        if (originValue.matches(dictionary.getConsistenceCriteria()) && originValue.length() <= dictionary.getLengthCriteria()) {
            Word word = new Word(originValue, translationService.showTranslations(), dictionary);
            wordService.addWord(word);
            translationService.addTranslation(new Translation(translation, wordService.findByKey(originValue)));
        }
        return "redirect:/words-list";
    }

    @RequestMapping (value = "/words/edit/{originValue}")
    public String editWord (@PathVariable("originValue") String originValue, Model model){
        model.addAttribute("word",wordService.findByKey(originValue));
        model.addAttribute("wordsList",wordService.show());
        model.addAttribute("dictionaryList", dictionaryService.showDictionaries());
        return "add-word";
    }

    @RequestMapping (value = "/words/editTranslation/{originValue}")
    public String addEditTranslationPage (@PathVariable ("originValue") String originValue, Model model){
        Word word = wordService.findByKey(originValue);
        model.addAttribute("word", word);
        model.addAttribute("translate", new Translation());
        model.addAttribute("dictionary", dictionaryService.findById(word.getDictionary().getId()));
        model.addAttribute("dictionaryList", dictionaryService.showDictionaries());

        return "edit-add-translation";
    }

    @RequestMapping (value = "/words/delete/{originValue}")
    public String deleteWord (@PathVariable("originValue") String originValue){
        wordService.deleteByKey(originValue);
//        model.addAttribute("wordsList", wordService.show());
        return "redirect:/words-list";
    }

    @RequestMapping (value = "/words/editTranslation", method = RequestMethod.POST)
    public String editTranslation (@RequestParam ("originValue")String originValue, @RequestParam ("oldTranslation")String oldTranslation, @RequestParam ("newTranslation")String newTranslation){
        Translation translation1 = translationService.findByNameAndWord(oldTranslation, wordService.findByKey(originValue));
        translation1.setTranslation(newTranslation);
        translationService.updateTranslation(translation1);
        return "redirect:/words-list";
    }

    @RequestMapping (value = "/words/deleteTranslation")
    public String deleteTranslation (@RequestParam ("trans") String trans, @RequestParam ("origin") String origin){
        Translation translation = translationService.findByNameAndWord(trans,wordService.findByKey(origin));
        translationService.deleteTranslation(translation);
        return "redirect:/words/editTranslation/"+origin;
    }

    @RequestMapping (value = "/words/search", method = RequestMethod.GET)
    @ResponseBody
    public List<String> searchWord (@RequestParam("term") String searchStr){
        List<Word> words = wordService.searchByKeyAndTranslation(searchStr,searchStr);
        List<String> wordsList = new ArrayList<>();
        for (Word word: words) {
            wordsList.add(word.getOriginValue());
        }
        System.out.println(words);
        return wordsList;
    }
}
