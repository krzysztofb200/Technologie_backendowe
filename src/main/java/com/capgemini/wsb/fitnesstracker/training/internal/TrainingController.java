package com.capgemini.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllTrainings(){
        return trainingService.getAllTrainings().stream().map(trainingMapper::toDTO).toList();
    }

    @GetMapping(value = "/{userId}")
    public List<TrainingDto> getTrainingById(@PathVariable("userId") Long userId) {
        return trainingService.getTrainingById(userId).stream().map(trainingMapper::toDTO).toList();
    }

    @GetMapping(value = "/finished/{afterTime}")
    public List<TrainingDto> getAllTrainingsAfterDate(@PathVariable("afterTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date time){
        return trainingService.getAllTrainingsAfterDate(time).stream().map(trainingMapper::toDTO).toList();
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getAllTrainingsByActivity(@RequestParam("activityType") ActivityType activityType){
        return trainingService.getAllTrainingsByActivity(activityType).stream().map(trainingMapper::toDTO).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingUserDto trainingDto){
        return trainingMapper.toDTO(trainingService.createTraining(trainingMapper.toEntity(trainingDto)));
    }

    @PutMapping(value ="/{trainingId}")
    public Optional<TrainingDto> updateTraining(@PathVariable("trainingId") Long trainingId, @RequestBody TrainingUserDto trainingDTO){
        return trainingService.updateTraining(trainingId, trainingMapper.toEntity(trainingDTO)).map(trainingMapper::toDTO);
    }
}
