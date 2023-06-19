package com.bikkadiT.demo.dtos;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponse {
    private String imageName;
    private String message;
    private String status;
    private String success;
    private String contentType;

}
