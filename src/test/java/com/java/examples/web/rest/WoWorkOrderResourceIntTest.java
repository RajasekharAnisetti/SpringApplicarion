package com.java.examples.web.rest;

import com.java.examples.SpringApplicarionApp;

import com.java.examples.domain.WoWorkOrder;
import com.java.examples.repository.WoWorkOrderRepository;
import com.java.examples.service.WoWorkOrderService;
import com.java.examples.service.dto.WoWorkOrderDTO;
import com.java.examples.service.mapper.WoWorkOrderMapper;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static com.java.examples.web.rest.TestUtil.sameInstant;
import static com.java.examples.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the WoWorkOrderResource REST controller.
 *
 * @see WoWorkOrderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringApplicarionApp.class)
public class WoWorkOrderResourceIntTest {

    private static final String DEFAULT_CUSTOMS_BROKER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMS_BROKER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMS_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMS_CONTACT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMS_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMS_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMS_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMS_PHONE_NUMBER = "BBBBBBBBBB";

    private static final Double DEFAULT_CUSTOMS_VALUE = 1D;
    private static final Double UPDATED_CUSTOMS_VALUE = 2D;

    private static final String DEFAULT_FROM_AIRPORT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_FROM_AIRPORT_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_FROM_LOCATION_TYPE = 11;
    private static final Integer UPDATED_FROM_LOCATION_TYPE = 10;

    private static final String DEFAULT_JOB_CONTENT_DESCR = "AAAAAAAAAA";
    private static final String UPDATED_JOB_CONTENT_DESCR = "BBBBBBBBBB";

    private static final Integer DEFAULT_SHIP_QUANTITY = 11;
    private static final Integer UPDATED_SHIP_QUANTITY = 10;

    private static final Integer DEFAULT_JOB_ORIGINAL_COST = 11;
    private static final Integer UPDATED_JOB_ORIGINAL_COST = 10;

    private static final String DEFAULT_QUOTED_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_QUOTED_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGN_TO = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGN_TO = "BBBBBBBBBB";

    private static final String DEFAULT_TO_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_TO_COMPANY = "BBBBBBBBBB";

    private static final Integer DEFAULT_TO_LOCATION_TYPE = 11;
    private static final Integer UPDATED_TO_LOCATION_TYPE = 10;

    private static final ZonedDateTime DEFAULT_DATE_CREATED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_CREATED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_JOB_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_JOB_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_CUSTOMER = "AAAAAAAAAA";
    private static final String UPDATED_JOB_CUSTOMER = "BBBBBBBBBB";

    private static final Long DEFAULT_SERVICE_TYPE = 1L;
    private static final Long UPDATED_SERVICE_TYPE = 2L;

    private static final String DEFAULT_JOB_SALES = "AAAAAAAAAA";
    private static final String UPDATED_JOB_SALES = "BBBBBBBBBB";

    private static final Integer DEFAULT_WO_REQUEST_TYPE = 11;
    private static final Integer UPDATED_WO_REQUEST_TYPE = 10;

    @Autowired
    private WoWorkOrderRepository woWorkOrderRepository;

    @Autowired
    private WoWorkOrderMapper woWorkOrderMapper;

    @Autowired
    private WoWorkOrderService woWorkOrderService;

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

    private MockMvc restWoWorkOrderMockMvc;

    private WoWorkOrder woWorkOrder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WoWorkOrderResource woWorkOrderResource = new WoWorkOrderResource(woWorkOrderService);
        this.restWoWorkOrderMockMvc = MockMvcBuilders.standaloneSetup(woWorkOrderResource)
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
    public static WoWorkOrder createEntity(EntityManager em) {
        WoWorkOrder woWorkOrder = new WoWorkOrder()
            .customsBrokerName(DEFAULT_CUSTOMS_BROKER_NAME)
            .customsContactName(DEFAULT_CUSTOMS_CONTACT_NAME)
            .customsCurrency(DEFAULT_CUSTOMS_CURRENCY)
            .customsPhoneNumber(DEFAULT_CUSTOMS_PHONE_NUMBER)
            .customsValue(DEFAULT_CUSTOMS_VALUE)
            .fromAirportCode(DEFAULT_FROM_AIRPORT_CODE)
            .fromLocationType(DEFAULT_FROM_LOCATION_TYPE)
            .jobContentDescr(DEFAULT_JOB_CONTENT_DESCR)
            .shipQuantity(DEFAULT_SHIP_QUANTITY)
            .jobOriginalCost(DEFAULT_JOB_ORIGINAL_COST)
            .quotedAmount(DEFAULT_QUOTED_AMOUNT)
            .assignTo(DEFAULT_ASSIGN_TO)
            .toCompany(DEFAULT_TO_COMPANY)
            .toLocationType(DEFAULT_TO_LOCATION_TYPE)
            .dateCreated(DEFAULT_DATE_CREATED)
            .jobNumber(DEFAULT_JOB_NUMBER)
            .jobCustomer(DEFAULT_JOB_CUSTOMER)
            .serviceType(DEFAULT_SERVICE_TYPE)
            .jobSales(DEFAULT_JOB_SALES)
            .woRequestType(DEFAULT_WO_REQUEST_TYPE);
        return woWorkOrder;
    }

