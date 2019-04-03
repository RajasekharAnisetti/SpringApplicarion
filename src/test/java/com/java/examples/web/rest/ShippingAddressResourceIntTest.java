package com.java.examples.web.rest;

import com.java.examples.SpringApplicarionApp;

import com.java.examples.domain.ShippingAddress;
import com.java.examples.repository.ShippingAddressRepository;
import com.java.examples.service.ShippingAddressService;
import com.java.examples.service.dto.ShippingAddressDTO;
import com.java.examples.service.mapper.ShippingAddressMapper;
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
 * Test class for the ShippingAddressResource REST controller.
 *
 * @see ShippingAddressResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringApplicarionApp.class)
public class ShippingAddressResourceIntTest {

    private static final String DEFAULT_ATTENTION = "AAAAAAAAAA";
    private static final String UPDATED_ATTENTION = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CONFIRM_DELIVERY = false;
    private static final Boolean UPDATED_CONFIRM_DELIVERY = true;

    private static final String DEFAULT_INSTRUCTIONS = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUCTIONS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_NOTIFY_RECIPIENT = false;
    private static final Boolean UPDATED_NOTIFY_RECIPIENT = true;

    private static final Boolean DEFAULT_RES = false;
    private static final Boolean UPDATED_RES = true;

    private static final Boolean DEFAULT_TAILGATE = false;
    private static final Boolean UPDATED_TAILGATE = true;

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private ShippingAddressMapper shippingAddressMapper;

    @Autowired
    private ShippingAddressService shippingAddressService;

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

    private MockMvc restShippingAddressMockMvc;

    private ShippingAddress shippingAddress;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ShippingAddressResource shippingAddressResource = new ShippingAddressResource(shippingAddressService);
        this.restShippingAddressMockMvc = MockMvcBuilders.standaloneSetup(shippingAddressResource)
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
    public static ShippingAddress createEntity(EntityManager em) {
        ShippingAddress shippingAddress = new ShippingAddress()
            .attention(DEFAULT_ATTENTION)
            .company(DEFAULT_COMPANY)
            .address1(DEFAULT_ADDRESS_1)
            .address2(DEFAULT_ADDRESS_2)
            .phone(DEFAULT_PHONE)
            .email(DEFAULT_EMAIL)
            .postalCode(DEFAULT_POSTAL_CODE)
            .confirmDelivery(DEFAULT_CONFIRM_DELIVERY)
            .instructions(DEFAULT_INSTRUCTIONS)
            .notifyRecipient(DEFAULT_NOTIFY_RECIPIENT)
            .res(DEFAULT_RES)
            .tailgate(DEFAULT_TAILGATE);
        return shippingAddress;
    }

    @Before
    public void initTest() {
        shippingAddress = createEntity(em);
    }

