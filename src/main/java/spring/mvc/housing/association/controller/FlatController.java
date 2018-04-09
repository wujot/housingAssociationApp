package spring.mvc.housing.association.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.mvc.housing.association.model.Flat;
import spring.mvc.housing.association.model.HousingAssociation;
import spring.mvc.housing.association.model.Occupant;
import spring.mvc.housing.association.repository.FlatRepository;
import spring.mvc.housing.association.repository.HousingAssociationRepository;
import spring.mvc.housing.association.repository.OccupantRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class FlatController {

    private HousingAssociationRepository housingAssociationRepository;
    private FlatRepository flatRepository;
    private OccupantRepository occupantRepository;

    private int presentFlatId;

    public FlatController(HousingAssociationRepository housingAssociationRepository, FlatRepository flatRepository,
                          OccupantRepository occupantRepository) {
        this.housingAssociationRepository = housingAssociationRepository;
        this.flatRepository = flatRepository;
        this.occupantRepository = occupantRepository;
    }

    @GetMapping("/flat")
    public String flatDetails(@RequestParam int id, Model model) {
        Optional<HousingAssociation> housingAssociationOptional = housingAssociationRepository.findById(id);
        Optional<Flat> flatOptional = flatRepository.findById(id);
        List<Occupant> occupants = occupantRepository.findAll();

        if(flatOptional.isPresent()) {
            Flat flat = flatOptional.get();
            presentFlatId = flat.getId();
            Occupant occupant = new Occupant();
            occupant.setFlat(flat);

            model.addAttribute("flat", flat);
            model.addAttribute("occupant", occupant);
            model.addAttribute("occupants", occupants);

            // delete alert
            String deleteMessage;
                if (flat.getOccupants().size() > 0) {
                deleteMessage = "You can not delete flat with occupants";
                }else {
                    deleteMessage = "Flat had been deleted";
                }
            model.addAttribute("deleteMessage", deleteMessage);

        } else {
            return "redirect:/";
        }

        return "flat";
    }

    @PostMapping("/addoccupant")
    public String addOccupant(Occupant occupant) {
        occupantRepository.save(occupant);
        return "redirect:/flat?id=" + occupant.getFlat().getId();
    }

    @GetMapping("/deleteFlat")
    public String deleteFlat(@RequestParam int id, Model model) {
        Optional<Flat> flatOptional = flatRepository.findById(id);
        Flat flat = null;
        if (flatOptional.isPresent()) {
            flat = flatOptional.get();
            List<Occupant> occupants = flat.getOccupants();
            if (occupants.size() > 0){

                //deleteMessage = "You can not delete flat with occupants";

                System.out.println("You can not delete flat with occupants/");
                return "redirect:/flat?id=" + flat.getId();
            }else {

                //deleteMessage = "Flat had been deleted";

                flatRepository.delete(flat);
            }
        }else {
            return "redirect:/";
        }

        return "redirect:/housingAssociation?id=" + flat.getHousingAssociation().getId();
    }

    @PostMapping("/editflat")
    public String editFlat(Flat flat) {
        Optional<Flat> optionalFlat = flatRepository.findById(presentFlatId);

        if (optionalFlat.isPresent()) {
            Flat presentFlat = optionalFlat.get();
            presentFlat.setNumber(flat.getNumber());
            presentFlat.setAreaSqrMeter(flat.getAreaSqrMeter());
            flatRepository.save(presentFlat);
        }else {
            return "redirect:/";
        }
        return "redirect:/";
    }
}
