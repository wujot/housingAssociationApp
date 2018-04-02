package spring.mvc.housing.association.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.mvc.housing.association.model.Occupant;
import spring.mvc.housing.association.repository.FlatRepository;
import spring.mvc.housing.association.repository.HousingAssociationRepository;
import spring.mvc.housing.association.repository.OccupantRepository;

import java.util.Optional;

@Controller
public class OccupantController {

    private HousingAssociationRepository housingAssociationRepository;
    private FlatRepository flatRepository;
    private OccupantRepository occupantRepository;

    private int presentOccupantId;

    public OccupantController(HousingAssociationRepository housingAssociationRepository, FlatRepository flatRepository,
                              OccupantRepository occupantRepository) {
        this.housingAssociationRepository = housingAssociationRepository;
        this.flatRepository = flatRepository;
        this.occupantRepository = occupantRepository;
    }

    @GetMapping("/occupant")
    public String occupant(@RequestParam int id, Model model) {
        Optional<Occupant> occupantOptional = occupantRepository.findById(id);

        if(occupantOptional.isPresent()) {
            Occupant occupant = occupantOptional.get();
            presentOccupantId = occupant.getId();
            model.addAttribute("occupant", occupant);
        } else {
            return "redirect:/";
        }

        return "occupant";
    }

    @GetMapping("/deleteOccupant")
    public String deleteOccupant(@RequestParam int id, Model model) {
        Optional<Occupant> occupantOptional = occupantRepository.findById(id);
        int flatId = 0;
        if (occupantOptional.isPresent()) {
            Occupant occupant = occupantOptional.get();
            flatId = occupant.getFlat().getId();
            occupantRepository.delete(occupant);
        }else {
            return "redirect:/";
        }

        return "redirect:/flat?id="+ flatId;
    }

    @PostMapping("/editoccupant")
    public String editOccupant(Occupant occupant) {
        Optional<Occupant> optionalOccupant = occupantRepository.findById(presentOccupantId);

        if (optionalOccupant.isPresent()) {
            Occupant presentOccupant = optionalOccupant.get();
            presentOccupant.setName(occupant.getName());
            presentOccupant.setSurname(occupant.getSurname());
            presentOccupant.setGender(occupant.getGender());
            occupantRepository.save(presentOccupant);
        }else {
            return "redirect:/";
        }
        return "redirect:/occupant?id=" + presentOccupantId;
    }

}
