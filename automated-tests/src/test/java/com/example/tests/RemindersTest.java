package com.example.tests;

import static java.time.OffsetDateTime.now;

import com.example.entities.Reminder;
import com.example.steps.ui.CommonUiSteps;
import com.example.steps.ui.NavigationSteps;
import com.example.steps.ui.ReminderSteps;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RemindersTest {

    @DataProvider
    public Object[][] createReminderData() {
        return new Object[][]{
              new Object[]{
                    new Reminder()
                          .text("Buy flowers")
                          .time(now().plusDays(1))
                          .done(false)
              }
        };
    }

    @Test(dataProvider = "createReminderData")
    public void createReminder(Reminder reminder) {
        NavigationSteps.openRemindersApp();
        ReminderSteps.addReminder(reminder);
        ReminderSteps.checkLastReminder(reminder);
    }

    @DataProvider
    public Object[][] updateReminderData() {
        return new Object[][]{
              new Object[]{
                    new Reminder().text("Old text").time(now().plusDays(1)).done(false),
                    new Reminder().text("New text").time(now().plusDays(2)).done(false)
              }
        };
    }

    @Test(dataProvider = "updateReminderData")
    public void updateReminder(Reminder oldReminder, Reminder newReminder) {
        NavigationSteps.openRemindersApp();
        ReminderSteps.addReminder(oldReminder);
        ReminderSteps.updateLastReminder(newReminder);
        CommonUiSteps.refreshPage();
        ReminderSteps.checkLastReminder(newReminder);
    }

    @DataProvider
    public Object[][] deleteReminderData() {
        return new Object[][]{
              new Object[]{
                    new Reminder().text("Delete me").time(now().plusDays(5)).done(true)
              }
        };
    }

    @Test(dataProvider = "deleteReminderData")
    public void deleteReminder(Reminder reminder) {
        NavigationSteps.openRemindersApp();
        ReminderSteps.addReminder(reminder);
        ReminderSteps.deleteLastReminder();
        CommonUiSteps.refreshPage();
        ReminderSteps.checkReminderIsAbsent(reminder);
    }

}