    @Test
    @Transactional
    public void createShippingAddress() throws Exception {
        int databaseSizeBeforeCreate = shippingAddressRepository.findAll().size();

        // Create the ShippingAddress
        ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(shippingAddress);
        restShippingAddressMockMvc.perform(post("/api/shipping-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO)))
            .andExpect(status().isCreated());

        // Validate the ShippingAddress in the database
        List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
        assertThat(shippingAddressList).hasSize(databaseSizeBeforeCreate + 1);
        ShippingAddress testShippingAddress = shippingAddressList.get(shippingAddressList.size() - 1);
        assertThat(testShippingAddress.getAttention()).isEqualTo(DEFAULT_ATTENTION);
        assertThat(testShippingAddress.getCompany()).isEqualTo(DEFAULT_COMPANY);
        assertThat(testShippingAddress.getAddress1()).isEqualTo(DEFAULT_ADDRESS_1);
        assertThat(testShippingAddress.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
        assertThat(testShippingAddress.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testShippingAddress.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testShippingAddress.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testShippingAddress.isConfirmDelivery()).isEqualTo(DEFAULT_CONFIRM_DELIVERY);
        assertThat(testShippingAddress.getInstructions()).isEqualTo(DEFAULT_INSTRUCTIONS);
        assertThat(testShippingAddress.isNotifyRecipient()).isEqualTo(DEFAULT_NOTIFY_RECIPIENT);
        assertThat(testShippingAddress.isRes()).isEqualTo(DEFAULT_RES);
        assertThat(testShippingAddress.isTailgate()).isEqualTo(DEFAULT_TAILGATE);
    }

    @Test
    @Transactional
    public void createShippingAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shippingAddressRepository.findAll().size();

        // Create the ShippingAddress with an existing ID
        shippingAddress.setId(1L);
        ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(shippingAddress);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShippingAddressMockMvc.perform(post("/api/shipping-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShippingAddress in the database
        List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
        assertThat(shippingAddressList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllShippingAddresses() throws Exception {
        // Initialize the database
        shippingAddressRepository.saveAndFlush(shippingAddress);

        // Get all the shippingAddressList
        restShippingAddressMockMvc.perform(get("/api/shipping-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shippingAddress.getId().intValue())))
            .andExpect(jsonPath("$.[*].attention").value(hasItem(DEFAULT_ATTENTION.toString())))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].address1").value(hasItem(DEFAULT_ADDRESS_1.toString())))
            .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE.toString())))
            .andExpect(jsonPath("$.[*].confirmDelivery").value(hasItem(DEFAULT_CONFIRM_DELIVERY.booleanValue())))
            .andExpect(jsonPath("$.[*].instructions").value(hasItem(DEFAULT_INSTRUCTIONS.toString())))
            .andExpect(jsonPath("$.[*].notifyRecipient").value(hasItem(DEFAULT_NOTIFY_RECIPIENT.booleanValue())))
            .andExpect(jsonPath("$.[*].res").value(hasItem(DEFAULT_RES.booleanValue())))
            .andExpect(jsonPath("$.[*].tailgate").value(hasItem(DEFAULT_TAILGATE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getShippingAddress() throws Exception {
        // Initialize the database
        shippingAddressRepository.saveAndFlush(shippingAddress);

        // Get the shippingAddress
        restShippingAddressMockMvc.perform(get("/api/shipping-addresses/{id}", shippingAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(shippingAddress.getId().intValue()))
            .andExpect(jsonPath("$.attention").value(DEFAULT_ATTENTION.toString()))
            .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY.toString()))
            .andExpect(jsonPath("$.address1").value(DEFAULT_ADDRESS_1.toString()))
            .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE.toString()))
            .andExpect(jsonPath("$.confirmDelivery").value(DEFAULT_CONFIRM_DELIVERY.booleanValue()))
            .andExpect(jsonPath("$.instructions").value(DEFAULT_INSTRUCTIONS.toString()))
            .andExpect(jsonPath("$.notifyRecipient").value(DEFAULT_NOTIFY_RECIPIENT.booleanValue()))
            .andExpect(jsonPath("$.res").value(DEFAULT_RES.booleanValue()))
            .andExpect(jsonPath("$.tailgate").value(DEFAULT_TAILGATE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingShippingAddress() throws Exception {
        // Get the shippingAddress
        restShippingAddressMockMvc.perform(get("/api/shipping-addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShippingAddress() throws Exception {
        // Initialize the database
        shippingAddressRepository.saveAndFlush(shippingAddress);

        int databaseSizeBeforeUpdate = shippingAddressRepository.findAll().size();

        // Update the shippingAddress
        ShippingAddress updatedShippingAddress = shippingAddressRepository.findById(shippingAddress.getId()).get();
        // Disconnect from session so that the updates on updatedShippingAddress are not directly saved in db
        em.detach(updatedShippingAddress);
        updatedShippingAddress
            .attention(UPDATED_ATTENTION)
            .company(UPDATED_COMPANY)
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .postalCode(UPDATED_POSTAL_CODE)
            .confirmDelivery(UPDATED_CONFIRM_DELIVERY)
            .instructions(UPDATED_INSTRUCTIONS)
            .notifyRecipient(UPDATED_NOTIFY_RECIPIENT)
            .res(UPDATED_RES)
            .tailgate(UPDATED_TAILGATE);
        ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(updatedShippingAddress);

        restShippingAddressMockMvc.perform(put("/api/shipping-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO)))
            .andExpect(status().isOk());

        // Validate the ShippingAddress in the database
        List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
        assertThat(shippingAddressList).hasSize(databaseSizeBeforeUpdate);
        ShippingAddress testShippingAddress = shippingAddressList.get(shippingAddressList.size() - 1);
        assertThat(testShippingAddress.getAttention()).isEqualTo(UPDATED_ATTENTION);
        assertThat(testShippingAddress.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testShippingAddress.getAddress1()).isEqualTo(UPDATED_ADDRESS_1);
        assertThat(testShippingAddress.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testShippingAddress.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testShippingAddress.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testShippingAddress.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testShippingAddress.isConfirmDelivery()).isEqualTo(UPDATED_CONFIRM_DELIVERY);
        assertThat(testShippingAddress.getInstructions()).isEqualTo(UPDATED_INSTRUCTIONS);
        assertThat(testShippingAddress.isNotifyRecipient()).isEqualTo(UPDATED_NOTIFY_RECIPIENT);
        assertThat(testShippingAddress.isRes()).isEqualTo(UPDATED_RES);
        assertThat(testShippingAddress.isTailgate()).isEqualTo(UPDATED_TAILGATE);
    }

    @Test
    @Transactional
    public void updateNonExistingShippingAddress() throws Exception {
        int databaseSizeBeforeUpdate = shippingAddressRepository.findAll().size();

        // Create the ShippingAddress
        ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(shippingAddress);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShippingAddressMockMvc.perform(put("/api/shipping-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShippingAddress in the database
        List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
        assertThat(shippingAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShippingAddress() throws Exception {
        // Initialize the database
        shippingAddressRepository.saveAndFlush(shippingAddress);

        int databaseSizeBeforeDelete = shippingAddressRepository.findAll().size();

        // Delete the shippingAddress
        restShippingAddressMockMvc.perform(delete("/api/shipping-addresses/{id}", shippingAddress.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
        assertThat(shippingAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShippingAddress.class);
        ShippingAddress shippingAddress1 = new ShippingAddress();
        shippingAddress1.setId(1L);
        ShippingAddress shippingAddress2 = new ShippingAddress();
        shippingAddress2.setId(shippingAddress1.getId());
        assertThat(shippingAddress1).isEqualTo(shippingAddress2);
        shippingAddress2.setId(2L);
        assertThat(shippingAddress1).isNotEqualTo(shippingAddress2);
        shippingAddress1.setId(null);
        assertThat(shippingAddress1).isNotEqualTo(shippingAddress2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShippingAddressDTO.class);
        ShippingAddressDTO shippingAddressDTO1 = new ShippingAddressDTO();
        shippingAddressDTO1.setId(1L);
        ShippingAddressDTO shippingAddressDTO2 = new ShippingAddressDTO();
        assertThat(shippingAddressDTO1).isNotEqualTo(shippingAddressDTO2);
        shippingAddressDTO2.setId(shippingAddressDTO1.getId());
        assertThat(shippingAddressDTO1).isEqualTo(shippingAddressDTO2);
        shippingAddressDTO2.setId(2L);
        assertThat(shippingAddressDTO1).isNotEqualTo(shippingAddressDTO2);
        shippingAddressDTO1.setId(null);
        assertThat(shippingAddressDTO1).isNotEqualTo(shippingAddressDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(shippingAddressMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(shippingAddressMapper.fromId(null)).isNull();
    }
}