    @Before
    public void initTest() {
        woWorkOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoWorkOrder() throws Exception {
        int databaseSizeBeforeCreate = woWorkOrderRepository.findAll().size();

        // Create the WoWorkOrder
        WoWorkOrderDTO woWorkOrderDTO = woWorkOrderMapper.toDto(woWorkOrder);
        restWoWorkOrderMockMvc.perform(post("/api/wo-work-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woWorkOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the WoWorkOrder in the database
        List<WoWorkOrder> woWorkOrderList = woWorkOrderRepository.findAll();
        assertThat(woWorkOrderList).hasSize(databaseSizeBeforeCreate + 1);
        WoWorkOrder testWoWorkOrder = woWorkOrderList.get(woWorkOrderList.size() - 1);
        assertThat(testWoWorkOrder.getCustomsBrokerName()).isEqualTo(DEFAULT_CUSTOMS_BROKER_NAME);
        assertThat(testWoWorkOrder.getCustomsContactName()).isEqualTo(DEFAULT_CUSTOMS_CONTACT_NAME);
        assertThat(testWoWorkOrder.getCustomsCurrency()).isEqualTo(DEFAULT_CUSTOMS_CURRENCY);
        assertThat(testWoWorkOrder.getCustomsPhoneNumber()).isEqualTo(DEFAULT_CUSTOMS_PHONE_NUMBER);
        assertThat(testWoWorkOrder.getCustomsValue()).isEqualTo(DEFAULT_CUSTOMS_VALUE);
        assertThat(testWoWorkOrder.getFromAirportCode()).isEqualTo(DEFAULT_FROM_AIRPORT_CODE);
        assertThat(testWoWorkOrder.getFromLocationType()).isEqualTo(DEFAULT_FROM_LOCATION_TYPE);
        assertThat(testWoWorkOrder.getJobContentDescr()).isEqualTo(DEFAULT_JOB_CONTENT_DESCR);
        assertThat(testWoWorkOrder.getShipQuantity()).isEqualTo(DEFAULT_SHIP_QUANTITY);
        assertThat(testWoWorkOrder.getJobOriginalCost()).isEqualTo(DEFAULT_JOB_ORIGINAL_COST);
        assertThat(testWoWorkOrder.getQuotedAmount()).isEqualTo(DEFAULT_QUOTED_AMOUNT);
        assertThat(testWoWorkOrder.getAssignTo()).isEqualTo(DEFAULT_ASSIGN_TO);
        assertThat(testWoWorkOrder.getToCompany()).isEqualTo(DEFAULT_TO_COMPANY);
        assertThat(testWoWorkOrder.getToLocationType()).isEqualTo(DEFAULT_TO_LOCATION_TYPE);
        assertThat(testWoWorkOrder.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testWoWorkOrder.getJobNumber()).isEqualTo(DEFAULT_JOB_NUMBER);
        assertThat(testWoWorkOrder.getJobCustomer()).isEqualTo(DEFAULT_JOB_CUSTOMER);
        assertThat(testWoWorkOrder.getServiceType()).isEqualTo(DEFAULT_SERVICE_TYPE);
        assertThat(testWoWorkOrder.getJobSales()).isEqualTo(DEFAULT_JOB_SALES);
        assertThat(testWoWorkOrder.getWoRequestType()).isEqualTo(DEFAULT_WO_REQUEST_TYPE);
    }

    @Test
    @Transactional
    public void createWoWorkOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woWorkOrderRepository.findAll().size();

        // Create the WoWorkOrder with an existing ID
        woWorkOrder.setId(1L);
        WoWorkOrderDTO woWorkOrderDTO = woWorkOrderMapper.toDto(woWorkOrder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoWorkOrderMockMvc.perform(post("/api/wo-work-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woWorkOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoWorkOrder in the database
        List<WoWorkOrder> woWorkOrderList = woWorkOrderRepository.findAll();
        assertThat(woWorkOrderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllWoWorkOrders() throws Exception {
        // Initialize the database
        woWorkOrderRepository.saveAndFlush(woWorkOrder);

        // Get all the woWorkOrderList
        restWoWorkOrderMockMvc.perform(get("/api/wo-work-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woWorkOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].customsBrokerName").value(hasItem(DEFAULT_CUSTOMS_BROKER_NAME.toString())))
            .andExpect(jsonPath("$.[*].customsContactName").value(hasItem(DEFAULT_CUSTOMS_CONTACT_NAME.toString())))
            .andExpect(jsonPath("$.[*].customsCurrency").value(hasItem(DEFAULT_CUSTOMS_CURRENCY.toString())))
            .andExpect(jsonPath("$.[*].customsPhoneNumber").value(hasItem(DEFAULT_CUSTOMS_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].customsValue").value(hasItem(DEFAULT_CUSTOMS_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].fromAirportCode").value(hasItem(DEFAULT_FROM_AIRPORT_CODE.toString())))
            .andExpect(jsonPath("$.[*].fromLocationType").value(hasItem(DEFAULT_FROM_LOCATION_TYPE)))
            .andExpect(jsonPath("$.[*].jobContentDescr").value(hasItem(DEFAULT_JOB_CONTENT_DESCR.toString())))
            .andExpect(jsonPath("$.[*].shipQuantity").value(hasItem(DEFAULT_SHIP_QUANTITY)))
            .andExpect(jsonPath("$.[*].jobOriginalCost").value(hasItem(DEFAULT_JOB_ORIGINAL_COST)))
            .andExpect(jsonPath("$.[*].quotedAmount").value(hasItem(DEFAULT_QUOTED_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].assignTo").value(hasItem(DEFAULT_ASSIGN_TO.toString())))
            .andExpect(jsonPath("$.[*].toCompany").value(hasItem(DEFAULT_TO_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].toLocationType").value(hasItem(DEFAULT_TO_LOCATION_TYPE)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(sameInstant(DEFAULT_DATE_CREATED))))
            .andExpect(jsonPath("$.[*].jobNumber").value(hasItem(DEFAULT_JOB_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].jobCustomer").value(hasItem(DEFAULT_JOB_CUSTOMER.toString())))
            .andExpect(jsonPath("$.[*].serviceType").value(hasItem(DEFAULT_SERVICE_TYPE.intValue())))
            .andExpect(jsonPath("$.[*].jobSales").value(hasItem(DEFAULT_JOB_SALES.toString())))
            .andExpect(jsonPath("$.[*].woRequestType").value(hasItem(DEFAULT_WO_REQUEST_TYPE)));
    }
    
    @Test
    @Transactional
    public void getWoWorkOrder() throws Exception {
        // Initialize the database
        woWorkOrderRepository.saveAndFlush(woWorkOrder);

        // Get the woWorkOrder
        restWoWorkOrderMockMvc.perform(get("/api/wo-work-orders/{id}", woWorkOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(woWorkOrder.getId().intValue()))
            .andExpect(jsonPath("$.customsBrokerName").value(DEFAULT_CUSTOMS_BROKER_NAME.toString()))
            .andExpect(jsonPath("$.customsContactName").value(DEFAULT_CUSTOMS_CONTACT_NAME.toString()))
            .andExpect(jsonPath("$.customsCurrency").value(DEFAULT_CUSTOMS_CURRENCY.toString()))
            .andExpect(jsonPath("$.customsPhoneNumber").value(DEFAULT_CUSTOMS_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.customsValue").value(DEFAULT_CUSTOMS_VALUE.doubleValue()))
            .andExpect(jsonPath("$.fromAirportCode").value(DEFAULT_FROM_AIRPORT_CODE.toString()))
            .andExpect(jsonPath("$.fromLocationType").value(DEFAULT_FROM_LOCATION_TYPE))
            .andExpect(jsonPath("$.jobContentDescr").value(DEFAULT_JOB_CONTENT_DESCR.toString()))
            .andExpect(jsonPath("$.shipQuantity").value(DEFAULT_SHIP_QUANTITY))
            .andExpect(jsonPath("$.jobOriginalCost").value(DEFAULT_JOB_ORIGINAL_COST))
            .andExpect(jsonPath("$.quotedAmount").value(DEFAULT_QUOTED_AMOUNT.toString()))
            .andExpect(jsonPath("$.assignTo").value(DEFAULT_ASSIGN_TO.toString()))
            .andExpect(jsonPath("$.toCompany").value(DEFAULT_TO_COMPANY.toString()))
            .andExpect(jsonPath("$.toLocationType").value(DEFAULT_TO_LOCATION_TYPE))
            .andExpect(jsonPath("$.dateCreated").value(sameInstant(DEFAULT_DATE_CREATED)))
            .andExpect(jsonPath("$.jobNumber").value(DEFAULT_JOB_NUMBER.toString()))
            .andExpect(jsonPath("$.jobCustomer").value(DEFAULT_JOB_CUSTOMER.toString()))
            .andExpect(jsonPath("$.serviceType").value(DEFAULT_SERVICE_TYPE.intValue()))
            .andExpect(jsonPath("$.jobSales").value(DEFAULT_JOB_SALES.toString()))
            .andExpect(jsonPath("$.woRequestType").value(DEFAULT_WO_REQUEST_TYPE));
    }

    @Test
    @Transactional
    public void getNonExistingWoWorkOrder() throws Exception {
        // Get the woWorkOrder
        restWoWorkOrderMockMvc.perform(get("/api/wo-work-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoWorkOrder() throws Exception {
        // Initialize the database
        woWorkOrderRepository.saveAndFlush(woWorkOrder);

        int databaseSizeBeforeUpdate = woWorkOrderRepository.findAll().size();

        // Update the woWorkOrder
        WoWorkOrder updatedWoWorkOrder = woWorkOrderRepository.findById(woWorkOrder.getId()).get();
        // Disconnect from session so that the updates on updatedWoWorkOrder are not directly saved in db
        em.detach(updatedWoWorkOrder);
        updatedWoWorkOrder
            .customsBrokerName(UPDATED_CUSTOMS_BROKER_NAME)
            .customsContactName(UPDATED_CUSTOMS_CONTACT_NAME)
            .customsCurrency(UPDATED_CUSTOMS_CURRENCY)
            .customsPhoneNumber(UPDATED_CUSTOMS_PHONE_NUMBER)
            .customsValue(UPDATED_CUSTOMS_VALUE)
            .fromAirportCode(UPDATED_FROM_AIRPORT_CODE)
            .fromLocationType(UPDATED_FROM_LOCATION_TYPE)
            .jobContentDescr(UPDATED_JOB_CONTENT_DESCR)
            .shipQuantity(UPDATED_SHIP_QUANTITY)
            .jobOriginalCost(UPDATED_JOB_ORIGINAL_COST)
            .quotedAmount(UPDATED_QUOTED_AMOUNT)
            .assignTo(UPDATED_ASSIGN_TO)
            .toCompany(UPDATED_TO_COMPANY)
            .toLocationType(UPDATED_TO_LOCATION_TYPE)
            .dateCreated(UPDATED_DATE_CREATED)
            .jobNumber(UPDATED_JOB_NUMBER)
            .jobCustomer(UPDATED_JOB_CUSTOMER)
            .serviceType(UPDATED_SERVICE_TYPE)
            .jobSales(UPDATED_JOB_SALES)
            .woRequestType(UPDATED_WO_REQUEST_TYPE);
        WoWorkOrderDTO woWorkOrderDTO = woWorkOrderMapper.toDto(updatedWoWorkOrder);

        restWoWorkOrderMockMvc.perform(put("/api/wo-work-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woWorkOrderDTO)))
            .andExpect(status().isOk());

        // Validate the WoWorkOrder in the database
        List<WoWorkOrder> woWorkOrderList = woWorkOrderRepository.findAll();
        assertThat(woWorkOrderList).hasSize(databaseSizeBeforeUpdate);
        WoWorkOrder testWoWorkOrder = woWorkOrderList.get(woWorkOrderList.size() - 1);
        assertThat(testWoWorkOrder.getCustomsBrokerName()).isEqualTo(UPDATED_CUSTOMS_BROKER_NAME);
        assertThat(testWoWorkOrder.getCustomsContactName()).isEqualTo(UPDATED_CUSTOMS_CONTACT_NAME);
        assertThat(testWoWorkOrder.getCustomsCurrency()).isEqualTo(UPDATED_CUSTOMS_CURRENCY);
        assertThat(testWoWorkOrder.getCustomsPhoneNumber()).isEqualTo(UPDATED_CUSTOMS_PHONE_NUMBER);
        assertThat(testWoWorkOrder.getCustomsValue()).isEqualTo(UPDATED_CUSTOMS_VALUE);
        assertThat(testWoWorkOrder.getFromAirportCode()).isEqualTo(UPDATED_FROM_AIRPORT_CODE);
        assertThat(testWoWorkOrder.getFromLocationType()).isEqualTo(UPDATED_FROM_LOCATION_TYPE);
        assertThat(testWoWorkOrder.getJobContentDescr()).isEqualTo(UPDATED_JOB_CONTENT_DESCR);
        assertThat(testWoWorkOrder.getShipQuantity()).isEqualTo(UPDATED_SHIP_QUANTITY);
        assertThat(testWoWorkOrder.getJobOriginalCost()).isEqualTo(UPDATED_JOB_ORIGINAL_COST);
        assertThat(testWoWorkOrder.getQuotedAmount()).isEqualTo(UPDATED_QUOTED_AMOUNT);
        assertThat(testWoWorkOrder.getAssignTo()).isEqualTo(UPDATED_ASSIGN_TO);
        assertThat(testWoWorkOrder.getToCompany()).isEqualTo(UPDATED_TO_COMPANY);
        assertThat(testWoWorkOrder.getToLocationType()).isEqualTo(UPDATED_TO_LOCATION_TYPE);
        assertThat(testWoWorkOrder.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testWoWorkOrder.getJobNumber()).isEqualTo(UPDATED_JOB_NUMBER);
        assertThat(testWoWorkOrder.getJobCustomer()).isEqualTo(UPDATED_JOB_CUSTOMER);
        assertThat(testWoWorkOrder.getServiceType()).isEqualTo(UPDATED_SERVICE_TYPE);
        assertThat(testWoWorkOrder.getJobSales()).isEqualTo(UPDATED_JOB_SALES);
        assertThat(testWoWorkOrder.getWoRequestType()).isEqualTo(UPDATED_WO_REQUEST_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingWoWorkOrder() throws Exception {
        int databaseSizeBeforeUpdate = woWorkOrderRepository.findAll().size();

        // Create the WoWorkOrder
        WoWorkOrderDTO woWorkOrderDTO = woWorkOrderMapper.toDto(woWorkOrder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoWorkOrderMockMvc.perform(put("/api/wo-work-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woWorkOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoWorkOrder in the database
        List<WoWorkOrder> woWorkOrderList = woWorkOrderRepository.findAll();
        assertThat(woWorkOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoWorkOrder() throws Exception {
        // Initialize the database
        woWorkOrderRepository.saveAndFlush(woWorkOrder);

        int databaseSizeBeforeDelete = woWorkOrderRepository.findAll().size();

        // Delete the woWorkOrder
        restWoWorkOrderMockMvc.perform(delete("/api/wo-work-orders/{id}", woWorkOrder.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<WoWorkOrder> woWorkOrderList = woWorkOrderRepository.findAll();
        assertThat(woWorkOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoWorkOrder.class);
        WoWorkOrder woWorkOrder1 = new WoWorkOrder();
        woWorkOrder1.setId(1L);
        WoWorkOrder woWorkOrder2 = new WoWorkOrder();
        woWorkOrder2.setId(woWorkOrder1.getId());
        assertThat(woWorkOrder1).isEqualTo(woWorkOrder2);
        woWorkOrder2.setId(2L);
        assertThat(woWorkOrder1).isNotEqualTo(woWorkOrder2);
        woWorkOrder1.setId(null);
        assertThat(woWorkOrder1).isNotEqualTo(woWorkOrder2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoWorkOrderDTO.class);
        WoWorkOrderDTO woWorkOrderDTO1 = new WoWorkOrderDTO();
        woWorkOrderDTO1.setId(1L);
        WoWorkOrderDTO woWorkOrderDTO2 = new WoWorkOrderDTO();
        assertThat(woWorkOrderDTO1).isNotEqualTo(woWorkOrderDTO2);
        woWorkOrderDTO2.setId(woWorkOrderDTO1.getId());
        assertThat(woWorkOrderDTO1).isEqualTo(woWorkOrderDTO2);
        woWorkOrderDTO2.setId(2L);
        assertThat(woWorkOrderDTO1).isNotEqualTo(woWorkOrderDTO2);
        woWorkOrderDTO1.setId(null);
        assertThat(woWorkOrderDTO1).isNotEqualTo(woWorkOrderDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(woWorkOrderMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(woWorkOrderMapper.fromId(null)).isNull();
    }
}
