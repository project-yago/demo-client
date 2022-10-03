package com.yago.app.demo.client.java8.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private String id;

    private String name;

    private String description;

    private String type;

    private String category;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("home_url")
    private String homeUrl;

    @JsonProperty("create_time")
    private String createTime;

    @JsonProperty("update_time")
    private String updateTime;
}
