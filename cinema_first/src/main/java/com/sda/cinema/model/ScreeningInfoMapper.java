package com.sda.cinema.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ScreeningInfoMapper implements RowMapper<ScreeningInfo> {
    @Override
    public ScreeningInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        ScreeningInfo screeningInfo = new ScreeningInfo();
        screeningInfo.setTitle(resultSet.getString("tytul"));
        screeningInfo.setAgeCategory(resultSet.getString("kat_wiek"));
        screeningInfo.setCinemaRoom(resultSet.getString("sala"));
        screeningInfo.setDuration(LocalTime.parse(resultSet.getString("czas_trwania")));
        screeningInfo.setGenre(resultSet.getString("gatunek"));
        screeningInfo.setId(resultSet.getInt("id"));
        screeningInfo.setLanguage(resultSet.getString("jezyk"));
        screeningInfo.setScreeningDate(LocalDate.parse(resultSet.getString("data")));
        screeningInfo.setScreeningTime(LocalTime.parse(resultSet.getString("godzina")));
        screeningInfo.setSoldTickets(resultSet.getInt("sprzedane_bilety"));

        return screeningInfo;
    }
}
