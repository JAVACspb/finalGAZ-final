package com.example.casegpn.dto;

import com.example.casegpn.validator.RequestValid;
import lombok.*;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VkRequestDTO {

    @Size(min = 1)
    private String userId;
    @Size(min = 3,max = 32)
    @RequestValid(message = "ERROR")
    private String groupId;
}
