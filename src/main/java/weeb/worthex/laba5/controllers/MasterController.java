package weeb.worthex.laba5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import weeb.worthex.laba5.models.Master;
import weeb.worthex.laba5.services.MasterService;

import java.util.List;

@Controller
@RequestMapping("/masters")
public class MasterController {
    private final MasterService masterService;

    @Autowired
    public MasterController(MasterService masterService) {
        this.masterService = masterService;
    }

    @GetMapping()
    public String getMasters(Model model) {
        List<Master> masterList = masterService.getMasters();
        model.addAttribute("masters", masterList);
        return "masters/observe";
    }

    @GetMapping("/edit/{id}")
    public String editMasterForm(@PathVariable Long id, Model model) {
        model.addAttribute("master", masterService.getById(id));
        model.addAttribute("pageTitle", "Edit master's data (id: " + id + ")");
        return "masters/edit";

    }

    @GetMapping("/new")
    public String registerMaster(Model model) {
        model.addAttribute("master", new Master());
        return "masters/register";
    }


    @PostMapping("/save")
    public String saveMaster(@ModelAttribute("master") Master master,
                             RedirectAttributes ra) {
        masterService.saveMaster(master);
        ra.addFlashAttribute("saveMessage", "Master has been saved successfully.");
        return "redirect:/masters";
    }

    @PostMapping("/{id}")
    public String deleteMaster(@PathVariable("id") Long id,
                               RedirectAttributes ra) {
        masterService.deleteMaster(id);
        ra.addFlashAttribute("deleteMessage", "Master has been deleted successfully.");
        return "redirect:/masters";
    }
}
