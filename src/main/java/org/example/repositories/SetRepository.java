package org.example.repositories;

import org.apache.logging.log4j.LogManager;
import org.example.entities.Set;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class SetRepository extends _BaseRepository implements _Logger<SetRepository>{


    public static final String TB_NAME = "SET_CARDS";

    public static final Map<String, String> TB_COLUMNS = Map.of(
            "NAME", "NAME",
            "ID", "ID",
            "SET_PATCH", "SET_PATCH",
            "RELEASEDATE", "RELEASEDATE",
            "SET_SIZE", "SET_SIZE"
    );

    public SetRepository(){
        Initialize();

    }

    public void Initialize() {
        try {
            if (!tableExists(conn, TB_NAME)) {
                try (var stmt = conn.prepareStatement(
                        "CREATE TABLE %s (%s NUMBER GENERATED AS IDENTITY CONSTRAINT %s_PK PRIMARY KEY,"
                                .formatted(TB_NAME,
                                        TB_COLUMNS.get("ID"),
                                        TB_NAME) +
                                "%s VARCHAR2(50) NOT NULL,".formatted(TB_COLUMNS.get("NAME")) +
                                "%s VARCHAR2(20) NOT NULL,".formatted(TB_COLUMNS.get("SET_PATCH")) +
                                "%s VARCHAR2(20) NOT NULL,".formatted(TB_COLUMNS.get("RELEASEDATE")) +
                                "%s NUMBER(3) NOT NULL)".formatted(TB_COLUMNS.get("SET_SIZE")))) {
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

    public List<Set> readAll(){
        var sets = new ArrayList<Set>();
        try(var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " ORDER BY ID")) {
            var rs = stmt.executeQuery();
            while (rs.next()){
                sets.add(new Set(rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getString("SET_PATCH"),
                        rs.getString("RELEASEDATE"),
                        rs.getInt("SET_SIZE")));
            }
        } catch (SQLException e) {
            logError("Erro ao retornar Set: ");
            e.printStackTrace();
        }
        return sets;
    }

    public Optional<Set> findById(int id){
        try(var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE ID = ?")) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Set(rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getString("SET_PATCH"),
                        rs.getString("RELEASEDATE"),
                        rs.getInt("SET_SIZE")));
            }
        }catch (Exception e){
            logError("Erro ao localizar ID: ");
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void create(Set set){
        try(var stmt = conn.prepareStatement(
                "INSERT INTO %s(%s, %s, %s, %s) VALUES(?, ?, ?, ?)"
                        .formatted(TB_NAME,
                                TB_COLUMNS.get("NAME"),
                                TB_COLUMNS.get("SET_PATCH"),
                                TB_COLUMNS.get("RELEASEDATE"),
                                TB_COLUMNS.get("SET_SIZE")))) {
            stmt.setString(1, set.getName());
            stmt.setString(2, set.getPatch());
            stmt.setString(3, set.getReleaseDate());
            stmt.setInt(4, set.getSize());
            logInfo("Set criado com sucesso!");
            stmt.executeUpdate();
        } catch (SQLException e) {
            logError("Erro ao adicionar ao Banco de Dados: ");
            e.printStackTrace();
        }
    }

    public void update(int id, Set set){
        try(var stmt = conn.prepareStatement(
                "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE ID = ? "
                    .formatted(TB_NAME,
                                TB_COLUMNS.get("NAME"),
                                TB_COLUMNS.get("SET_PATCH"),
                                TB_COLUMNS.get("RELEASEDATE"),
                                TB_COLUMNS.get("SET_SIZE")))) {
            stmt.setString(1, set.getName());
            stmt.setString(2, set.getPatch());
            stmt.setString(3, set.getReleaseDate());
            stmt.setInt(4, set.getSize());
            stmt.setInt(5, set.getId());
            stmt.executeUpdate();
            logInfo("Set atualizado com sucesso!");
        } catch (SQLException e) {
            logError("Erro ao atualizar Set: ");
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
