/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.edu.fesa.MedQuery.dtos;

import br.edu.fesa.MedQuery.enums.UserRole;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author hugok
 */
public record RegisterDto(@NotNull String email, @NotNull String password, @NotNull UserRole userRole) {

}
