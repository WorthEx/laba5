package weeb.worthex.laba5.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import weeb.worthex.laba5.models.WorkStage;
import weeb.worthex.laba5.services.RepairWorkService;
import weeb.worthex.laba5.services.WorkStageService;

import java.util.List;

@Controller
@RequestMapping("/work-stages")
public class WorkStageController {
    private final WorkStageService wsService;
    private final RepairWorkService rwService;

    @Autowired
    public WorkStageController(RepairWorkService repairWorkService,
                               WorkStageService workStageService) {
        this.wsService = workStageService;
        this.rwService = repairWorkService;
    }

    @GetMapping()
    public String getWorkStages(Model model) {
        List<WorkStage> wsList = wsService.getWorkStages();
        model.addAttribute("workStages", wsList);
        return "workStages/observe";
    }

    @GetMapping("/new")
    public String registerWorkStage(Model model) {
        model.addAttribute("ws", new WorkStage());
        model.addAttribute("repairWorks", rwService.getRepairWorks());
        return "workStages/register";
    }

    @GetMapping("/edit/{id}")
    public String editWorkStageForm(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("ws", wsService.getById(id));
            model.addAttribute("repairWorks", rwService.getRepairWorks());
            model.addAttribute("pageTitle", "Edit work stage's data (id: " + id + ")");
            return "workStages/edit";
        } catch (EntityNotFoundException exception) {
            return "redirect:/error";
        }
    }

    @PostMapping("/save")
    public String saveWorkStage(@ModelAttribute("ws") WorkStage ws,
                                RedirectAttributes ra) {
        try {
            wsService.saveWorkStage(ws);
            ra.addFlashAttribute("saveMessage", String.format("Work stage #%d has been saved successfully.", ws.getId()));
            return "redirect:/work-stages";
        } catch (IllegalArgumentException exception) {
            ra.addFlashAttribute("errorMessage", "Work stage couldn't be added. Check if the cost doesn't exceed repair work cost.");
            return "redirect:/work-stages";
        }
    }

    @PostMapping("/{id}")
    public String deleteWorkStage(@PathVariable("id") Long id,
                                  RedirectAttributes ra) {
        wsService.deleteWorkStage(id);
        ra.addFlashAttribute("deleteMessage", String.format("Work stage #%d has been deleted successfully.", id));
        return "redirect:/work-stages";
    }
}