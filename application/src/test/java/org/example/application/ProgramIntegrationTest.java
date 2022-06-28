package org.example.application;

import co.com.sofka.domain.program.command.CreateProgramCommand;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.restdocs.restassured3.RestDocumentationFilter;


import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProgramIntegrationTest extends CommandBaseIntegrationTest {

    @Test
    @Order(1)
    void createProgram() {
        var command = new CreateProgramCommand();
        command.setName("C2-2022 Ext");
        command.setProgramId("c2-2022");

        RestDocumentationFilter docs = getSpecDoc("createProgram", requestFields(
                fieldWithPath("name").description("Nombre del programa"),
                fieldWithPath("programId").description("Id del programa"),
                fieldWithPath("instant").description("Fecha del comando").optional(),
                fieldWithPath("type").description("Tipo del comando").optional()
        ));
        given(documentationSpec)
                .filter(docs)
                .contentType(ContentType.JSON)
                .body(command)
                .when()
                .post("/api/createProgram")
                .then()
                .assertThat().statusCode(Matchers.is(200));
    }


}