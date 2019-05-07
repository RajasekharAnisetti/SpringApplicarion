package com.java.examples.web.rest;

import com.java.examples.SpringApplicarionApp;
import com.java.examples.config.TestSecurityConfiguration;
import com.java.examples.domain.TransportModes;
import com.java.examples.repository.TransportModesRepository;
import com.java.examples.service.TransportModesService;
import com.java.examples.service.dto.TransportModesDTO;
import com.java.examples.service.mapper.TransportModesMapper;
import com.java.examples.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.java.examples.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link TransportModesResource} REST controller.
 */
@SpringBootTest(classes = {SpringApplicarionApp.class, TestSecurityConfiguration.class})
public class TransportModesResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private TransportModesRepository transportModesRepository;

    @Autowired
    private TransportModesMapper transportModesMapper;

    @Autowired
    private TransportModesService transportModesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTransportModesMockMvc;

    private TransportModes transportModes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransportModesResource transportModesResource = new TransportModesResource(transportModesService);
        this.restTransportModesMockMvc = MockMvcBuilders.standaloneSetup(transportModesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransportModes createEntity(EntityManager em) {
        TransportModes transportModes = new TransportModes()
            .name(DEFAULT_NAME);
        return transportModes;
    }

    @BeforeEach
    public void initTest() {
        transportModes = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransportModes() throws Exception {
        int databaseSizeBeforeCreate = transportModesRepository.findAll().size();

        // Create the TransportModes
        TransportModesDTO transportModesDTO = transportModesMapper.toDto(transportModes);
        restTransportModesMockMvc.perform(post("/api/transport-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportModesDTO)))
            .andExpect(status().isCreated());

        // Validate the TransportModes in the database
        List<TransportModes> transportModesList = transportModesRepository.findAll();
        assertThat(transportModesList).hasSize(databaseSizeBeforeCreate + 1);
        TransportModes testTransportModes = transportModesList.get(transportModesList.size() - 1);
        assertThat(testTransportModes.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createTransportModesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transportModesRepository.findAll().size();

        // Create the TransportModes with an existing ID
        transportModes.setId(1L);
        TransportModesDTO transportModesDTO = transportModesMapper.toDto(transportModes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransportModesMockMvc.perform(post("/api/transport-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportModesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransportModes in the database
        List<TransportModes> transportModesList = transportModesRepository.findAll();
        assertThat(transportModesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTransportModes() throws Exception {
        // Initialize the database
        transportModesRepository.saveAndFlush(transportModes);

        // Get all the transportModesList
        restTransportModesMockMvc.perform(get("/api/transport-modes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transportModes.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getTransportModes() throws Exception {
        // Initialize the database
        transportModesRepository.saveAndFlush(transportModes);

        // Get the transportModes
        restTransportModesMockMvc.perform(get("/api/transport-modes/{id}", transportModes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transportModes.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTransportModes() throws Exception {
        // Get the transportModes
        restTransportModesMockMvc.perform(get("/api/transport-modes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransportModes() throws Exception {
        // Initialize the database
        transportModesRepository.saveAndFlush(transportModes);

        int databaseSizeBeforeUpdate = transportModesRepository.findAll().size();

        // Update the transportModes
        TransportModes updatedTransportModes = transportModesRepository.findById(transportModes.getId()).get();
        // Disconnect from session so that the updates on updatedTransportModes are not directly saved in db
        em.detach(updatedTransportModes);
        updatedTransportModes
            .name(UPDATED_NAME);
        TransportModesDTO transportModesDTO = transportModesMapper.toDto(updatedTransportModes);

        restTransportModesMockMvc.perform(put("/api/transport-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportModesDTO)))
            .andExpect(status().isOk());

        // Validate the TransportModes in the database
        List<TransportModes> transportModesList = transportModesRepository.findAll();
        assertThat(transportModesList).hasSize(databaseSizeBeforeUpdate);
        TransportModes testTransportModes = transportModesList.get(transportModesList.size() - 1);
        assertThat(testTransportModes.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingTransportModes() throws Exception {
        int databaseSizeBeforeUpdate = transportModesRepository.findAll().size();

        // Create the TransportModes
        TransportModesDTO transportModesDTO = transportModesMapper.toDto(transportModes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransportModesMockMvc.perform(put("/api/transport-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportModesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransportModes in the database
        List<TransportModes> transportModesList = transportModesRepository.findAll();
        assertThat(transportModesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransportModes() throws Exception {
        // Initialize the database
        transportModesRepository.saveAndFlush(transportModes);

        int databaseSizeBeforeDelete = transportModesRepository.findAll().size();

        // Delete the transportModes
        restTransportModesMockMvc.perform(delete("/api/transport-modes/{id}", transportModes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<TransportModes> transportModesList = transportModesRepository.findAll();
        assertThat(transportModesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransportModes.class);
        TransportModes transportModes1 = new TransportModes();
        transportModes1.setId(1L);
        TransportModes transportModes2 = new TransportModes();
        transportModes2.setId(transportModes1.getId());
        assertThat(transportModes1).isEqualTo(transportModes2);
        transportModes2.setId(2L);
        assertThat(transportModes1).isNotEqualTo(transportModes2);
        transportModes1.setId(null);
        assertThat(transportModes1).isNotEqualTo(transportModes2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransportModesDTO.class);
        TransportModesDTO transportModesDTO1 = new TransportModesDTO();
        transportModesDTO1.setId(1L);
        TransportModesDTO transportModesDTO2 = new TransportModesDTO();
        assertThat(transportModesDTO1).isNotEqualTo(transportModesDTO2);
        transportModesDTO2.setId(transportModesDTO1.getId());
        assertThat(transportModesDTO1).isEqualTo(transportModesDTO2);
        transportModesDTO2.setId(2L);
        assertThat(transportModesDTO1).isNotEqualTo(transportModesDTO2);
        transportModesDTO1.setId(null);
        assertThat(transportModesDTO1).isNotEqualTo(transportModesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(transportModesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(transportModesMapper.fromId(null)).isNull();
    }
}
