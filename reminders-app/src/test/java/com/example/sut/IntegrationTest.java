package com.example.sut;

import static java.time.OffsetDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.example.sut.entities.Reminder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void addAndReadReminder() {
        Reminder reminder = new Reminder();
        reminder.setText("Buy flowers 1");
        reminder.setTime(now());
        reminder.setDone(false);
        restTemplate.put("/reminder", reminder);
        ResponseEntity<Reminder[]> reminders = restTemplate.getForEntity("/reminder", Reminder[].class);
        assertThat(reminders.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(reminders.getBody()).isNotEmpty();
        Reminder actualReminder = reminders.getBody()[reminders.getBody().length -1];
        assertThat(actualReminder).isEqualToComparingOnlyGivenFields(reminder, "text");
    }
}
