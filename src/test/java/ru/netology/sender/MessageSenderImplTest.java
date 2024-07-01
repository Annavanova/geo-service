package ru.netology.sender;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;
import static ru.netology.geo.GeoServiceImpl.NEW_YORK_IP;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

class MessageSenderImplTest {

    @Test
    void sendTextRus() {
        String ipRu = "172.152.123.234";

        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        HashMap<String, String> mapRu = new HashMap<>();
        mapRu.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipRu);

        String messRus = messageSender.send(mapRu);

        String expected = "Добро пожаловать";
        // assert
        assertEquals(expected, messRus);
    }


    @Test
    void sendTextRusIP() {

        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(MOSCOW_IP))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        HashMap<String, String> mapRu = new HashMap<>();
        mapRu.put(MessageSenderImpl.IP_ADDRESS_HEADER, MOSCOW_IP);

        String messRus = messageSender.send(mapRu);

        String expected = "Добро пожаловать";
        // assert
        assertEquals(expected, messRus);
    }
    @Test
    void sendTextEng() {
        String ipEn = "96.134.345.134";
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.startsWith("96.")))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        HashMap mapEn = new HashMap<>();
        mapEn.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipEn);
        String messEn = messageSender.send(mapEn);
        String expected = "Welcome";

        // assert
        assertEquals(expected, messEn);
    }

}