package com.spectral.demoapplication.controller;

import com.spectral.demoapplication.model.dto.UserDto;
import com.spectral.demoapplication.model.form.UserCreateForm;
import com.spectral.demoapplication.model.form.UserUpdateForm;
import com.spectral.demoapplication.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(value = "User Controller", description = "CRUD functionality for user entity")
public class UserController {

    private final UserService service;

    @ApiOperation(value = "Create user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@ApiParam(value = "Create user form", required = true) @Validated @RequestBody UserCreateForm form) {
        service.createUser(form);
    }

    @ApiOperation(value = "Get user by filter, or get all users (send request without parameters)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserDto.class),
            @ApiResponse(code = 500, message = "Internal server error")})
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsersByFilter(@RequestParam(value = "name", required = false) String name,
                                                          @RequestParam(value = "email", required = false) String email,
                                                          @RequestParam(value = "countryName", required = false) String countryName,
                                                          @RequestParam(value = "roleName", required = false) String roleName,
                                                          @RequestParam(value = "dateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
                                                          @RequestParam(value = "dateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo) {
        return ResponseEntity.ok(service.getUsersByFilter(name, email, countryName, roleName, dateFrom, dateTo));
    }

    @ApiOperation(value = "Update user by update form")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(@ApiParam(value = "Update user form", required = true) @Validated @RequestBody UserUpdateForm form) {
        service.updateUser(form);
    }

    @ApiOperation(value = "Delete user by user id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@ApiParam(value = "User id for deletion", required = true) @PathVariable Long userId) {
        service.deleteUserById(userId);
    }
}
