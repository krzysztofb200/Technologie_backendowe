package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    @GetMapping("/{id}")
    public Optional<User> getTrainingById(@PathVariable("id") Long id){
        return trainingService.getTraining(id);
    }

//- [ ] wyszukiwanie wszystkich treningów
//- [ ] wyszukiwanie treningów dla określonego Użytkownika:
//- [ ] wyszukiwanie wszystkich treningów zakończonych (po konkretnej zdefiniowanej dacie)
//- [ ] wyszukiwanie wszystkich treningów dla konkretnej aktywności (np. wszystkie treningi biegowe)
//- [ ] utworzenie nowego treningu
//- [ ] aktualizacja treningu (dowolnie wybrane pole np. dystans)
}
