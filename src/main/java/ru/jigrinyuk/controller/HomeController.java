package ru.jigrinyuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.jigrinyuk.models.Location;
import ru.jigrinyuk.services.VirusDataService;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    VirusDataService virusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<Location> allStats = virusDataService.getAllStats();
        int totalCasesWorldwide = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDelta()).sum();
        model.addAttribute("locationStatistics", allStats);
        model.addAttribute("totalCasesWorldwide", totalCasesWorldwide);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }
}
