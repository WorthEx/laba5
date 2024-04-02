package weeb.worthex.laba5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @PostMapping("/save")
    public String saveRepairWork(@ModelAttribute("rw") RepairWork rw,
                                 RedirectAttributes ra) {
        rwService.saveRepairWork(rw);
        ra.addFlashAttribute("saveMessage", String.format("Repair work #%d has been saved successfully.", rw.getId()));
        return "redirect:/repair-works";
    }
}