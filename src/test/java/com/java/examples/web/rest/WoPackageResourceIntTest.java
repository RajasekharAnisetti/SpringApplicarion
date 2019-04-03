package com.java.examples.web.rest;

import com.java.examples.SpringApplicarionApp;

import com.java.examples.domain.WoPackage;
import com.java.examples.repository.WoPackageRepository;
import com.java.examples.service.WoPackageService;
import com.java.examples.service.dto.WoPackageDTO;
import com.java.examples.service.mapper.WoPackageMapper;
import com.java.examples.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
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
 * Test class for the WoPackageResource REST controller.
 *
 * @see WoPackageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringApplicarionApp.class)
public class WoPackageResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_LENGTH = 11;
    private static final Integer UPDATED_LENGTH = 10;

    private static final Integer DEFAULT_WIDTH = 11;
    private static final Integer UPDATED_WIDTH = 10;

    private static final Integer DEFAULT_HEIGHT = 11;
    private static final Integer UPDATED_HEIGHT = 10;

    private static final Integer DEFAULT_WEIGHT = 11;
    private static final Integer UPDATED_WEIGHT = 10;

    private static final Integer DEFAULT_CUBED_WEIGHT = 11;
    private static final Integer UPDATED_CUBED_WEIGHT = 10;

    private static final String DEFAULT_TRACKING_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TRACKING_NUMBER = "BBBBBBBBBB";

    private static final Double DEFAULT_COD_VALUE = 1D;
    private static final Double UPDATED_COD_VALUE = 2D;

    private static final Double DEFAULT_INSURANCE_AMOUNT = 1D;
    private static final Double UPDATED_INSURANCE_AMOUNT = 2D;

    private static final Integer DEFAULT_OID = 20;
    private static final Integer UPDATED_OID = 19;

    private static final Integer DEFAULT_POSITION = 11;
    private static final Integer UPDATED_POSITION = 10;

    private static final String DEFAULT_FREIGHT_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_FREIGHT_CLASS = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    @Autowired
    private WoPackageRepository woPackageRepository;

    @Autowired
    private WoPackageMapper woPackageMapper;

    @Autowired
    private WoPackageService woPackageService;

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

    private MockMvc restWoPackageMockMvc;

    private WoPackage woPackage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WoPackageResource woPackageResource = new WoPackageResource(woPackageService);
        this.restWoPackageMockMvc = MockMvcBuilders.standaloneSetup(woPackageResource)
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
    public static WoPackage createEntity(EntityManager em) {
        WoPackage woPackage = new WoPackage()
            .description(DEFAULT_DESCRIPTION)
            .length(DEFAULT_LENGTH)
            .width(DEFAULT_WIDTH)
            .height(DEFAULT_HEIGHT)
            .weight(DEFAULT_WEIGHT)
            .cubedWeight(DEFAULT_CUBED_WEIGHT)
            .trackingNumber(DEFAULT_TRACKING_NUMBER)
            .codValue(DEFAULT_COD_VALUE)
            .insuranceAmount(DEFAULT_INSURANCE_AMOUNT)
            .oid(DEFAULT_OID)
            .position(DEFAULT_POSITION)
            .freightClass(DEFAULT_FREIGHT_CLASS)
            .type(DEFAULT_TYPE);
        return woPackage;
    }

    @Before
    public void initTest() {
        woPackage = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoPackage() throws Exception {
        int databaseSizeBeforeCreate = woPackageRepository.findAll().size();

        // Create the WoPackage
        WoPackageDTO woPackageDTO = woPackageMapper.toDto(woPackage);
        restWoPackageMockMvc.perform(post("/api/wo-packages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woPackageDTO)))
            .andExpect(status().isCreated());

        // Validate the WoPackage in the database
        List<WoPackage> woPackageList = woPackageRepository.findAll();
        assertThat(woPackageList).hasSize(databaseSizeBeforeCreate + 1);
        WoPackage testWoPackage = woPackageList.get(woPackageList.size() - 1);
        assertThat(testWoPackage.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testWoPackage.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testWoPackage.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testWoPackage.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testWoPackage.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testWoPackage.getCubedWeight()).isEqualTo(DEFAULT_CUBED_WEIGHT);
        assertThat(testWoPackage.getTrackingNumber()).isEqualTo(DEFAULT_TRACKING_NUMBER);
        assertThat(testWoPackage.getCodValue()).isEqualTo(DEFAULT_COD_VALUE);
        assertThat(testWoPackage.getInsuranceAmount()).isEqualTo(DEFAULT_INSURANCE_AMOUNT);
        assertThat(testWoPackage.getOid()).isEqualTo(DEFAULT_OID);
        assertThat(testWoPackage.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testWoPackage.getFreightClass()).isEqualTo(DEFAULT_FREIGHT_CLASS);
        assertThat(testWoPackage.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createWoPackageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woPackageRepository.findAll().size();

        // Create the WoPackage with an existing ID
        woPackage.setId(1L);
        WoPackageDTO woPackageDTO = woPackageMapper.toDto(woPackage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoPackageMockMvc.perform(post("/api/wo-packages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woPackageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoPackage in the database
        List<WoPackage> woPackageList = woPackageRepository.findAll();
        assertThat(woPackageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllWoPackages() throws Exception {
        // Initialize the database
        woPackageRepository.saveAndFlush(woPackage);

        // Get all the woPackageList
        restWoPackageMockMvc.perform(get("/api/wo-packages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woPackage.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH)))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].cubedWeight").value(hasItem(DEFAULT_CUBED_WEIGHT)))
            .andExpect(jsonPath("$.[*].trackingNumber").value(hasItem(DEFAULT_TRACKING_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].codValue").value(hasItem(DEFAULT_COD_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].insuranceAmount").value(hasItem(DEFAULT_INSURANCE_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].oid").value(hasItem(DEFAULT_OID)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].freightClass").value(hasItem(DEFAULT_FREIGHT_CLASS.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getWoPackage() throws Exception {
        // Initialize the database
        woPackageRepository.saveAndFlush(woPackage);

        // Get the woPackage
        restWoPackageMockMvc.perform(get("/api/wo-packages/{id}", woPackage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(woPackage.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.cubedWeight").value(DEFAULT_CUBED_WEIGHT))
            .andExpect(jsonPath("$.trackingNumber").value(DEFAULT_TRACKING_NUMBER.toString()))
            .andExpect(jsonPath("$.codValue").value(DEFAULT_COD_VALUE.doubleValue()))
            .andExpect(jsonPath("$.insuranceAmount").value(DEFAULT_INSURANCE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.oid").value(DEFAULT_OID))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.freightClass").value(DEFAULT_FREIGHT_CLASS.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWoPackage() throws Exception {
        // Get the woPackage
        restWoPackageMockMvc.perform(get("/api/wo-packages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoPackage() throws Exception {
        // Initialize the database
        woPackageRepository.saveAndFlush(woPackage);

        int databaseSizeBeforeUpdate = woPackageRepository.findAll().size();

        // Update the woPackage
        WoPackage updatedWoPackage = woPackageRepository.findById(woPackage.getId()).get();
        // Disconnect from session so that the updates on updatedWoPackage are not directly saved in db
        em.detach(updatedWoPackage);
        updatedWoPackage
            .description(UPDATED_DESCRIPTION)
            .length(UPDATED_LENGTH)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .cubedWeight(UPDATED_CUBED_WEIGHT)
            .trackingNumber(UPDATED_TRACKING_NUMBER)
            .codValue(UPDATED_COD_VALUE)
            .insuranceAmount(UPDATED_INSURANCE_AMOUNT)
            .oid(UPDATED_OID)
            .position(UPDATED_POSITION)
            .freightClass(UPDATED_FREIGHT_CLASS)
            .type(UPDATED_TYPE);
        WoPackageDTO woPackageDTO = woPackageMapper.toDto(updatedWoPackage);

        restWoPackageMockMvc.perform(put("/api/wo-packages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woPackageDTO)))
            .andExpect(status().isOk());

        // Validate the WoPackage in the database
        List<WoPackage> woPackageList = woPackageRepository.findAll();
        assertThat(woPackageList).hasSize(databaseSizeBeforeUpdate);
        WoPackage testWoPackage = woPackageList.get(woPackageList.size() - 1);
        assertThat(testWoPackage.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testWoPackage.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testWoPackage.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testWoPackage.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testWoPackage.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testWoPackage.getCubedWeight()).isEqualTo(UPDATED_CUBED_WEIGHT);
        assertThat(testWoPackage.getTrackingNumber()).isEqualTo(UPDATED_TRACKING_NUMBER);
        assertThat(testWoPackage.getCodValue()).isEqualTo(UPDATED_COD_VALUE);
        assertThat(testWoPackage.getInsuranceAmount()).isEqualTo(UPDATED_INSURANCE_AMOUNT);
        assertThat(testWoPackage.getOid()).isEqualTo(UPDATED_OID);
        assertThat(testWoPackage.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testWoPackage.getFreightClass()).isEqualTo(UPDATED_FREIGHT_CLASS);
        assertThat(testWoPackage.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingWoPackage() throws Exception {
        int databaseSizeBeforeUpdate = woPackageRepository.findAll().size();

        // Create the WoPackage
        WoPackageDTO woPackageDTO = woPackageMapper.toDto(woPackage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoPackageMockMvc.perform(put("/api/wo-packages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woPackageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoPackage in the database
        List<WoPackage> woPackageList = woPackageRepository.findAll();
        assertThat(woPackageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoPackage() throws Exception {
        // Initialize the database
        woPackageRepository.saveAndFlush(woPackage);

        int databaseSizeBeforeDelete = woPackageRepository.findAll().size();

        // Delete the woPackage
        restWoPackageMockMvc.perform(delete("/api/wo-packages/{id}", woPackage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<WoPackage> woPackageList = woPackageRepository.findAll();
        assertThat(woPackageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoPackage.class);
        WoPackage woPackage1 = new WoPackage();
        woPackage1.setId(1L);
        WoPackage woPackage2 = new WoPackage();
        woPackage2.setId(woPackage1.getId());
        assertThat(woPackage1).isEqualTo(woPackage2);
        woPackage2.setId(2L);
        assertThat(woPackage1).isNotEqualTo(woPackage2);
        woPackage1.setId(null);
        assertThat(woPackage1).isNotEqualTo(woPackage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoPackageDTO.class);
        WoPackageDTO woPackageDTO1 = new WoPackageDTO();
        woPackageDTO1.setId(1L);
        WoPackageDTO woPackageDTO2 = new WoPackageDTO();
        assertThat(woPackageDTO1).isNotEqualTo(woPackageDTO2);
        woPackageDTO2.setId(woPackageDTO1.getId());
        assertThat(woPackageDTO1).isEqualTo(woPackageDTO2);
        woPackageDTO2.setId(2L);
        assertThat(woPackageDTO1).isNotEqualTo(woPackageDTO2);
        woPackageDTO1.setId(null);
        assertThat(woPackageDTO1).isNotEqualTo(woPackageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(woPackageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(woPackageMapper.fromId(null)).isNull();
    }
}
