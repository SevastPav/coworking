package org.spm.coworking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.spm.coworking.entity.Rle;
import org.spm.coworking.entity.Training;
import org.spm.coworking.entity.UserProfile;
import org.spm.coworking.repository.TrainingRepository;
import org.spm.coworking.repository.UserProfileRepository;

import java.util.Collections;
import java.util.Optional;

@Controller
public class MainController {

    private final UserProfileRepository userProfileRepository;

    private final TrainingRepository trainingRepository;

    public MainController(UserProfileRepository userProfileRepository, TrainingRepository trainingRepository) {
        this.userProfileRepository = userProfileRepository;
        this.trainingRepository = trainingRepository;
    }

    //Мы могли бы расписать эти 2 маппинга отдельно, но смысла дублировать одинаковый код нет.
    // этот метод будет слушать запросы на "/" и "/index"
    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "/welcomePage.xhtml";
    }

    @GetMapping("/login")
    public String login() {
        return "/loginPage.xhtml";
    }

/*    @PostMapping("/login")
    public String loginPost() {
        return "/welcomePage.xhtml";
    }*/

    @GetMapping("/generation")
    public String generation() {
        return "/generationPage.xhtml";
    }

    @GetMapping("/registration")
    public String registtration() {
        return "/registrationPage.xhtml";
    }

    @PostMapping("/registration")
    public String addUser(UserProfile user) {
        user.setFio("test");
        user.setRoles(Collections.singleton(Rle.USER));
        userProfileRepository.save(user);
        return "/registrationPage.xhtml";
    }

    @GetMapping("/home")
    public String home() {
        return "/welcomePage.xhtml";
    }

    @GetMapping("/training_desc")
    public String trainingDesc() {
        return "/trainingDescPage.xhtml";
    }

    @GetMapping("/statistic")
    public String statistic() {
        return "/statisticPage.xhtml";
    }

    @GetMapping("/services")
    public String services() {
        return "/servicesPage.xhtml";
    }

    @GetMapping("/timetable")
    public String timetable() {
        return "/timetablePage.xhtml";
    }

    @GetMapping("/contacts")
    public String contacts() {
        return "/contactsPage.xhtml";
    }

    @GetMapping("/create_training")
    public String createTraining() {
        return "/createTrainingPage.xhtml";
    }

    @GetMapping("/training_list")
    public String trainingList() {
        return "/trainingListPage.xhtml";
    }

    @GetMapping("/delete_training")
    public String deleteTraining(@RequestParam(name = "id") Long id) {
        Optional<Training> training = trainingRepository.findByTrainingId(id);
        training.ifPresent(trainingRepository::delete);
        return "/trainingListPage.xhtml";
    }

}