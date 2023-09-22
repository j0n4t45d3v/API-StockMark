package br.com.jonatas.sectorservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseMessage(
        @JsonProperty("message")
        String message
) {
}
