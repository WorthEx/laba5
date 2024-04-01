package weeb.worthex.laba5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import weeb.worthex.laba5.models.RepairWork;
import weeb.worthex.laba5.services.RepairWorkService;

import java.util.List;

@Controller
@RequestMapping("/repair-works")
public class RepairWorkController {
    private final RepairWorkService rwService;

    @Autowired
    public RepairWorkController(RepairWorkService repairWorkService) {
        this.rwService = repairWorkService;
    }

    @GetMapping()
    public String getRepairWorks(Model model) {
        List<RepairWork> rwList = rwService.getRepairWorks();
        model.addAttribute("repairWorks", rwList);
        return "repairWorks/observe";
    }
}