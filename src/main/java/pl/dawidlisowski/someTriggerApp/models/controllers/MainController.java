package pl.dawidlisowski.someTriggerApp.models.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.dawidlisowski.someTriggerApp.models.forms.AlertForm;
import pl.dawidlisowski.someTriggerApp.models.services.AlertService;

import javax.validation.Valid;

@Controller
public class MainController {

    final AlertService alertService;

    @Autowired
    public MainController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("alertForm", new AlertForm());
        model.addAttribute("alerts", alertService.getAllAlerts());
        return "main";
    }

    @PostMapping("/")
    public String index(@ModelAttribute @Valid AlertForm alertForm,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("hasError", true);
            return "redirect:/";
        }
        alertService.saveAlert(alertForm);
        return "redirect:/";
    }
}
