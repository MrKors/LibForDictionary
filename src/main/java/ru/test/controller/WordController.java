package ru.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.test.model.Dictionary;
import ru.test.model.Translation;
import ru.test.model.Word;
import ru.test.service.DictionaryService;
import ru.test.service.TranslationService;
import ru.test.service.WordService;

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
    public String addWord (@RequestParam String originValue,@RequestParam ("translation")String translation, @RequestParam ("dictionary") long id, Model model){
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

    @RequestMapping (value = "/words/add/{originValue}/addTranslation")
    public String addTranslation (@PathVariable ("originValue") String originValue){

        //TODO

        return "redirect:/words-list";
    }

    @RequestMapping (value = "/words/delete/{originValue}")
    public String deleteWord (@PathVariable("originValue") String originValue, Model model){
        wordService.deleteByKey(originValue);
//        model.addAttribute("wordsList", wordService.show());
        return "redirect:/words-list";
    }

//    @RequestMapping (value = "/words/delete")
//    public String deleteWordAjax (@RequestParam Word word){
//        wordService.deleteByKey(word.getOriginValue());
//        return "/words-list";
//    }
}
