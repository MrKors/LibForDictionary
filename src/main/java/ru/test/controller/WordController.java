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

import javax.validation.constraints.NotNull;
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
            Word word = new Word(originValue.toLowerCase(), translationService.showTranslations(), dictionary);
            wordService.addWord(word);
            translationService.addTranslation(new Translation(translation, wordService.findByKey(originValue)));
        }
        return "redirect:/words-list";
    }

    @RequestMapping (value = "/words/edit/{originValue}")
    public String editWordPage (@PathVariable("originValue") String originValue, Model model){
        model.addAttribute("word",wordService.findByKey(originValue));
//        model.addAttribute("wordsList",wordService.show());
        model.addAttribute("dictionaryList", dictionaryService.showDictionaries());
        return "edit-word";
    }

    @RequestMapping (value = "/words/edit", method = RequestMethod.POST)
    public String editWord (@RequestParam ("oldOriginValue")String originValue, @RequestParam ("newOriginValue") String newOriginValue, @RequestParam ("dictionary") long id){
        Dictionary dictionary = dictionaryService.findById(id);
        List<Translation> translationList = translationService.showTranslations();

        if (originValue.matches(dictionary.getConsistenceCriteria()) && originValue.length() <= dictionary.getLengthCriteria()) {
            Word word = new Word(originValue.toLowerCase(), translationList, dictionary);
            word.setOriginValue(newOriginValue);

            wordService.addWord(word);

        }
        return "redirect:/words-list";
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
    public String editTranslation (@RequestParam ("oldTranslation") long id, @RequestParam ("newTranslation")String newTranslation){
        Translation translation = translationService.findById(id);
        translation.setTranslation(newTranslation);
        translationService.updateTranslation(translation);
        return "redirect:/words-list";
    }

    @RequestMapping (value = "/words/deleteTranslation")
    public String deleteTranslation (/*@RequestParam ("trans") String trans*/@RequestParam ("id") long id, @RequestParam ("origin") String origin){
//        Translation translation = translationService.findByNameAndWord(oldTranslation, wordService.findByKey(originValue));
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
    public List<String> searchWord (@RequestParam("term") String searchStr, @PathVariable (value = "id") long id){
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
        System.out.println(words);
        return wordsList;
    }

    @RequestMapping (value = "/word-data", method = RequestMethod.POST)
    public String wordData (@RequestParam ("originValue") String originValue){
        return "redirect:/word-data/" + originValue;
    }

    @RequestMapping (value = "/word-data/{originValue}", method = RequestMethod.GET)
    public String wordDataPage (@PathVariable ("originValue") @NotNull String originValue, Model model){
        model.addAttribute("word", wordService.findByKey(originValue));
        return "word-data";
    }


}
