package com.jose.backend.usersapp.backendusersapp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.jose.backend.usersapp.backendusersapp.controllers.EtablissementController;
import com.jose.backend.usersapp.backendusersapp.models.dto.EtablissementDto;
import com.jose.backend.usersapp.backendusersapp.services.implementation.EtablissementServiceImp;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class EtablissementControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private EtablissementController etablissementController;

    @Mock
    private EtablissementServiceImp etablissementService;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(etablissementController).build();
    }

    @Test
    public void testListEtablissements() throws Exception {
    // Créer un exemple de liste EtablissementDto pour simuler la réponse du service
        List<EtablissementDto> etablissementList = new ArrayList<>();
        etablissementList.add(new EtablissementDto(1L, "Etablissement1", "123456789", "Rue1", 
        "12345", "Ville1", "12345", null, null, true));
        etablissementList.add(new EtablissementDto(2L, "Etablissement2", "987654321", "Rue2", 
        "54321", "Ville2", "54321", null, null, true));

        // Configuration de comportement de servicio mock
        when(etablissementService.findAll()).thenReturn(etablissementList);

        // Simulation de demande GET  al endpoint de controller
        ResultActions resultActions = mockMvc.perform(get("/etablissements")
                .contentType(MediaType.APPLICATION_JSON));

        // Verification 200 (OK)
        resultActions.andExpect(status().isOk());

        // Verification de reponse JSON 
        resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(jsonPath("$[0].nom").value("Etablissement1"));
        resultActions.andExpect(jsonPath("$[1].nom").value("Etablissement2"));
    }
}