package DAO.Interfaces;

import Entities.Instrument;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public interface IInstrumentDAO {
    void addInstrument(Instrument instrument);
    List<Instrument> readInstruments() throws JsonProcessingException;
    List<Instrument> findInstrumentsName(String name) throws JsonProcessingException;
    List<Instrument> findInstrumentsCost(float cost) throws JsonProcessingException;
    void deleteInstrument(int id);
}
