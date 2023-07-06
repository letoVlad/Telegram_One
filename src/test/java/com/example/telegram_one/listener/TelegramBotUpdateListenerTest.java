package com.example.telegram_one.listener;

import com.example.telegram_one.sevice.NotificationTaskService;
import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class TelegramBotUpdateListenerTest {

    @Mock
    private TelegramBot telegramBot;

    @Mock
    private NotificationTaskService notificationTaskService;

    @InjectMocks
    private TelegramBotUpdateListener telegramBotUpdateListener;

    @Test
    public void handleStartTest() throws URISyntaxException, IOException {
        String json =
                Files.readString(Path.of(TelegramBotUpdateListenerTest.class.getResource("update.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%text%", "/start"), Update.class);
        telegramBotUpdateListener.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBot).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();

        Assertions.assertThat(actual.getParameters().get("chat_id")).isEqualTo(update.message().chat().id());
        Assertions.assertThat(actual.getParameters().get("text")).isEqualTo(
                """
                        Привет! Я помогу тебе запланировать задачу, отправь её в формате 12.03.2023 21:00 Сделать домашку
                        """);
    }
}
