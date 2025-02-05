package com.mycinelist.mycinelist_backend;

import com.mycinelist.mycinelist_backend.controller.MovieSelectionDayController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UpdateTheMovie {
    @Autowired
    private MovieSelectionDayController movieSelectionDayController;

    @Scheduled(cron = "0 30 09 * * ?")
    public void distributeReports() throws InterruptedException {
        System.out.println("coucou");
        movieSelectionDayController.updateMovie();
    }
}
