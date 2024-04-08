package weeb.worthex.laba5.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import weeb.worthex.laba5.models.RepairWork;
import weeb.worthex.laba5.services.MasterService;
import weeb.worthex.laba5.services.RepairWorkService;

import java.util.List;

@Controller
@RequestMapping("/repair-works")
public class RepairWorkController {
    private final RepairWorkService rwService;
    private final MasterService masterService;

    @Autowired
    public RepairWorkController(RepairWorkService repairWorkService,
                                MasterService masterService) {
        this.masterService = masterService;
        this.rwService = repairWorkService;
    }

    @GetMapping()
    public String getRepairWorks(Model model) {
        List<RepairWork> rwList = rwService.getRepairWorks();
        model.addAttribute("repairWorks", rwList);
        return "repairWorks/observe";
    }

    @GetMapping("/new")
    public String registerRepairWork(Model model) {
        model.addAttribute("rw", new RepairWork());
        model.addAttribute("masters", masterService.getMasters());
        return "repairWorks/register";
    }

    @GetMapping("/edit/{id}")
    public String editRepairWorkForm(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("rw", rwService.getById(id));
            model.addAttribute("masters", masterService.getMasters());
            model.addAttribute("pageTitle", "Edit repair work's data (id: " + id + ")");
            return "repairWorks/edit";
        } catch (EntityNotFoundException exception) {
            return "redirect:/error";
        }
    }

    @PostMapping("/save")
    public String saveRepairWork(@ModelAttribute("rw") RepairWork rw,
                                 RedirectAttributes ra) {
        rwService.saveRepairWork(rw);
        ra.addFlashAttribute("saveMessage", String.format("Repair work #%d has been saved successfully.", rw.getId()));
        return "redirect:/repair-works";
    }

    @PostMapping("/{id}")
    public String deleteRepairWork(@PathVariable("id") Long id,
                                   RedirectAttributes ra) {
        rwService.deleteRepairWork(id);
        ra.addFlashAttribute("deleteMessage", String.format("Repair work #%d has been deleted successfully.", id));
        return "redirect:/repair-works";
    }
}