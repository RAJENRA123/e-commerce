package com.bikkadiT.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String categoryId;

    @NotBlank
    @Min(value = 4,message = "title must be of minimum 4 charecters!!")
    private String title;

    @NotBlank(message = "Description required")
    private String description;

    private String coverImage;
}
