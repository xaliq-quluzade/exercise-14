package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalculationRepository {
    void saveCalculationResult(int a, int b, int result, String method) {
        try (Connection c = JDBCConnection.connect()) {
            PreparedStatement ps = c.prepareStatement("""
                    insert into "calculation".calculation_result(variable_a,variable_b,calc_result,calc_method)
                    values(?,?,?,?)
                    """);

            ps.setInt(1, a);
            ps.setInt(2, b);
            ps.setInt(3, result);
            ps.setString(4, method);

            ps.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void updateCalculationResult(int id, int a, int b, int result, String method) {
        try (Connection c = JDBCConnection.connect()) {
            PreparedStatement ps = c.prepareStatement("""
                    UPDATE "calculation".calculation_result
                    SET variable_a = ?, variable_b = ?, calc_result = ?, calc_method = ?
                    WHERE id = ?
                    """);

            ps.setInt(1, a);
            ps.setInt(2, b);
            ps.setInt(3, result);
            ps.setString(4, method);
            ps.setInt(5, id);

            ps.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void deleteCalculationResult(int id) {
        try (Connection c = JDBCConnection.connect()) {
            PreparedStatement ps = c.prepareStatement("""
                    delete from "calculation".calculation_result where id = ?
                    """);

            ps.setInt(1, id);

            ps.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    List<CalculationResultDTO> getCalculationResult() {
        try (Connection c = JDBCConnection.connect()) {
            List<CalculationResultDTO> results = new ArrayList<>();
            PreparedStatement ps = c.prepareStatement("""
                    select * from "calculation".calculation_result
                    """);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int a = rs.getInt("variable_a");
                int b = rs.getInt("variable_b");
                int result = rs.getInt("calc_result");
                String method = rs.getString("calc_method");

                results.add(new CalculationResultDTO(a, b, result, method));
            }
            return results;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
