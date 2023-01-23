package com.example.smartagenda.utils;

import com.example.smartagenda.dto.ClientDto;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Objects {
    public static ClientDto client = new ClientDto("Alin", "Popescu", "0765432345", "alin_popescu@gmail.com", new Date(), "M");
    public static ClientDto client2 = new ClientDto("Alina", "Popescu", "0765432345", "alina_popescu@gmail.com", new Date(), "F");
    public static List<ClientDto> clients = List.of(new ClientDto[]{client, client2});

}
