package spring.mvc.housing.association.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.mvc.housing.association.model.Flat;
import spring.mvc.housing.association.model.HousingAssociation;
import spring.mvc.housing.association.repository.FlatRepository;
import spring.mvc.housing.association.repository.HousingAssociationRepository;
import spring.mvc.housing.association.repository.OccupantRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class HousingAssociationController {

    private HousingAssociationRepository housingAssociationRepository;
    private FlatRepository flatRepository;
    private OccupantRepository occupantRepository;

    private int presentHousingAssociationId;

    public HousingAssociationController(HousingAssociationRepository housingAssociationRepository,
                           FlatRepository flatRepository, OccupantRepository occupantRepository) {
        this.housingAssociationRepository = housingAssociationRepository;
        this.flatRepository = flatRepository;
        this.occupantRepository = occupantRepository;
    }

    @GetMapping("/housingAssociation")
    public String housingAssociationDetails(@RequestParam int id, Model model) {

        Optional<HousingAssociation> housingAssociationOptional = housingAssociationRepository.findById(id);

        if(housingAssociationOptional.isPresent()) {
            HousingAssociation housingAssociation = housingAssociationOptional.get();
            presentHousingAssociationId = housingAssociation.getId();
            Flat flat = new Flat();
            flat.setHousingAssociation(housingAssociation);
            double sum = calculateArea(housingAssociation.getFlats());
            int numberOfFlats = housingAssociation.getFlats().size();

            model.addAttribute("housingAssociation", housingAssociation);
            model.addAttribute("flat", flat);
            model.addAttribute("sum", sum);
            model.addAttribute("numberOfFlats", numberOfFlats);
        } else {
            return "redirect:/";
        }

        return "housingAssociation";
    }


    @GetMapping("/addFlat")
    public String home(Model model) {
        model.addAttribute("flat", new Flat());

        return "addFlat";
    }

    @PostMapping("/addflat")
    public String addFlat(Flat flat) {
        flatRepository.save(flat);

        return "redirect:/housingAssociation?id=" + flat.getHousingAssociation().getId();
    }

    @PostMapping("/editHousingAssociation")
    public String editHousingAssociation(HousingAssociation housingAssociation) {

            Optional<HousingAssociation> optionalHousingAssociation = housingAssociationRepository.findById(presentHousingAssociationId);

            if (optionalHousingAssociation.isPresent()) {
                HousingAssociation presentHousingAssociation = optionalHousingAssociation.get();
                presentHousingAssociation.setName(housingAssociation.getName());
                presentHousingAssociation.setNumber(housingAssociation.getNumber());
                presentHousingAssociation.setStreet(housingAssociation.getStreet());
                housingAssociationRepository.save(presentHousingAssociation);
            }else {
                return "redirect:/";
            }
        return "redirect:/";
    }

    @GetMapping ("/deleteHousingAssociation")
    public String deleteHousingAssociation(@RequestParam int id) {
        Optional<HousingAssociation> housingAssociationOptional = housingAssociationRepository.findById(id);
        List<Flat> flats = null;
        HousingAssociation housingAssociation = null;

        if (housingAssociationOptional.isPresent()) {
            housingAssociation = housingAssociationOptional.get();
            flats = housingAssociation.getFlats();
            if (flats.size() > 0) {
                return "redirect:/housingAssociation?id=" + housingAssociation.getId();
            }else {
                housingAssociationRepository.delete(housingAssociation);
            }
        }
        return "redirect:/";
    }

    public double calculateArea(List<Flat> flats) {
        double sum = 0;
        for (Flat flat: flats) {
            sum += flat.getAreaSqrMeter();
        }
        return sum;
    }

}

