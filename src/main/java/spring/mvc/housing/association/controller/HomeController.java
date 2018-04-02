package spring.mvc.housing.association.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.mvc.housing.association.model.HousingAssociation;
import spring.mvc.housing.association.repository.HousingAssociationRepository;

import java.util.List;

@Controller
public class HomeController {

    private HousingAssociationRepository housingAssociationRepository;

    public HomeController(HousingAssociationRepository housingAssociationRepository) {
        this.housingAssociationRepository = housingAssociationRepository;
    }

    @GetMapping("/")
    public String list(Model model) {
        List<HousingAssociation> housingAssociations = housingAssociationRepository.findAll();
        model.addAttribute("housingAssociations", housingAssociations);
        model.addAttribute("housingAssociation", new HousingAssociation());
        return "index";
    }

    @PostMapping("/addHousingAssociation")
    public String home(HousingAssociation housingAssociation, Model model) {
        model.addAttribute("housingAssociation", new HousingAssociation());
        if (housingAssociation.getName().equals("")) {
            housingAssociation.setName(housingAssociation.getNumber() + " " + housingAssociation.getStreet());
        }
        housingAssociationRepository.save(housingAssociation);
        return "redirect:/";
    }

    @PostMapping("/addhousingAssociation")
    public String addHousingAssociation(HousingAssociation housingAssociation) {
        housingAssociationRepository.save(housingAssociation);
        return "redirect:/";
    }


}
