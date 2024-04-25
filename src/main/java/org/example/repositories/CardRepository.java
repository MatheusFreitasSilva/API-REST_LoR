package org.example.repositories;

import org.example.entities.Card;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CardRepository extends _BaseRepository implements _Logger<Card>{

    public static final String TB_NAME = "CARD";

    public static final Map<String, String> TB_COLUMNS = Map.of(
            "NAME", "NAME",
            "TYPE", "TYPE",
            "REGION", "REGION",
            "FLAVOR", "FLAVOR",
            "DESCRIPTION", "DESCRIPTION",
            "LEVELUP", "LEVELUP",
            "RARITY", "RARITY",
            "POWER", "POWER",
            "HEALTH", "HEALTH",
            "COST", "COST"
    );

    public static final Map<String, String> TB_COLUMNS2 = Map.of(
            "SET_ID", "SET_ID",
            "ID", "ID"
    );

    public CardRepository(){
        Initialize();
    }

    public void Initialize(){
        try {
            if (!tableExists(conn, TB_NAME)) {
                try (var stmt = conn.prepareStatement(
                        "CREATE TABLE %s (%s NUMBER GENERATED AS IDENTITY CONSTRAINT %s_PK PRIMARY KEY,"
                                .formatted(TB_NAME,
                                        TB_COLUMNS2.get("ID"),
                                        TB_NAME) +
                                "%s VARCHAR2(50) NOT NULL,".formatted(TB_COLUMNS.get("NAME")) +
                                "%s VARCHAR2(20) NOT NULL,".formatted(TB_COLUMNS.get("TYPE")) +
                                "%s VARCHAR2(20) NOT NULL,".formatted(TB_COLUMNS.get("REGION")) +
                                "%s NUMBER(3) NOT NULL,".formatted(TB_COLUMNS.get("COST")) +
                                "%s VARCHAR2(300) NOT NULL,".formatted(TB_COLUMNS.get("DESCRIPTION")) +
                                "%s NUMBER(3) NOT NULL,".formatted(TB_COLUMNS.get("POWER")) +
                                "%s NUMBER(3) NOT NULL,".formatted(TB_COLUMNS.get("HEALTH")) +
                                "%s VARCHAR2(20) NOT NULL,".formatted(TB_COLUMNS.get("RARITY")) +
                                "%s VARCHAR2(50) NOT NULL,".formatted(TB_COLUMNS.get("LEVELUP")) +
                                "%s VARCHAR2(50) NOT NULL,".formatted(TB_COLUMNS.get("FLAVOR")) +
                                "%s NUMBER NOT NULL,".formatted(TB_COLUMNS2.get("SET_ID")) +
                                "CONSTRAINT FK_%s FOREIGN KEY (%s) REFERENCES %s(%s))"
                                        .formatted(TB_COLUMNS2.get("SET_ID"),
                                                TB_COLUMNS2.get("SET_ID"),
                                                SetRepository.TB_NAME,
                                                SetRepository.TB_COLUMNS.get("ID")))) {
                    logInfo("Tabela " + TB_NAME + " criada com sucesso");
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    logError("Erro ao criar a tabela " + TB_NAME + ": ");
                    e.printStackTrace();
                }
            }
            else {
                logInfo("Tabela " + TB_NAME + " Já existe.");
            }
        } catch (SQLException e) {
            logError("Erro ao conectar ao banco de dados: ");
            e.printStackTrace();
        }
    }

    public List<Card> readAll(){
        var cards = new ArrayList<Card>();
        try(var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " ORDER BY ID")) {
            var rs = stmt.executeQuery();
            while (rs.next()){
                cards.add(new Card(rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getString("TYPE"),
                        rs.getString("REGION"),
                        rs.getString("FLAVOR"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("LEVELUP"),
                        rs.getString("RARITY"),
                        rs.getInt("POWER"),
                        rs.getInt("HEALTH"),
                        rs.getInt("COST"),
                        rs.getInt("SET_ID")));
            }
        } catch (SQLException e) {
            logError("Erro ao retornar Card: ");
            e.printStackTrace();
        }
        return cards;
    }

    public Optional<Card> findById(int id){
        try(var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE ID = ?")) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Card(rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getString("TYPE"),
                        rs.getString("REGION"),
                        rs.getString("FLAVOR"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("LEVELUP"),
                        rs.getString("RARITY"),
                        rs.getInt("POWER"),
                        rs.getInt("HEALTH"),
                        rs.getInt("COST"),
                        rs.getInt("SET_ID")));
            }
        }catch (Exception e){
            logError("Erro ao localizar ID: ");
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void create(Card card){
        try(var stmt = conn.prepareStatement(
                "INSERT INTO %s(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                        .formatted(TB_NAME,
                                TB_COLUMNS.get("NAME"),
                                TB_COLUMNS.get("TYPE"),
                                TB_COLUMNS.get("REGION"),
                                TB_COLUMNS.get("FLAVOR"),
                                TB_COLUMNS.get("DESCRIPTION"),
                                TB_COLUMNS.get("LEVELUP"),
                                TB_COLUMNS.get("RARITY"),
                                TB_COLUMNS.get("POWER"),
                                TB_COLUMNS.get("HEALTH"),
                                TB_COLUMNS.get("COST"),
                                TB_COLUMNS2.get("SET_ID")))) {
            stmt.setString(1, card.getName());
            stmt.setString(2, card.getType());
            stmt.setString(3, card.getRegion());
            stmt.setString(4, card.getFlavor());
            stmt.setString(5, card.getDescription());
            stmt.setString(6, card.getLevelUp());
            stmt.setString(7, card.getRarity());
            stmt.setInt(8, card.getPower());
            stmt.setInt(9, card.getHealth());
            stmt.setInt(10, card.getCost());
            stmt.setInt(11, card.getSetId());
            logInfo("Card criado com sucesso!");
            stmt.executeUpdate();
        } catch (SQLException e) {
            logError("Erro ao adicionar ao Banco de Dados: ");
            e.printStackTrace();
        }
    }

    public void update(int id, Card card){
        try(var stmt = conn.prepareStatement(
                ("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE ID = ? ")
                        .formatted(TB_NAME,
                                TB_COLUMNS.get("NAME"),
                                TB_COLUMNS.get("TYPE"),
                                TB_COLUMNS.get("REGION"),
                                TB_COLUMNS.get("FLAVOR"),
                                TB_COLUMNS.get("DESCRIPTION"),
                                TB_COLUMNS.get("LEVELUP"),
                                TB_COLUMNS.get("RARITY"),
                                TB_COLUMNS.get("POWER"),
                                TB_COLUMNS.get("HEALTH"),
                                TB_COLUMNS.get("COST"),
                                TB_COLUMNS2.get("SET_ID")))) {
            stmt.setString(1, card.getName());
            stmt.setString(2, card.getType());
            stmt.setString(3, card.getRegion());
            stmt.setString(4, card.getFlavor());
            stmt.setString(5, card.getDescription());
            stmt.setString(6, card.getLevelUp());
            stmt.setString(7, card.getRarity());
            stmt.setInt(8, card.getPower());
            stmt.setInt(9, card.getHealth());
            stmt.setInt(10, card.getCost());
            stmt.setInt(11, card.getSetId());
            stmt.setInt(12, id);
            stmt.executeUpdate();
            logInfo("Card atualizado com sucesso!");
        } catch (SQLException e) {
            logError("Erro ao atualizar Card: ");
            e.printStackTrace();
        }
    }

    public void deleteById(int id){
        try(var stmt = conn.prepareStatement("DELETE FROM %s WHERE ID = ?"
                .formatted(TB_NAME))) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            logInfo("Delete completado com sucesso! ");
        } catch (SQLException e){
            logError("Erro ao realizar Delete: ");
            e.printStackTrace();
        }
    }
}
