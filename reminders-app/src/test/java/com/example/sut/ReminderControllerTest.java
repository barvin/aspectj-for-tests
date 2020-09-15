package com.example.sut;

import static java.time.OffsetDateTime.now;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.sut.controller.ReminderController;
import com.example.sut.data.RemindersStorage;
import com.example.sut.entities.Reminder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ReminderController.class)
public class ReminderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void readReminders() throws Exception {
        Reminder reminder = new Reminder();
        reminder.setText("Buy flowers");
        reminder.setTime(now());
        reminder.setDone(false);

        RemindersStorage.clearStorage();
        RemindersStorage.add(reminder);

        mockMvc.perform(MockMvcRequestBuilders.get("/reminder"))
              .andExpect(status().isOk())
              .andExpect(jsonPath("[0].text").value("Buy flowers"));
    }
}
