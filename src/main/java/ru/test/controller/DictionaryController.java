package ru.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.test.model.Criteria;
import ru.test.model.Dictionary;
import ru.test.service.DictionaryService;

@Controller
public class DictionaryController {
    private DictionaryService dictionaryService;

    @Autowired
    @Qualifier(value = "dictionaryServiceImpl")
    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @RequestMapping(value = "/create-dictionary",method = RequestMethod.GET)
    public String dictionariesList (Model model){
        model.addAttribute("dictionary", new Dictionary());
        model.addAttribute("criteria", new Criteria());
        return "create-dictionary";
    }

    @RequestMapping (value = "/dictionary/create", method = RequestMethod.POST)
    public String createDictionary (@ModelAttribute("dictionary") Dictionary dictionary) {
        dictionaryService.addDictionary(dictionary);
        return "redirect:/create-dictionary";
    }
}
